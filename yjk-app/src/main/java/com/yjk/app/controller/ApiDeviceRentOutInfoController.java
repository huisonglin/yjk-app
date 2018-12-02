package com.yjk.app.controller;


import java.math.BigDecimal;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yjk.app.annotation.LimitedAccessByIP;
import com.yjk.app.annotation.Login;
import com.yjk.app.dto.DeviceRentOutInfoDTO;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
@RequestMapping("/deviceRentOut")
public class ApiDeviceRentOutInfoController {

	@Autowired
	DeviceRentOutInfoService deviceRentOutInfoService;
	/**
	 * 添加或者修改发布信息
	 * @param deviceRentOutInfoDTO
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping("/addOrUpdateRentOutInfo")
	@LimitedAccessByIP(key = "addOrUpdateRentOutInfo" ,EachInterva=5)
	public R addOrUpdateRentOutInfo(DeviceRentOutInfoDTO deviceRentOutInfoDTO,@RequestAttribute("memberId")Long memberId) throws Exception {
		
		Assert.isBlank(deviceRentOutInfoDTO.getPics(), "图片不能为空");
		Assert.isBlank(deviceRentOutInfoDTO.getDeviceName(), "机械名称不能为空");
		Assert.isNull(deviceRentOutInfoDTO.getModeId(), "一级机型Id不能为空");
		Assert.isNull(deviceRentOutInfoDTO.getTwoStageModeId(), "二级机型Id不能为空");
		Assert.isNull(deviceRentOutInfoDTO.getSpecId(), "规格Id不能为空");
		Assert.isBlank(deviceRentOutInfoDTO.getAddress(), "地址不能为空");
		Assert.isBlank(deviceRentOutInfoDTO.getAddressDetail(), "详细地址不能为空");
		Assert.isNull(deviceRentOutInfoDTO.getLatitude(), "纬度不能为空");
		Assert.isNull(deviceRentOutInfoDTO.getLongitude(), "经度不能为空");
		Assert.isNull(deviceRentOutInfoDTO.getContactMobile(), "联系电话");
		deviceRentOutInfoDTO.setMemberId(memberId);
		return deviceRentOutInfoService.addOrUpdateRentOutInfo(deviceRentOutInfoDTO);
	}
	
	/**
	 * 编辑发布出售信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping("/editRentOutInfo")
	public R editRentOutInfo(Long id) throws Exception {
		Assert.isNull(id, "id不能为空");
		return deviceRentOutInfoService.editRentOutInfo(id);
	}
	/**
	 * 下架发布出售信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Login
	@RequestMapping("/cacelRentOutInfo")
	public R cacelRentOutInfo(Long id) throws Exception {
		Assert.isNull(id, "id不能为空");
		return deviceRentOutInfoService.cacelRentOutInfo(id);
	}
	/**
	 * 上架发布出售信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Login
	@RequestMapping("/releaseRentOutInfo")
	public R releaseRentOutInfo(Long id) throws Exception {
		Assert.isNull(id, "id不能为空");
		return deviceRentOutInfoService.releaseRentOutInfo(id);
	}
	/**
	 * 删除发布出售信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Login
	@RequestMapping("/delRentOutInfo")
	public R delRentOutInfo(Long id) throws Exception {
		Assert.isNull(id, "id不能为空");
		return deviceRentOutInfoService.delRentOutInfo(id);
	}
}
