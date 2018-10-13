package com.yjk.app.service.impl;


import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.DeviceSeekToPurchaseInfoMapper;
import com.yjk.app.dto.ReleasePurchaseInfoDTO;
import com.yjk.app.entity.DeviceSeekToPurchaseInfoDO;
import com.yjk.app.service.DeviceSeekToPurchaseInfoService;
import com.yjk.app.util.R;
import com.yjk.app.vo.ReleasePurchaseInfoVO;

@Service
public class DeviceSeekToPurchaseInfoServiceImpl implements DeviceSeekToPurchaseInfoService{

	@Autowired
	private DeviceSeekToPurchaseInfoMapper deviceSeekToPurchaseInfoMapper;
	
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
	
}
