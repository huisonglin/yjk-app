/*package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.service.RecognizeService;
import com.yjk.app.validator.Assert;
import com.yjk.app.vo.IdCardInfoVO;

@RestController
public class ApiRecognizeController {

	@Autowired
	RecognizeService recognizeService;
	
	
	@RequestMapping("IdCardInfoVO")
	@LimitedAccessByIP(EachInterva = 5, key = "recognize")
	public IdCardInfoVO info(String facadeIdCardUrl,String backIdCardUrl) throws Exception {
		Assert.isBlank(facadeIdCardUrl, "身份证正面地址不能为空");
		Assert.isBlank(backIdCardUrl, "身份证反面地址不能为空");
		IdCardInfoVO idCardInfo = recognizeService.getIdCardInfo(facadeIdCardUrl, backIdCardUrl);
		return idCardInfo;
	}
}
*/