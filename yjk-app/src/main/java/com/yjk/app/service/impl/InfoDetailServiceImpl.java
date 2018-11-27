package com.yjk.app.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.dao.DeviceRentOutInfoMapper;
import com.yjk.app.dao.DeviceRentalInNeedInfoMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.entity.DeviceRentOutInfoDO;
import com.yjk.app.entity.DeviceRentalInNeedInfoDO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.service.DeviceRentalInNeedInfoService;
import com.yjk.app.service.InfoDetailService;
import com.yjk.app.util.R;
import com.yjk.app.vo.DeviceRentOutInfoDetailVO;
import com.yjk.app.vo.DeviceRentalInNeedInfoDetailVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class InfoDetailServiceImpl implements InfoDetailService{

	@Autowired
	DeviceRentalInNeedInfoMapper deviceRentalInNeedInfoMapper;
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	@Autowired
	DeviceMapper deviceMapper;
	
	//@Cacheable(value = "infoDetail", key="#id")
	public R infoDetail(Long id,Integer infoType) throws Exception {
		System.out.println("走了数据库....");
		if(PublishingTypeEnum.RENT_OUT.getValue() == infoType) { //发布出租
			
			DeviceRentOutInfoDO deviceRentOutInfoDO = deviceRentOutInfoMapper.selectByPrimaryKey(id);
			if(deviceRentOutInfoDO == null) {
				throw new RRException("没有找到该条数据");
			}
			DeviceDO deviceDO = deviceMapper.selectByPrimaryKey(deviceRentOutInfoDO.getDeviceId());
			DeviceRentOutInfoDetailVO deviceRentOutInfoDetailVO = new DeviceRentOutInfoDetailVO();
			deviceRentOutInfoDetailVO.setId(deviceRentOutInfoDO.getId());
			deviceRentOutInfoDetailVO.setPics(deviceDO.getPics());
			deviceRentOutInfoDetailVO.setAddress(deviceRentOutInfoDO.getAddress());
			deviceRentOutInfoDetailVO.setAddressDetail(deviceRentOutInfoDO.getAddressDetail());
			deviceRentOutInfoDetailVO.setDeviceName(deviceDO.getDeviceName());
			deviceRentOutInfoDetailVO.setContactMobile(deviceRentOutInfoDO.getContactMobile());
			deviceRentOutInfoDetailVO.setContactName(deviceRentOutInfoDO.getContactName());
			deviceRentOutInfoDetailVO.setManufacture(deviceDO.getManufacture());
			deviceRentOutInfoDetailVO.setRemark(deviceRentOutInfoDO.getRemark());
			return R.ok().put("info", deviceRentOutInfoDetailVO);
		}else if(PublishingTypeEnum.RENTAL_IN_NEED.getValue() == infoType) {//发布求租
			DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = deviceRentalInNeedInfoMapper.selectByPrimaryKey(id);
			if(deviceRentalInNeedInfoDO == null) {
				throw new RRException("没有找到该条数据");
			}
			DeviceRentalInNeedInfoDetailVO deviceRentalInNeedInfoDetailVO = new DeviceRentalInNeedInfoDetailVO();
			PropertyUtils.copyProperties(deviceRentalInNeedInfoDetailVO, deviceRentalInNeedInfoDO);
			deviceRentalInNeedInfoDetailVO.setPics(deviceRentalInNeedInfoDO.getPics());
			return R.ok().put("info", deviceRentalInNeedInfoDetailVO);
			
		}
		return null;

	}
}
