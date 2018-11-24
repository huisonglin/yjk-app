package com.yjk.app.util;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.util.Json;
import com.yjk.app.common.Constants;
import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.AccessTokenDTO;
import com.yjk.app.dto.DecryptUserInfoDTO;
import com.yjk.app.dto.GenerateOpenIdDTO;
import com.yjk.app.dto.PhoneNumberDTO;
import com.yjk.app.dto.WeiXinRefundDTO;
import com.yjk.app.dto.XcxUnifiedorderDTO;
import com.yjk.app.exception.RRException;
import com.yjk.app.vo.AccessTokenVO;
import com.yjk.app.vo.DecryptUserInfoVO;
import com.yjk.app.vo.Jscode2SessionVO;
import com.yjk.app.vo.PhoneNumberVO;
import com.yjk.app.vo.UnifiedorderAttachVO;
import com.yjk.app.vo.WeiXinRefundVO;
import com.yjk.app.vo.XcxPayPramsVO;

@Component
public class PayUtil {

	@Autowired
	WeiXinConfig weiXinConfig;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	/**
	 * 生成小程序支付参数
	 * @param xcxUnifiedorderDTO
	 * @return
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IntrospectionException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public R xcxAccessPayParam(XcxUnifiedorderDTO xcxUnifiedorderDTO) throws Exception {
		xcxUnifiedorderDTO.setTrade_type(weiXinConfig.getTradeType());
		xcxUnifiedorderDTO.setAppid(weiXinConfig.getXcxAppId());
		xcxUnifiedorderDTO.setMch_id(weiXinConfig.getMechId());
		xcxUnifiedorderDTO.setAttach(JSON.toJSONString(new UnifiedorderAttachVO(weiXinConfig.getApikey(), weiXinConfig.getXcxNotifyQueueName(),xcxUnifiedorderDTO.getBody(),xcxUnifiedorderDTO.getDetail())));
		xcxUnifiedorderDTO.setKey(weiXinConfig.getApikey());
		xcxUnifiedorderDTO.setNotify_url(weiXinConfig.getXcxAccessPayParamUrl()+"/notify");
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getXcxAccessPayParamUrl(),BeanUtils.describe(xcxUnifiedorderDTO));
		XcxPayPramsVO xcxPayPramsVO = dealResult(result,XcxPayPramsVO.class);
		//获取预支付ID,供发送信息模板
		valueOperations.set(Constants.PREPAY_ID+xcxUnifiedorderDTO.getOut_trade_no(), xcxPayPramsVO.get_package().replaceAll("prepay_id=", ""));
		valueOperations.set(Constants.XCX_PAY_PRAMS+xcxUnifiedorderDTO.getOut_trade_no(), new Gson().toJson(xcxPayPramsVO), 7200, TimeUnit.SECONDS); //有效期两个小时
		return R.ok().put("data", xcxPayPramsVO);
	}
	
	/**
	 * 获取openId
	 * @param generateOpenIdDTO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Jscode2SessionVO xcxAccessOpenId(GenerateOpenIdDTO generateOpenIdDTO)throws Exception {
		generateOpenIdDTO.setAppId(weiXinConfig.getXcxAppId());
		generateOpenIdDTO.setAppSecret(weiXinConfig.getXcxAppSecret());
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getXcxAccessOpenIdUrl(), BeanUtils.describe(generateOpenIdDTO));
		return dealResult(result,Jscode2SessionVO.class);
	}
	
	/**
	 * 微信退款  
	 * @param weiXinRefundDTO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public WeiXinRefundVO refundByWeiXin(WeiXinRefundDTO weiXinRefundDTO) throws Exception {
		
		weiXinRefundDTO.setApiKey("1145B1AFA2994480808B42793E486A81");
		weiXinRefundDTO.setAppid("wxbcac66730a4136dc");
		weiXinRefundDTO.setMch_id("1498445182");
		
/*		weiXinRefundDTO.setApiKey(weiXinConfig.getApikey());
		weiXinRefundDTO.setAppid(weiXinConfig.getXcxAppId());
		weiXinRefundDTO.setMch_id(weiXinConfig.getMechId());*/
		
		weiXinRefundDTO.setCertUrl(weiXinConfig.getCertUrl());
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getRefundUrl(), BeanUtils.describe(weiXinRefundDTO));
		return dealResult(result,WeiXinRefundVO.class);
	}
	
	/**
	 * 解密用户信息
	 * @param decryptUserInfoDTO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public DecryptUserInfoVO DecryptUserInfo(DecryptUserInfoDTO decryptUserInfoDTO)throws Exception {
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getXcxDecryptUserInfoUrl(), BeanUtils.describe(decryptUserInfoDTO));
		return dealResult(result,DecryptUserInfoVO.class);
	}
	
	/**
	 * 获取accessToken
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String xcxAccessToken() throws Exception {
		String accessToken = valueOperations.get(weiXinConfig.getAccessToken());
		if(accessToken == null) {
			AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
			accessTokenDTO.setAppid(weiXinConfig.getXcxAppId());
			accessTokenDTO.setSecret(weiXinConfig.getXcxAppSecret());
			HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getAccessTokendUrl(), BeanUtils.describe(accessTokenDTO));
			AccessTokenVO dealResult = dealResult(result,AccessTokenVO.class);
			accessToken = dealResult.getAccess_token();
			if(accessToken == null) {
				throw new RRException("获取accessToken失败,请检查配置！");
			}
			valueOperations.set(weiXinConfig.getAccessToken(), accessToken,Long.valueOf(dealResult.getExpires_in()),TimeUnit.SECONDS);
		}
		return accessToken;

	}
	/**
	 * 解密获取用户手机号
	 * @param phoneNumberDTO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PhoneNumberVO decryptedPhoneNumber(PhoneNumberDTO phoneNumberDTO)throws Exception {
		HttpClientResult result = HttpClientUtils.doGet(weiXinConfig.getXcxDecryptedPhoneNumber(), BeanUtils.describe(phoneNumberDTO));
		return dealResult(result,PhoneNumberVO.class);
	}

	
	
	public static  <T>  T dealResult(HttpClientResult result,Class<T> clazz)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		if(result.getCode() == 200) {
			String content = result.getContent();
			R r = JSONUtil.parse(content, R.class);
			Object msg = r.get(R.CODE);
			if(msg.equals(R.SUCCESS)) {
				Object rsg = r.get("data");
				if(rsg != null) {
					T data = JSONUtil.parse(JSON.toJSONString(rsg), clazz);
					return data;
				}else {
					throw new RRException("请求异常");
				}
			}else {
				throw new RRException(r.get(R.MSG).toString());
			}
		}else {
			throw new RRException("代理服务器请求失败");
		}
	}
}
