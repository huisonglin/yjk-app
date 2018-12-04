package com.yjk.app.service;

import com.yjk.app.dto.DeviceDTO;
import com.yjk.app.util.R;

public interface DeviceService {

	/**
	 * 添加设备接口
	 */
	R addDevice(DeviceDTO addDeviceDTO);
	
	R editDevice(Long id);
	
	R updateDevcie(DeviceDTO editDeviceDTO);
	//我的设备列表
	R myList(Long memberId);

}
