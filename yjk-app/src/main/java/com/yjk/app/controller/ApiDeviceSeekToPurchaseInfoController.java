package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.Login;
import com.yjk.app.dto.ReleasePurchaseInfoDTO;
import com.yjk.app.service.DeviceSeekToPurchaseInfoService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/app/device/seek")
public class ApiDeviceSeekToPurchaseInfoController {

	@Autowired
	private DeviceSeekToPurchaseInfoService deviceSeekToPurchaseInfoService;
	
	/**
	 * 发布求购信息接口
	 * @throws Exception 
	 */
	@Login
	@LimitedAccessByIP(key="releasePurchaseInfo",EachInterva=3)
	@RequestMapping("/releasePurchaseInfo")
	public R releasePurchaseInfo(ReleasePurchaseInfoDTO releasePurchaseInfoDTO,@RequestAttribute("memberId") Long memberId) throws Exception {
		Assert.isBlank(releasePurchaseInfoDTO.getAdress(), "求购地址不能为空");
		Assert.isBlank(releasePurchaseInfoDTO.getName(), "设备名称不能为空");
		Assert.isBlank(releasePurchaseInfoDTO.getUseDegree(), "成色要求不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getExpectedPrice(), "期望价格不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getLatitude(), "设备纬度不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getLongitude(), "设备经度不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getModeId(), "机型ID不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getTwoStageModeId(), "二级机型ID不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getSpecId(), "规格ID不能为空");
		releasePurchaseInfoDTO.setMemberId(memberId);
		return deviceSeekToPurchaseInfoService.releasePurchaseInfo(releasePurchaseInfoDTO);
	}
	
	/**
	 * 取消发布求购信息
	 * @param id
	 * @return
	 */
	@Login
	@RequestMapping("/cacelReleasePurchaseInfo")
	public R cacelReleasePurchaseInfo(Long id) {
		Assert.isNull(id, "id不能为空");
		return deviceSeekToPurchaseInfoService.cacelReleasePurchaseInfo(id);
	}
	
	/**
	 * 删除求购信息
	 * @param id
	 * @return
	 */
	@Login
	@RequestMapping("/delReleasePurchaseInfo")
	public R delReleasePurchaseInfo(Long id) {
		Assert.isNull(id, "id不能为空");
		return deviceSeekToPurchaseInfoService.delReleasePurchaseInfo(id);
	}
	
	/**
	 * 编辑求购信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping("/editReleasePurchaseInfo")
	public R eidtReleasePurchaseInfo(Long id) throws Exception {
		Assert.isNull(id, "id不能为空");
		return deviceSeekToPurchaseInfoService.eidtReleasePurchaseInfo(id);
	}
	
	
	/**
	 * 编辑求购信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping("/updateReleasePurchaseInfo")
	public R updateReleasePurchaseInfo(ReleasePurchaseInfoDTO releasePurchaseInfoDTO) throws Exception {
		Assert.isNull(releasePurchaseInfoDTO.getId(), "id不能为空");
		Assert.isBlank(releasePurchaseInfoDTO.getAdress(), "求购地址不能为空");
		Assert.isBlank(releasePurchaseInfoDTO.getName(), "设备名称不能为空");
		Assert.isBlank(releasePurchaseInfoDTO.getUseDegree(), "成色要求不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getExpectedPrice(), "期望价格不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getLatitude(), "设备纬度不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getLongitude(), "设备经度不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getModeId(), "机型ID不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getTwoStageModeId(), "二级机型ID不能为空");
		Assert.isNull(releasePurchaseInfoDTO.getSpecId(), "规格ID不能为空");
		return deviceSeekToPurchaseInfoService.updateReleasePurchaseInfo(releasePurchaseInfoDTO);
	}
}
