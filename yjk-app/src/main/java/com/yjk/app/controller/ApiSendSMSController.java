package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.service.SendSmsService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/app/send")
public class ApiSendSMSController {

	@Autowired
	SendSmsService sendSmsService;
	
	
	@LimitedAccessByIP(key="sms",EachInterva=2,timesOfDay=20)
	@RequestMapping("/sms")
	public R sendSms(String mobile,Integer smsType) {
		Assert.isBlank(mobile, "手机号不能为空");
		Assert.isNull(smsType, "发送类型不能为空！");
		R r = sendSmsService.sendSMS(mobile, smsType);
		return r;
	}
}
