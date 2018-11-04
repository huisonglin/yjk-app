package com.yjk.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.service.DeviceRentalInNeedInfoService;
import com.yjk.app.service.InfoDetailService;
import com.yjk.app.util.R;

@Service
public class InfoDetailServiceImpl implements InfoDetailService{

	@Autowired
	DeviceRentalInNeedInfoService deviceRentalInNeedInfoService;
	@Autowired
	DeviceRentOutInfoService deviceRentOutInfoService;
	
	@Cacheable(value = "infoDetail", key="#id")
	public R infoDetail(Long id,Integer infoType) throws Exception {
		System.out.println("走了数据库....");
		if(PublishingTypeEnum.RENT_OUT.getValue() == infoType) { //发布出租
			return deviceRentOutInfoService.editRentOutInfo(id);
		}else if(PublishingTypeEnum.RENTAL_IN_NEED.getValue() == infoType) {//发布求租
			return deviceRentalInNeedInfoService.editNeedInfo(id);
		}
		return null;

	}
}
