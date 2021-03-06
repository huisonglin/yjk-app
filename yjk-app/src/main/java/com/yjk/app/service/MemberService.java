package com.yjk.app.service;

import com.yjk.app.dto.BindMobileDTO;
import com.yjk.app.dto.CallAppealDTO;
import com.yjk.app.dto.ForgotPasswordDTO;
import com.yjk.app.dto.LoginDTO;
import com.yjk.app.dto.ModifyPasswordDTO;
import com.yjk.app.dto.PhoneNumberDTO;
import com.yjk.app.dto.RegisterDTO;
import com.yjk.app.dto.ShareInfoDTO;
import com.yjk.app.util.R;
import com.yjk.common.entity.MemberDO;

public interface MemberService {

	 MemberDO selectByPrimaryKey(Long id);
	 
	 R login(LoginDTO loginDTO);
	 
	 R register(RegisterDTO registerDTO);
	 
	 R modifyPassword(ModifyPasswordDTO modifyPasswordDTO);
	 
	 R forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
	 
	 R editMemberInfo(MemberDO member);
	 
	 R loginByXcx(String code,String iv,String encryptedData) throws Exception;
	 
	 R loginByXcxNeedMobile(String code) throws Exception;
	 
	 R bindMobile(BindMobileDTO bindMobileDTO);
	 
	 R memberInfo(Long memberId);
	 
	 R bindMobileByXcx(PhoneNumberDTO phoneNumberDTO,Long userId) throws Exception;
	 
	 R chooseIdentify(String memberId,String identify);
	 
	 Integer RemainingNumbercallCount(Long memberId);
	 
	 R feedBack(Long memberId,String content);
	 
	 R getShareInfo(ShareInfoDTO shareInfoDTO,String memberId)throws Exception ;
	 
	 R toShare(Long memberId);
	 
	 R callAppeal(CallAppealDTO dto) throws Exception;
	 
	 R loginByWxXcx(String code) throws Exception;
	 
	 R obtainUserInfo(String iv,String encryptedData,Long memberId) throws Exception;
}
