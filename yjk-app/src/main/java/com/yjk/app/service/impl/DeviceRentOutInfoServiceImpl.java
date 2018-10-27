package com.yjk.app.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.DeviceRentOutInfoMapper;
import com.yjk.app.dto.DeviceRentOutInfoDTO;
import com.yjk.app.entity.DeviceRentOutInfoDO;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.util.R;
import com.yjk.app.vo.DeviceRentOutInfoVO;

@Service
public class DeviceRentOutInfoServiceImpl implements DeviceRentOutInfoService{
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	
	/**
	 * 添加或者修改发布信息
	 * @param deviceRentOutInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R addOrUpdateRentOutInfo(DeviceRentOutInfoDTO deviceRentOutInfoDTO) throws Exception {
		DeviceRentOutInfoDO deviceRentOutInfoDO = new DeviceRentOutInfoDO();
		PropertyUtils.copyProperties(deviceRentOutInfoDO, deviceRentOutInfoDTO);
		deviceRentOutInfoDO.setCreateTime(new Date());
		deviceRentOutInfoDO.setUpdateTime(new Date());
		deviceRentOutInfoDO.setStatus(1);
		Long deviceId = deviceRentOutInfoDO.getId();
		if(deviceRentOutInfoDO.getId() == null) {
			deviceRentOutInfoMapper.insertSelective(deviceRentOutInfoDO);
			deviceId = deviceRentOutInfoDO.getId();
		}else {
			deviceRentOutInfoMapper.updateByPrimaryKeySelective(deviceRentOutInfoDO);
		}
		return R.ok().put("info", deviceId);
	}
	
	/**
	 * 编辑发布出售信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public R editRentOutInfo(Long id) throws Exception {
		DeviceRentOutInfoDO deviceRentOutInfoDO = deviceRentOutInfoMapper.selectByPrimaryKey(id);
		DeviceRentOutInfoVO deviceRentOutInfoVO = new DeviceRentOutInfoVO();
		BeanUtils.copyProperties(deviceRentOutInfoVO, deviceRentOutInfoDO);
		return R.ok().put("info", deviceRentOutInfoVO);
	}
	/**
	 * 下架发布出售信息
	 * @param id
	 * @return
	 */
	public R cacelRentOutInfo(Long id) {
		DeviceRentOutInfoDO droid = new DeviceRentOutInfoDO();
		droid.setId(id);
		droid.setStatus(1);
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(droid);
		return R.ok();
	}
	/**
	 * 上架发布出售信息
	 * @param id
	 * @return
	 */
	public R releaseRentOutInfo(Long id) {
		DeviceRentOutInfoDO droid = new DeviceRentOutInfoDO();
		droid.setId(id);
		droid.setStatus(2);
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(droid);
		return R.ok();
	}
	/**
	 * 删除发布出售信息
	 * @param id
	 * @return
	 */
	public R delRentOutInfo(Long id) {
		DeviceRentOutInfoDO droid = new DeviceRentOutInfoDO();
		droid.setId(id);
		droid.setStatus(3);
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(droid);
		return R.ok();
	}
}
