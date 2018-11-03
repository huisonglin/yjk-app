package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.Login;
import com.yjk.app.dto.DeviceDTO;
import com.yjk.app.service.DeviceService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/device")
public class ApiDeviceController {

	@Autowired
	DeviceService deviceService;
	
	/**
	 * 添加设备接口
	 * @param addDeviceDTO
	 * @param memberId
	 * @return
	 */
	@Login
	@RequestMapping("/addDevice")
	public R addDevice(DeviceDTO addDeviceDTO,@RequestAttribute("memberId")Long memberId) {
		addDeviceDTO.setMemberId(memberId);
		Assert.isBlank(addDeviceDTO.getDeviceName(), "设备名称不能为空");
		Assert.isBlank(addDeviceDTO.getPics(), "设备图片不能为空");
		Assert.isNull(addDeviceDTO.getMemberId(), "用户ID不能为空");
		Assert.isNull(addDeviceDTO.getTwoStageModeId(), "二级机型ID不能为空");
		Assert.isNull(addDeviceDTO.getSpecId(), "规格ID不能为空");
		Assert.isNull(addDeviceDTO.getModeId(), "机型ID不能为空");

/*		Assert.isNull(addDeviceDTO.getNewMachinePrice(), "新机价格不能为空");
		Assert.isNull(addDeviceDTO.getManufacture(), "出厂日期不能为空");
		Assert.isNull(addDeviceDTO.getSelfEstimate(), "自我估值不能为空");
		Assert.isNull(addDeviceDTO.getWorkTime(), "工作时长不能为空");*/
		return deviceService.addDevice(addDeviceDTO) ;
	}
	
	/**
	 * 查看单个设备接口
	 * @param id
	 * @return
	 */
	@Login
	@RequestMapping("/editDevice")
	public R editDevice(Long id) {
		Assert.isNull(id, "设备Id不能为空");
		return deviceService.editDevice(id);
	}
	
	/**
	 * 更新设备接口
	 * @param deviceDTO
	 * @return
	 */
	@Login
	@RequestMapping("/updateDevice")
	public R updateDevice(DeviceDTO deviceDTO) {
		Assert.isNull(deviceDTO.getId(), "设备ID不能为空");
		Assert.isBlank(deviceDTO.getDeviceName(), "设备名称不能为空");
		Assert.isBlank(deviceDTO.getPics(), "设备图片不能为空");
		Assert.isNull(deviceDTO.getManufacture(), "出厂日期不能为空");
		Assert.isNull(deviceDTO.getModeId(), "机型ID不能为空");
		Assert.isNull(deviceDTO.getTwoStageModeId(), "二级机型ID不能为空");
		Assert.isNull(deviceDTO.getSpecId(), "规格ID不能为空");
		Assert.isNull(deviceDTO.getNewMachinePrice(), "新机价格不能为空");
		Assert.isNull(deviceDTO.getSelfEstimate(), "自我估值不能为空");
		Assert.isNull(deviceDTO.getWorkTime(), "工作时长不能为空");
		return deviceService.updateDevcie(deviceDTO);
	}
}
