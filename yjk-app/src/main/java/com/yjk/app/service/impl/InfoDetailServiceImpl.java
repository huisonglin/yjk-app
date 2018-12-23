package com.yjk.app.service.impl;


import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yjk.app.common.Constants;
import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.InfoDetailService;
import com.yjk.app.service.ValueUnitCorrelationService;
import com.yjk.app.util.DatetimeUtil;
import com.yjk.app.vo.DeviceRentOutInfoDetailVO;
import com.yjk.app.vo.DeviceRentalInNeedInfoDetailVO;
import com.yjk.common.dao.DeviceMapper;
import com.yjk.common.dao.DeviceRentOutInfoMapper;
import com.yjk.common.dao.DeviceRentalInNeedInfoMapper;
import com.yjk.common.entity.DeviceDO;
import com.yjk.common.entity.DeviceRentOutInfoDO;
import com.yjk.common.entity.DeviceRentalInNeedInfoDO;

@Service
public class InfoDetailServiceImpl implements InfoDetailService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	DeviceRentalInNeedInfoMapper deviceRentalInNeedInfoMapper;
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	ValueUnitCorrelationService valueUnitCorrelationService;
	
	/**
	 * 列表详情页处理
	 */
	@Cacheable(value = "infoDetail", key="#id")
	public Object infoDetail(Long id,Integer infoType) throws Exception {
		logger.info("走了数据库....");
		if(PublishingTypeEnum.RENT_OUT.getValue() == infoType) { //发布出租
			
			DeviceRentOutInfoDO deviceRentOutInfoDO = deviceRentOutInfoMapper.selectByPrimaryKey(id);
			if(deviceRentOutInfoDO == null) {
				return null;
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
			deviceRentOutInfoDetailVO.setMemberId(deviceRentOutInfoDO.getMemberId());
			deviceRentOutInfoDetailVO.setPrice(valueUnitCorrelationService.showPriceName(id));
			return deviceRentOutInfoDetailVO;
		}else if(PublishingTypeEnum.RENTAL_IN_NEED.getValue() == infoType) {//发布求租
			DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = deviceRentalInNeedInfoMapper.selectByPrimaryKey(id);
			if(deviceRentalInNeedInfoDO == null) {
				throw new RRException("没有找到该条数据");
			}
			DeviceRentalInNeedInfoDetailVO deviceRentalInNeedInfoDetailVO = new DeviceRentalInNeedInfoDetailVO();
			PropertyUtils.copyProperties(deviceRentalInNeedInfoDetailVO, deviceRentalInNeedInfoDO);
			deviceRentalInNeedInfoDetailVO.setPics(deviceRentalInNeedInfoDO.getPics());
			return deviceRentalInNeedInfoDetailVO;
			
		}
		return null;
		
	}
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	public void dealDetail(Object infoDetail) {
		if(infoDetail instanceof DeviceRentOutInfoDetailVO) {
			DeviceRentOutInfoDetailVO d = (DeviceRentOutInfoDetailVO)infoDetail;
			String lastTime = valueOperations.get(Constants.LAST_LOGIN_TIME+d.getMemberId());
			if(lastTime != null) {
				Date date = new Date(Long.valueOf(lastTime));
				String showTime = DatetimeUtil.showTime(date, null);
				d.setActive(showTime+"活跃");
			}
		}
		if(infoDetail instanceof DeviceRentalInNeedInfoDetailVO) {
			DeviceRentalInNeedInfoDetailVO d = (DeviceRentalInNeedInfoDetailVO)infoDetail;
			String lastTime = valueOperations.get(Constants.LAST_LOGIN_TIME+d.getMemberId());
			if(lastTime != null) {
				Date date = new Date(Long.valueOf(lastTime));
				String showTime = DatetimeUtil.showTime(date, null);
				d.setActive(showTime+"活跃");
			}
		}
	}



}
