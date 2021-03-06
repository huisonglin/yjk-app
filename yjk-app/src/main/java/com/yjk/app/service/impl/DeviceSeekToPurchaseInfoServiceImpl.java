package com.yjk.app.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.common.SelfIncreasingIdService;
import com.yjk.app.dto.ReleasePurchaseInfoDTO;
import com.yjk.app.service.DeviceSeekToPurchaseInfoService;
import com.yjk.app.util.R;
import com.yjk.app.vo.ReleasePurchaseInfoVO;
import com.yjk.common.dao.DeviceSeekToPurchaseInfoMapper;
import com.yjk.common.entity.DeviceSeekToPurchaseInfoDO;

@Service
public class DeviceSeekToPurchaseInfoServiceImpl implements DeviceSeekToPurchaseInfoService{

	@Autowired
	private DeviceSeekToPurchaseInfoMapper deviceSeekToPurchaseInfoMapper;
	
	@Autowired
	SelfIncreasingIdService selfIncreasingIdService;
	
	/**
	 * 发布求购信息
	 * @param releasePurchaseInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R releasePurchaseInfo(ReleasePurchaseInfoDTO releasePurchaseInfoDTO) throws Exception {
		DeviceSeekToPurchaseInfoDO deviceSeekToPurchaseInfoDO = new DeviceSeekToPurchaseInfoDO();
		BeanUtils.copyProperties(deviceSeekToPurchaseInfoDO, releasePurchaseInfoDTO);
		deviceSeekToPurchaseInfoDO.setStatus(1);
		deviceSeekToPurchaseInfoDO.setCreateTime(new Date());
		deviceSeekToPurchaseInfoDO.setUpdateTime(new Date());
		deviceSeekToPurchaseInfoDO.setId(selfIncreasingIdService.generateId());
		deviceSeekToPurchaseInfoMapper.insertSelective(deviceSeekToPurchaseInfoDO);
		return R.ok();
	}
	

	/**
	 * 取消发布求购信息
	 * @param id
	 * @return
	 */
	public R cacelReleasePurchaseInfo(Long id) {
		DeviceSeekToPurchaseInfoDO deviceSeekToPurchaseInfoDO = new DeviceSeekToPurchaseInfoDO();
		deviceSeekToPurchaseInfoDO.setId(id);
		deviceSeekToPurchaseInfoDO.setStatus(2);
		deviceSeekToPurchaseInfoMapper.updateByPrimaryKeySelective(deviceSeekToPurchaseInfoDO);
		return R.ok();
	}
	/**
	 * 删除发布求购信息
	 * @param id
	 * @return
	 */
	public R delReleasePurchaseInfo(Long id) {
		DeviceSeekToPurchaseInfoDO deviceSeekToPurchaseInfoDO = new DeviceSeekToPurchaseInfoDO();
		deviceSeekToPurchaseInfoDO.setId(id);
		deviceSeekToPurchaseInfoDO.setStatus(3);
		deviceSeekToPurchaseInfoMapper.updateByPrimaryKeySelective(deviceSeekToPurchaseInfoDO);
		return R.ok();
	}
	
	/**
	 * 编辑求购信息
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public R eidtReleasePurchaseInfo(Long id) throws Exception {
		DeviceSeekToPurchaseInfoDO selectByPrimaryKey = deviceSeekToPurchaseInfoMapper.selectByPrimaryKey(id);
		ReleasePurchaseInfoVO releasePurchaseInfoVO = new ReleasePurchaseInfoVO(); 
		BeanUtils.copyProperties(releasePurchaseInfoVO, selectByPrimaryKey);
		return R.ok().put("info", releasePurchaseInfoVO);
	}
	
	/**
	  * 更新求购信息 
	 * @param releasePurchaseInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R updateReleasePurchaseInfo(ReleasePurchaseInfoDTO releasePurchaseInfoDTO) throws Exception {
		DeviceSeekToPurchaseInfoDO deviceSeekToPurchaseInfoDO = new DeviceSeekToPurchaseInfoDO();
		BeanUtils.copyProperties(deviceSeekToPurchaseInfoDO, releasePurchaseInfoDTO);
		deviceSeekToPurchaseInfoDO.setUpdateTime(new Date());
		deviceSeekToPurchaseInfoMapper.updateByPrimaryKeySelective(deviceSeekToPurchaseInfoDO);
		return R.ok();
	}
	
}
