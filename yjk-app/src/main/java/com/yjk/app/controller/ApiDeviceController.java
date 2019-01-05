package com.yjk.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.Login;
import com.yjk.app.dto.DeviceDTO;
import com.yjk.app.dto.MyListDTO;
import com.yjk.app.dto.RefreshPositionAndPublishDTO;
import com.yjk.app.service.DeviceService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/app/device")
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
	/**
	 * 我的发布列表接口
	 * @param memberId
	 * @return
	 */
	@Login
	@RequestMapping("/myList")
	public R myList(@RequestAttribute("memberId")Long memberId,MyListDTO dto) {
		dto.setMemberId(memberId);
		return R.ok().put("info", deviceService.myList(dto));
	}
	
	@Login
	@RequestMapping("/deviceList")
	public R deviceList(@RequestAttribute("memberId")Long memberId,MyListDTO dto){
		Assert.isNull(dto.getPageNo(), "页码不能为空");
		dto.setMemberId(memberId);
		return deviceService.deviceList(dto);
	}
	
	/**
	 * 刷新位置并发布
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping("/refreshPositionAndPublish")
	public R refreshPositionAndPublish(RefreshPositionAndPublishDTO dto) throws Exception {
		Assert.isNull(dto.getInfoId(), "信息ID不能为空");
		Assert.isNull(dto.getInfoType(), "信息类型不能为空");
		Assert.isNull(dto.getAddress(), "地址不能为空");
		Assert.isNull(dto.getAddressDetail(), "详细地址不能为空");
		Assert.isNull(dto.getLatitude(), "纬度不能为空");
		Assert.isNull(dto.getLongitude(), "经度不能为空");
		return deviceService.refreshPositionAndPublish(dto);
	}
}
