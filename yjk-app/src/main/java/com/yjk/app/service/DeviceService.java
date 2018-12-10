package com.yjk.app.service;

import java.util.List;

import com.yjk.app.dto.DeviceDTO;
import com.yjk.app.dto.RefreshPositionAndPublishDTO;
import com.yjk.app.util.R;
import com.yjk.app.vo.MyListVO;

public interface DeviceService {

	/**
	 * 添加设备接口
	 */
	R addDevice(DeviceDTO addDeviceDTO);
	
	R editDevice(Long id);
	
	R updateDevcie(DeviceDTO editDeviceDTO);
	//我的设备列表
	List<MyListVO> myList(Long memberId);
	
	public R refreshPositionAndPublish(RefreshPositionAndPublishDTO dto) throws Exception ;

}
