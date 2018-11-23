package com.yjk.app.util;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.yjk.app.common.Constants;
import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.AccessTokenDTO;
import com.yjk.app.dto.DecryptUserInfoDTO;
import com.yjk.app.dto.GenerateOpenIdDTO;
import com.yjk.app.dto.PhoneNumberDTO;
import com.yjk.app.dto.XcxUnifiedorderDTO;
import com.yjk.app.exception.RRException;
import com.yjk.app.vo.AccessTokenVO;
import com.yjk.app.vo.DecryptUserInfoVO;
import com.yjk.app.vo.Jscode2SessionVO;
import com.yjk.app.vo.PhoneNumberVO;
import com.yjk.app.vo.UnifiedorderAttachVO;
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
		XcxPayPramsVO xcxPayPramsVO = (XcxPayPramsVO)dealResult(result,XcxPayPramsVO.class);
		//获取预支付ID,供发送信息模板
		valueOperations.set(Constants.PREPAY_ID+xcxUnifiedorderDTO.getOut_trade_no(), xcxPayPramsVO.get_package().replaceAll("prepay_id=", ""));
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
		return (Jscode2SessionVO)dealResult(result,Jscode2SessionVO.class);
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
		return (DecryptUserInfoVO)dealResult(result,DecryptUserInfoVO.class);
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
			AccessTokenVO dealResult = (AccessTokenVO)dealResult(result,AccessTokenVO.class);
			accessToken = dealResult.getAccess_token();
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
		return (PhoneNumberVO)dealResult(result,PhoneNumberVO.class);
	}

	
	
	public static  <T> Object dealResult(HttpClientResult result,Class<T> clazz)
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
