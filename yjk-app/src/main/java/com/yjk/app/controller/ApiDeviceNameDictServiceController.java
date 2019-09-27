package com.yjk.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.service.DeviceNameDictService;
import com.yjk.app.service.impl.DeviceNameDictServiceImpl;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;


@RestController
@RequestMapping("/app/deviceName/dict")
public class ApiDeviceNameDictServiceController {

	@Autowired
	DeviceNameDictServiceImpl deviceNameDictService;
	
	@RequestMapping("/getModelList")
	public R getModelList() {
		return deviceNameDictService.getModelList();
	}
	
	@RequestMapping("/getTwoStageModelByModelId")
	public R getTwoStageModelByModelId(Long modelId) {
		Assert.isNull(modelId, "机型ID不能为空");
		return deviceNameDictService.getTwoStageModelByModelId(modelId);
	}
	
	@RequestMapping("/getSpecByTwoStageModelId")
	public R getSpecByTwoStageModelId(Long twoStageModelId) {
		Assert.isNull(twoStageModelId, "二级机型ID不能为空");
		return deviceNameDictService.getSpecByTwoStageModelId(twoStageModelId);
	}
	
	@RequestMapping("/getSubTypes")
	public R getSubTypes(Long modelId) {
		Assert.isNull(modelId, "机型ID不能为空");
		return deviceNameDictService.getSubTypes(modelId);
	}
	
}
