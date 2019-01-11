package com.yjk.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjk.app.annotation.DistributedLock;
import com.yjk.app.common.Constants;
import com.yjk.app.common.TemplateEnum;
import com.yjk.app.dto.BindMobileDTO;
import com.yjk.app.dto.CallAppealDTO;
import com.yjk.app.dto.DecryptUserInfoDTO;
import com.yjk.app.dto.ForgotPasswordDTO;
import com.yjk.app.dto.GenerateOpenIdDTO;
import com.yjk.app.dto.LoginDTO;
import com.yjk.app.dto.ModifyPasswordDTO;
import com.yjk.app.dto.PhoneNumberDTO;
import com.yjk.app.dto.RegisterDTO;
import com.yjk.app.dto.ShareInfoDTO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.MemberService;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.strategy.TemplateMessageStragegy;
import com.yjk.app.util.JwtUtils;
import com.yjk.app.util.PayUtil;
import com.yjk.app.util.R;
import com.yjk.app.util.RandomMethod;
import com.yjk.app.util.UuidUtils;
import com.yjk.app.validator.Assert;
import com.yjk.app.vo.DecryptUserInfoVO;
import com.yjk.app.vo.Jscode2SessionVO;
import com.yjk.app.vo.LoginVO;
import com.yjk.app.vo.PhoneNumberVO;
import com.yjk.app.vo.ReceiveComplainVO;
import com.yjk.app.vo.ShareInfoVO;
import com.yjk.app.vo.WxComplainVO;
import com.yjk.common.dao.FeedBackMapper;
import com.yjk.common.dao.MemberInfoMapper;
import com.yjk.common.dao.MemberMapper;
import com.yjk.common.entity.FeedBackDO;
import com.yjk.common.entity.MemberDO;
import com.yjk.common.entity.MemberInfoDO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	JwtUtils jwtUtils;
	
	public MemberDO selectByPrimaryKey(Long id) {
		return memberMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 用户登录接口
	 * @param loginDTO
	 */
	public R login(LoginDTO loginDTO) {
		Example example = new Example(MemberDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("moble", loginDTO.getMobile());
		
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size()<1) {
			throw new RRException("手机号不存在！");
		}if(members.size()>1) {
			throw new RRException("存在多个相同账号请联系管理员");
		}else {
			MemberDO member = members.get(0);
			String password = member.getPassword();
			if(!password.equals(loginDTO.getPassword())) {
				throw new RRException("用户名密码错误");
			}else {
				if(member.getStatus()==0) {
					throw new RRException("该用户已被禁用！");
				}else if(member.getLoginCount()==null) {
					member.setLoginCount(0);
				}
				member.setLoginCount(member.getLoginCount()+1);
				memberMapper.updateByPrimaryKeySelective(member);
				
				LoginVO loginVO = new LoginVO();
				String uuid = UuidUtils.get32UUID();
				loginVO.setToken(jwtUtils.generateToken(member.getId().toString()+"#"+uuid));
				valueOperations.set(Constants.TERMINAL+"_"+member.getId().toString(),uuid);
				loginVO.setHeadImage(member.getHeadImage());
				loginVO.setNickName(member.getNickName());
				loginVO.setMobile(member.getMoble());
				return R.ok().put("data", loginVO);
			}
		}
		
	}
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@Autowired
	MemberInfoMapper memberInfoMapper;
	
	/**
	 * 用户注册接口
	 * @param registerDTO
	 */
	@Transactional
	public R register(RegisterDTO registerDTO) {
		Long inviterId = null;
		Example example = new Example(MemberDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("moble",registerDTO.getMobile());
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size() > 0) {
			throw new RRException("该用户已被注册！");
		}
		String zcCaptcha = valueOperations.get(Constants.ZC_KEY_CAPTCHA+"_"+registerDTO.getMobile());
		if(zcCaptcha == null) {
			throw new RRException("请发送验证码");
		}else {
			if(!zcCaptcha.equals(registerDTO.getVerificationCode())) {
				throw new RRException("验证码错误！");
			}
			if(registerDTO.getInviteCode() != null) {
				Example inviteQuery = new Example(MemberDO.class);
				inviteQuery.createCriteria().andEqualTo("inviteCode",registerDTO.getInviteCode());
				List<MemberDO> inviteQuerys = memberMapper.selectByExample(inviteQuery);
				if(inviteQuerys.size()<1) {
					throw new RRException("该邀请人不存在");
				}else {
					inviterId = inviteQuerys.get(0).getId();	
				}
			}
		}
		
		MemberDO member = new MemberDO();
		member.setInviterId(inviterId);
		member.setStatus(1);
		member.setType(1);//普通用户
		member.setPassword(registerDTO.getPassword());
		member.setMoble(registerDTO.getMobile());
		member.setCreateTime(new Date());
		member.setUpdateTime(new Date());
		member.setCreditScore(0);
		member.setNickName(generateNickName());
		member.setInviteCode(generateInviteCode());
		member.setCorporageCertification(0);//0 未认证  1待审核 2已认证
		member.setPersionCertification(0);//0未认证 1待审核  2已认证
		memberMapper.insertSelective(member);
		MemberInfoDO memberInfo = new MemberInfoDO();
		memberInfo.setCreateTime(new Date());
		memberInfo.setUpdateTime(new Date());
		memberInfo.setMemberId(member.getId());
		memberInfoMapper.insertSelective(memberInfo);
		return R.ok().put("info",member);
		
	}
	
	
	public R bindMobileByXcx(PhoneNumberDTO phoneNumberDTO,Long userId) throws Exception {
		String session_key = valueOperations.get(Constants.XCX_SESSION_KEY+userId);
		phoneNumberDTO.setSession_key(session_key);
		PhoneNumberVO phoneNumberVO = payUtil.decryptedPhoneNumber(phoneNumberDTO);
		if(phoneNumberVO !=null) {
			MemberDO memberDO = new MemberDO();
			memberDO.setId(userId);
			if(phoneNumberVO.getPhoneNumber() != null) {
				memberDO.setMoble(phoneNumberVO.getPhoneNumber());
			}else {
				memberDO.setMoble(phoneNumberVO.getPurePhoneNumber());
			}
			memberMapper.updateByPrimaryKeySelective(memberDO);
		}
		return R.ok().put("info", phoneNumberVO);
	}
	/**
	 * 生成用户呢城
	 */
	public String generateNickName() {
		String nickName = "yjk_"+RandomMethod.getRandomStr(5+new Random().nextInt(2));
		Example example = new Example(MemberDO.class);
		example.createCriteria().andEqualTo("nickName",nickName);
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size()>0) {
			return generateNickName();
		}else {
			return nickName;
		}
	}
	
	/**
	 * 生成注册邀请码
	 * @return
	 */
	public String generateInviteCode() {
		String inviteCode = RandomMethod.getRandomStr(6);
		Example example = new Example(MemberDO.class);
		example.createCriteria().andEqualTo("inviteCode",inviteCode);
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size()>0) {
			return generateInviteCode();
		}else {
			return inviteCode;
		}
	}
	
	/**
	 * 用户忘记密码接口
	 */
	public R forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
		String zhCaptcha = valueOperations.get(Constants.ZH_KEY_CAPTCHA+"_"+forgotPasswordDTO.getMobile());
		if(zhCaptcha==null) {
			throw new RRException("请发送验证码！");
		}
		if(!forgotPasswordDTO.getVerificationCode().equals(zhCaptcha)){
			throw new RRException("验证码错误！");
		}
		Example example = new Example(MemberDO.class);
		example.createCriteria().andEqualTo("moble",forgotPasswordDTO.getMobile());
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size()<1) {
			throw new RRException("该账号不存在");
		}
		if(members.size()>1) {
			throw new RRException("存在多个账号请联系管理员");
		}
		MemberDO member = new MemberDO();
		member.setId(members.get(0).getId());
		member.setPassword(forgotPasswordDTO.getNewPassword());
		member.setUpdateTime(new Date());
		memberMapper.updateByPrimaryKeySelective(member);
		return R.ok();
		
	}
	
	/**
	 * 用户修改密码接口
	 * @param modifyPasswordDTO
	 */
	public R modifyPassword(ModifyPasswordDTO modifyPasswordDTO) {
		MemberDO member = memberMapper.selectByPrimaryKey(modifyPasswordDTO.getMemberId());
		String password = member.getPassword();
		if(!modifyPasswordDTO.getOldPassword().equals(password)) {
			throw new RRException("原密码错误！");
		}
		member.setPassword(modifyPasswordDTO.getNewPassword());
		memberMapper.updateByPrimaryKeySelective(member);
		return R.ok();
		
	}
	
	/**
	 * 编辑用户信息接口
	 * @return
	 */
	public R editMemberInfo(MemberDO member) {
		MemberDO dbMemer = memberMapper.selectByPrimaryKey(member.getId());
		if(StringUtils.isBlank(member.getNickName())) {
			dbMemer.setNickName(member.getNickName());
		}else if(StringUtils.isBlank(member.getHeadImage())) {
			dbMemer.setHeadImage(member.getHeadImage());
		}
		memberMapper.updateByPrimaryKeySelective(dbMemer);		
		return R.ok();
	}
	
	interface loginStatus{
		Integer NOT_BIND_PHONE = 1007;
	}
	
	
	/**
	 * 用户通过小程序登录(需要保定手机号才能登陆)
	 * @throws Exception 
	 */
	public R loginByXcxNeedMobile(String code) throws Exception {
		String openId = getOpenIdByCode(code);
		Example example = new Example(MemberDO.class);
		example.createCriteria().andEqualTo("xcxOpenId",openId);
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size()<1) {
			valueOperations.set(openId, code, 10, TimeUnit.MINUTES); //该方法是为了防止客户端传入假的openID
			return R.error(MemberServiceImpl.loginStatus.NOT_BIND_PHONE, "请绑定手机号码").put("info", openId);
		}
		MemberDO member = members.get(0);
		LoginVO loginVO = new LoginVO();
		loginVO.setToken(jwtUtils.generateToken(member.getId().toString()));
		loginVO.setHeadImage(member.getHeadImage());
		loginVO.setNickName(member.getNickName());
		loginVO.setMobile(member.getMoble());
		return R.ok().put("info", loginVO);
	}
	
	/**
	 * 不需要通过手机号就能登陆
	 * @throws Exception 
	 */
	public R loginByXcx(String code,String iv,String encryptedData) throws Exception {
		Jscode2SessionVO jsv = getOpenIdAndSeesinKeyByCode(code);
		Example example = new Example(MemberDO.class);
		example.createCriteria().andEqualTo("xcxOpenId",jsv.getOpenid());
		List<MemberDO> members = memberMapper.selectByExample(example);
		MemberDO memberDO = null;
		if(members.size()<1) {
			DecryptUserInfoDTO decryptUserInfoDTO = new DecryptUserInfoDTO(); 
			decryptUserInfoDTO.setIv(iv);
			decryptUserInfoDTO.setEncryptedData(encryptedData);
			decryptUserInfoDTO.setSession_key(jsv.getSession_key());
			DecryptUserInfoVO decryptUserInfo = payUtil.DecryptUserInfo(decryptUserInfoDTO);
			MemberDO member = new MemberDO();
			member.setStatus(1);
			member.setType(1);//普通用户
			member.setCreateTime(new Date());
			member.setUpdateTime(new Date());
			member.setCreditScore(0);
			member.setHeadImage(decryptUserInfo.getAvatarUrl());
			member.setNickName(decryptUserInfo.getNickName());
			member.setInviteCode(generateInviteCode());
			member.setCorporageCertification(0);//0 未认证  1待审核 2已认证
			member.setPersionCertification(0);//0未认证 1待审核  2已认证
			member.setXcxOpenId(jsv.getOpenid());
			member.setRemainCallCount(2);
			memberMapper.insertSelective(member);
			MemberInfoDO memberInfo = new MemberInfoDO();
			memberInfo.setCreateTime(new Date());
			memberInfo.setUpdateTime(new Date());
			memberInfo.setMemberId(member.getId());
			memberInfoMapper.insertSelective(memberInfo);
			memberDO = member;
		}else {
			memberDO = members.get(0);
			if(memberDO.getStatus() == 0) {
				throw new RRException("该用户已被禁用！");
			}
		}
		//保存用户的session_key
		valueOperations.set(Constants.XCX_SESSION_KEY+memberDO.getId().toString(), jsv.getSession_key());
		valueOperations.set(Constants.LAST_LOGIN_TIME+memberDO.getId(), System.currentTimeMillis()+"");
		LoginVO loginVO = new LoginVO();
		loginVO.setToken(jwtUtils.generateToken(memberDO.getId().toString()));
		loginVO.setHeadImage(memberDO.getHeadImage());
		loginVO.setNickName(memberDO.getNickName());
		loginVO.setMobile(memberDO.getMoble());
		return R.ok().put("info", loginVO);
	}
	
	
	
	/**
	 * 小程序绑定手机号接口
	 * @return
	 */
	public R bindMobile(BindMobileDTO bindMobileDTO) {
		String bmCaptcha = valueOperations.get(Constants.BM_KEY_CAPTCHA+"_"+bindMobileDTO.getMobile());
		if(bmCaptcha==null) {
			throw new RRException("请发送验证码！");
		}
		if(!bindMobileDTO.getVerificationCode().equals(bmCaptcha)) {
			throw new RRException("验证码错误");
		}
		
		/*****************该方法是方式客户端传入假的openId*****************/
		String code = valueOperations.get(bindMobileDTO.getOpenId());
		if(code == null) {
			throw new RRException("code失效请重新获取"); 
		}
		/*************************************/
		Example example = new Example(MemberDO.class);
		example.createCriteria().andEqualTo("xcxOpenId",bindMobileDTO.getOpenId());
		List<MemberDO> members = memberMapper.selectByExample(example);
		if(members.size()>0) {
			throw new RRException("您已绑定手机号");
		}
		Example exampleQueryMobile = new Example(MemberDO.class);
		exampleQueryMobile.createCriteria().andEqualTo("moble",bindMobileDTO.getMobile());
		List<MemberDO> memberList = memberMapper.selectByExample(exampleQueryMobile);
		MemberDO member = new MemberDO();
		if(memberList.size()<1) {
			member.setStatus(1);
			member.setType(1);//普通用户
			member.setMoble(bindMobileDTO.getMobile());
			member.setCreateTime(new Date());
			member.setUpdateTime(new Date());
			member.setCreditScore(0);
			member.setXcxOpenId(bindMobileDTO.getOpenId());
			member.setNickName(generateNickName());
			member.setInviteCode(generateInviteCode());
			member.setCorporageCertification(0);//0 未认证  1待审核 2已认证
			member.setPersionCertification(0);//0未认证 1待审核  2已认证
			memberMapper.insertSelective(member);
			MemberInfoDO memberInfo = new MemberInfoDO();
			memberInfo.setCreateTime(new Date());
			memberInfo.setUpdateTime(new Date());
			memberInfo.setMemberId(member.getId());
			memberInfoMapper.insertSelective(memberInfo);

		}else {
			member = memberList.get(0);
			if(member.getXcxOpenId() == null) {
				member.setXcxOpenId(bindMobileDTO.getOpenId());
				memberMapper.updateByPrimaryKeySelective(member);
			}else {
				throw new RRException("该手机号已绑定其他微信账号");
			}
		}
		LoginVO loginVO = new LoginVO();
		loginVO.setToken(jwtUtils.generateToken(member.getId().toString()));
		loginVO.setHeadImage(member.getHeadImage());
		loginVO.setNickName(member.getNickName());
		loginVO.setMobile(member.getMoble());
		return R.ok().put("info", loginVO);
	}
	
	@Autowired
	PayUtil payUtil;
	/***
	 * 根据code获取小程序的openId
	 * @return
	 * @throws Exception 
	 */
	private String getOpenIdByCode(String code) throws Exception {
		GenerateOpenIdDTO generateOpenIdDTO = new GenerateOpenIdDTO();
		generateOpenIdDTO.setCode(code);
		Jscode2SessionVO xcxAccessOpenId = payUtil.xcxAccessOpenId(generateOpenIdDTO);
		String openid = xcxAccessOpenId.getOpenid();
		if(openid == null) {
			openid = "openId"+code;
		}
		return openid;
		 
	}
	
	/***
	 * 根据code获取小程序的openId
	 * @return
	 * @throws Exception 
	 */
	private Jscode2SessionVO getOpenIdAndSeesinKeyByCode(String code) throws Exception {
		GenerateOpenIdDTO generateOpenIdDTO = new GenerateOpenIdDTO();
		generateOpenIdDTO.setCode(code);
		Jscode2SessionVO jscode2SessionVO = payUtil.xcxAccessOpenId(generateOpenIdDTO);
		if(jscode2SessionVO != null && jscode2SessionVO.getOpenid()!=null && jscode2SessionVO.getSession_key()!=null) {
			return jscode2SessionVO;
		}else {
			throw new RRException("获取openId失败");
		}

		 
	}
	
	@Cacheable(value = "memberInfo")
	public R memberInfo(Long memberId) {
		MemberDO member = memberMapper.selectByPrimaryKey(memberId);
		return R.ok().put("info", member);
		
	}
	
	/**
	 * 用户身份选择
	 * @return
	 */
	public R chooseIdentify(String memberId,String identify) {
		valueOperations.set(Constants.IDENTIFY+memberId, identify);
		return R.ok();
	}
	
	public Integer RemainingNumbercallCount(Long memberId) {
		
		
		Example example = new Example(MemberDO.class);
		example.selectProperties("id","remainCallCount");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id",memberId);
		List<MemberDO> memberDOs = memberMapper.selectByExample(example);
		if(memberDOs != null && memberDOs.size()  == 1) {
			Integer remainCallCount = memberDOs.get(0).getRemainCallCount();
			if(remainCallCount == null) {
				remainCallCount = 0;
			}
			return remainCallCount;
		}
		
		return 0;
	}
	
	@Autowired
	FeedBackMapper feedBackMapper;
	
	public R feedBack(Long memberId,String content) {
		FeedBackDO feedBackDO = new FeedBackDO();
		feedBackDO.setContent(content);
		feedBackDO.setCreateTime(new Date());
		feedBackDO.setMemberId(memberId);
		feedBackMapper.insertSelective(feedBackDO);
		return R.ok();
	}
	
	public R getShareInfo(ShareInfoDTO shareInfoDTO,String memberId) throws Exception {
/*		String session_key = valueOperations.get(Constants.XCX_SESSION_KEY+memberId);
		shareInfoDTO.setSession_key(session_key);
		ShareInfoVO shareInfoVO = payUtil.DecryptShareInfo(shareInfoDTO);
		if(shareInfoVO!=null && shareInfoVO.getOpenGId() != null) {
			valueOperations.set(Constants.XCX_SHARE_INFO+memberId, shareInfoVO.getOpenGId());
		}*/
		MemberDO memberDO = memberMapper.selectByPrimaryKey(memberId);
		memberDO.setRemainCallCount(memberDO.getRemainCallCount()+1);
		memberMapper.updateByPrimaryKeySelective(memberDO);
		return R.ok();
	}
	
	/**
	 * 用户分享
	 * @param memberId
	 * @return
	 */
	public R toShare(Long memberId) {
		MemberDO memberDO = memberMapper.selectByPrimaryKey(memberId);
		memberDO.setRemainCallCount(memberDO.getRemainCallCount()+1);
		memberMapper.updateByPrimaryKeySelective(memberDO);
		return R.ok();
	}
	
	@Autowired
	TemplateMessageStragegy templateMessageStragegy;
	
	public R callAppeal(CallAppealDTO dto) throws Exception {
		MemberDO memberDO = memberMapper.selectByPrimaryKey(dto.getMemberId());
		memberDO.setRemainCallCount(memberDO.getRemainCallCount()+1);
		memberMapper.updateByPrimaryKeySelective(memberDO);
		valueOperations.set(Constants.FORM_ID+dto.getMemberId(), dto.getFormId());
		NotifyRequest notifyRequest = new NotifyRequest();
		WxComplainVO wxComplainVO = new WxComplainVO();
		wxComplainVO.setMemberId(dto.getMemberId());
		wxComplainVO.setComplainContent(dto.getAppealContent());
		wxComplainVO.setComplainResult("返还一次拨打机会");
		wxComplainVO.setRemark("工作人员会尽快核实该条信息，请您耐心等待，感谢您的举报");
		notifyRequest.setWxComplainVO(wxComplainVO);
		notifyRequest.setType(TemplateEnum.COMPLAIN.getValue());
		templateMessageStragegy.excute(notifyRequest);
		
		
		
		ReceiveComplainVO receiveComplainVO = new ReceiveComplainVO();
		return R.ok();
	}
	
}
