package com.yjk.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.common.Constants;
import com.yjk.app.service.SendSmsService;
import com.yjk.app.util.R;



@Service
public class SendSmsServiceImpl implements SendSmsService{
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	

	public R sendSMS(String mobile,Integer smsType) {
		String captcha="";
		String template="";
		switch(smsType) {
		/**
		 * 1、会员注册
		 * 2、找回密码
		 * 3、绑定手机号
		 */
		  case 1 : captcha=Constants.ZC_KEY_CAPTCHA;template=Constants.ZC_SMS_TEMPLATE;break;
		  case 2 : captcha=Constants.ZH_KEY_CAPTCHA;template=Constants.ZH_SMS_TEMPLATE;break;
		  case 3 : captcha=Constants.BM_KEY_CAPTCHA;template=Constants.BM_SMS_TEMPLATE;break;
		  default : break;
		}
		String random = Random();
		valueOperations.set(captcha+"_"+mobile, random);
		return R.ok().put("verificationCode", random);

	}
	
	/******
	 * 生成6位随机验证码
	 * @return
	 */
	private String Random(){
		Random random = new Random();
		StringBuffer result = new StringBuffer("");
		for(int i=0; i<6; i++){
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
}
