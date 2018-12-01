package com.yjk.app.service.impl;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.common.SelfIncreasingIdService;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.dao.DeviceRentOutInfoMapper;
import com.yjk.app.dto.DeviceRentOutInfoDTO;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.entity.DeviceRentOutInfoDO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.service.PutOnRentInfoService;
import com.yjk.app.util.R;
import com.yjk.app.vo.DeviceRentOutInfoVO;

@Service
public class DeviceRentOutInfoServiceImpl implements DeviceRentOutInfoService{
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	@Autowired
	SelfIncreasingIdService selfIncreasingIdService;
	@Autowired
	PutOnRentInfoService putOnRentInfoService;
	@Autowired
	DeviceMapper deviceMapper;
	/**
	 * 添加或者修改发布信息
	 * @param deviceRentOutInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R addOrUpdateRentOutInfo(DeviceRentOutInfoDTO deviceRentOutInfoDTO) throws Exception {
		DeviceRentOutInfoDO deviceRentOutInfoDO = new DeviceRentOutInfoDO();
		PropertyUtils.copyProperties(deviceRentOutInfoDO, deviceRentOutInfoDTO);
		
		deviceRentOutInfoDO.setUpdateTime(new Date());
		deviceRentOutInfoDO.setStatus(1);
		Long deviceRentOutInfoId = deviceRentOutInfoDO.getId();
		if(deviceRentOutInfoDO.getId() == null) {
			
			DeviceDO deviceDO = new DeviceDO();
			deviceDO.setCreateTime(new Date());
			deviceDO.setDeviceName(deviceRentOutInfoDTO.getDeviceName());
			deviceDO.setManufacture(deviceRentOutInfoDTO.getManufacture());
			deviceDO.setMemberId(deviceRentOutInfoDTO.getMemberId());
			deviceDO.setModeId(deviceRentOutInfoDTO.getModeId());
			deviceDO.setTwoStageModeId(deviceRentOutInfoDTO.getTwoStageModeId());
			deviceDO.setSpecId(deviceRentOutInfoDTO.getSpecId());
			deviceDO.setPics(deviceRentOutInfoDTO.getPics());
			deviceDO.setType(1);
			deviceMapper.insertSelective(deviceDO);
			deviceRentOutInfoDO.setCreateTime(new Date());
			deviceRentOutInfoDO.setId(selfIncreasingIdService.generateId());
			deviceRentOutInfoDO.setDeviceId(deviceDO.getId());
			deviceRentOutInfoMapper.insertSelective(deviceRentOutInfoDO);
			deviceRentOutInfoId = deviceRentOutInfoDO.getId();
		}else {
			DeviceDO deviceDO = deviceMapper.selectByPrimaryKey(deviceRentOutInfoDO.getId());
			PropertyUtils.copyProperties(deviceDO, deviceRentOutInfoDTO);
			deviceMapper.updateByPrimaryKeySelective(deviceDO);
			deviceRentOutInfoMapper.updateByPrimaryKeySelective(deviceRentOutInfoDO);
		}
		return R.ok().put("info", deviceRentOutInfoId);
	}
	
	/**
	 * 编辑发布出售信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public R editRentOutInfo(Long id) throws Exception {
		DeviceRentOutInfoDO deviceRentOutInfoDO = deviceRentOutInfoMapper.selectByPrimaryKey(id);
		if(deviceRentOutInfoDO == null) {
			throw new RRException("检索不到该条数据");
		}
		DeviceRentOutInfoVO deviceRentOutInfoVO = new DeviceRentOutInfoVO();
		BeanUtils.copyProperties(deviceRentOutInfoVO, deviceRentOutInfoDO);
		return R.ok().put("info", deviceRentOutInfoVO);
	}
	/**
	 * 下架发布出售信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public R cacelRentOutInfo(Long id) throws Exception {
		DeviceRentOutInfoDO droid = new DeviceRentOutInfoDO();
		droid.setId(id);
		droid.setStatus(1);
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(droid);
		putOnRentInfoService.rentInfoOut(id);
		return R.ok();
	}
	/**
	 * 上架发布出售信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public R releaseRentOutInfo(Long id) throws Exception {
		DeviceRentOutInfoDO droid = new DeviceRentOutInfoDO();
		droid.setId(id);
		droid.setStatus(2);
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(droid);
		putOnRentInfoService.putOnRent(id);
		return R.ok();
	}
	/**
	 * 删除发布出售信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public R delRentOutInfo(Long id) throws Exception {
		DeviceRentOutInfoDO droid = new DeviceRentOutInfoDO();
		droid.setId(id);
		droid.setStatus(3);
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(droid);
		putOnRentInfoService.rentInfoOut(id);
		return R.ok();
	}
}
