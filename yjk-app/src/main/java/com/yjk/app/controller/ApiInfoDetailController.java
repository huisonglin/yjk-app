package com.yjk.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.service.InfoDetailService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;


@RestController
@RequestMapping("/info")
public class ApiInfoDetailController {

	@Autowired
	InfoDetailService infoDetailService;
	
	@RequestMapping("/detail")
	public R detail(Long id,Integer infoType) throws Exception {
		Assert.isNull(id, "id不能为空");
		Assert.isNull(infoType, "信息类型不能为空");
		Object infoDetail = infoDetailService.infoDetail(id, infoType);
		return R.ok().put("info", infoDetail);
	}
}
