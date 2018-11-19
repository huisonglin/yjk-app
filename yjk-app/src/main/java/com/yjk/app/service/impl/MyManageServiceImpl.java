package com.yjk.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.dao.DeviceRentalInNeedInfoMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.entity.DeviceRentalInNeedInfoDO;
import com.yjk.app.util.R;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MyManageServiceImpl {

	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	DeviceRentalInNeedInfoMapper deviceRentalInNeedInfoMapper;
	
	public R myDeviceList(Long memberId) {
		Example example =  new Example(DeviceDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("memberId",memberId);
		List<DeviceDO> deviceDOs = deviceMapper.selectByExample(example);
		return R.ok().put("info", deviceDOs);
	}
	
	public R myRentalInNeedInfoList(Long memberId) {
		Example example = new Example(DeviceRentalInNeedInfoDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("memberId",memberId);
		List<DeviceRentalInNeedInfoDO> DeviceRentalInNeedInfoDOs = deviceRentalInNeedInfoMapper.selectByExample(example);
		return R.ok().put("info", DeviceRentalInNeedInfoDOs);
	}
}
