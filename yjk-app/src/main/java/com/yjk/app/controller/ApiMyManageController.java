package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.Login;
import com.yjk.app.service.DeviceCollectionService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;
import com.yjk.app.vo.CollectionOptionsVO;

@RestController
@RequestMapping("/app/myManage")
public class ApiMyManageController {

	@Autowired
	DeviceCollectionService deviceCollectionService;
	
	@Login
	@RequestMapping("/collectionOptions")
	public R collection(@RequestAttribute("memberId")Long memberId,CollectionOptionsVO collectionOptionsVO ) {
		collectionOptionsVO.setMemberId(memberId);
		if(collectionOptionsVO.getCollectionId() == null) {
			Assert.isNull(collectionOptionsVO.getInfoId(), "信息ID不能为空");
			Assert.isNull(collectionOptionsVO.getInfoId(), "信息类型不能为空");
		}
		return deviceCollectionService.collectionOptions(collectionOptionsVO);
	}
	
	@Login
	public void myCollection(@RequestAttribute("memberId")Long userId) {
		
	}
}
