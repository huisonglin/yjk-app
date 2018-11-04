package com.yjk.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.Login;
import com.yjk.app.dto.DeviceRentalInNeedInfoDTO;
import com.yjk.app.service.DeviceRentalInNeedInfoService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

/**
 * 发布工程
 * @author huisonglin
 *
 */
@RestController
@RequestMapping("/rentalInNeedInfo")
public class DeviceRentalInNeedInfoController {

	@Autowired
	DeviceRentalInNeedInfoService deviceRentalInNeedInfoService;
	
	/**
	 * 添加或者修改发布信息
	 * @param deviceRentalInNeedInfoDTO
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping("/addOrUpdateNeedInfo")
	@LimitedAccessByIP(key = "addOrUpdateNeedInfo" ,EachInterva=5)
	public R addOrUpdateNeedInfo(DeviceRentalInNeedInfoDTO deviceRentalInNeedInfoDTO,@RequestAttribute("memberId") Long memberId) throws Exception{
		Assert.isBlank(deviceRentalInNeedInfoDTO.getAdress(), "地址不能为空");
		Assert.isBlank(deviceRentalInNeedInfoDTO.getName(), "求租设备名称不能为空");
		Assert.isNull(deviceRentalInNeedInfoDTO.getLatitude(), "纬度不能为空");
		Assert.isNull(deviceRentalInNeedInfoDTO.getLongitude(), "经度不能为空");
		Assert.isNull(deviceRentalInNeedInfoDTO.getPics(), "图片不能为空");
		Assert.isNull(deviceRentalInNeedInfoDTO.getContactMobile(), "联系方式不能为空");
		Assert.isNull(deviceRentalInNeedInfoDTO.getModeId(), "机型Id不能为空");
		Assert.isNull(deviceRentalInNeedInfoDTO.getTwoStageModeId(), "二级机型Id不能为空");
		deviceRentalInNeedInfoDTO.setMemberId(memberId);
		return deviceRentalInNeedInfoService.addOrUpdateNeedInfo(deviceRentalInNeedInfoDTO);
	}
	
	/**
	 * 编辑发布信息
	 * @param id
	 */
	@Login
	@RequestMapping("/editNeedInfo")
	public R editNeedInfo(Long id) throws Exception{
		Assert.isNull(id, "id不能为空");
		return deviceRentalInNeedInfoService.editNeedInfo(id);
	}
	
	/**
	 * 下架发布信息
	 * @return
	 */
	@Login
	@RequestMapping("/cacelNeedInfo")
	public R cacelNeedInfo(Long id) throws Exception{
		Assert.isNull(id, "id不能为空");
		return deviceRentalInNeedInfoService.cacelNeedInfo(id);
	}
	
	/**
	 * 上架发布信息
	 * @return
	 */
	@Login
	@RequestMapping("/releaseNeedInfo")
	public R releaseNeedInfo(Long id) throws Exception{
		Assert.isNull(id, "id不能为空");
		return deviceRentalInNeedInfoService.releaseNeedInfo(id);
	}
	
	/**
	 * 删除发布信息
	 * @param id
	 * @return
	 */
	@Login
	@RequestMapping("/deleteNeedInfo")
	public R deleteNeedInfo(Long id) throws Exception{
		Assert.isNull(id, "id不能为空");
		return deviceRentalInNeedInfoService.deleteNeedInfo(id);
	}
}
