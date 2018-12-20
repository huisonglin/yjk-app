package com.yjk.app.service.impl;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.common.SelfIncreasingIdService;
import com.yjk.app.config.QiNiuConfig;
import com.yjk.app.dao.DeviceRentalInNeedInfoMapper;
import com.yjk.app.dto.DeviceRentalInNeedInfoDTO;
import com.yjk.app.entity.DeviceRentalInNeedInfoDO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.DeviceRentalInNeedInfoService;
import com.yjk.app.service.PutOnProjectInfoService;
import com.yjk.app.service.ValueUnitCorrelationService;
import com.yjk.app.util.R;
import com.yjk.app.vo.DeviceRentalInNeedInfoVO;

@Service
public class DeviceRentalInNeedInfoServiceImpl implements DeviceRentalInNeedInfoService{

	@Autowired
	DeviceRentalInNeedInfoMapper deviceRentalInNeedInfoMapper;
	
	@Autowired
	SelfIncreasingIdService selfIncreasingIdService;
	
	@Autowired
	PutOnProjectInfoService putOnProjectInfoService;
	
	@Autowired
	ValueUnitCorrelationService valueUnitCorrelationService;
	
	/**
	 * 添加或者修改发布信息
	 * @param deviceRentalInNeedInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R addOrUpdateNeedInfo(DeviceRentalInNeedInfoDTO deviceRentalInNeedInfoDTO) throws Exception {
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = new DeviceRentalInNeedInfoDO();
		PropertyUtils.copyProperties(deviceRentalInNeedInfoDO, deviceRentalInNeedInfoDTO);
		Long rentalInNeedInfoId = deviceRentalInNeedInfoDTO.getId();
		if(deviceRentalInNeedInfoDO.getId() == null) {
			Long id = selfIncreasingIdService.generateId();
			deviceRentalInNeedInfoDO.setCreateTime(new Date());
			deviceRentalInNeedInfoDO.setUpdateTime(new Date());
			deviceRentalInNeedInfoDO.setId(id);
			deviceRentalInNeedInfoDO.setStatus(2);//已上架
			deviceRentalInNeedInfoMapper.insertSelective(deviceRentalInNeedInfoDO);
			rentalInNeedInfoId = deviceRentalInNeedInfoDO.getId();
		}else {
			deviceRentalInNeedInfoDO.setUpdateTime(new Date());
			deviceRentalInNeedInfoMapper.updateByPrimaryKeySelective(deviceRentalInNeedInfoDO);
		}
		valueUnitCorrelationService.saveValueUnitCorrelation(deviceRentalInNeedInfoDTO.getPrice(), rentalInNeedInfoId);
		putOnProjectInfoService.putOnProject(rentalInNeedInfoId);
		return R.ok().put("info", rentalInNeedInfoId);
	}
	
	/**
	 * 编辑发布信息
	 * @param id
	 */
	public R editNeedInfo(Long id) throws Exception{
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = deviceRentalInNeedInfoMapper.selectByPrimaryKey(id);
		if(deviceRentalInNeedInfoDO == null) {
			throw new RRException("检索不到该条数据");
		}
		DeviceRentalInNeedInfoVO vo = new DeviceRentalInNeedInfoVO();
		PropertyUtils.copyProperties(vo, deviceRentalInNeedInfoDO);
		if(StringUtils.isNotBlank(deviceRentalInNeedInfoDO.getPics())) {
			String[] picList = deviceRentalInNeedInfoDO.getPics().split("#");
			for(int i=0;i<picList.length;i++) {
				picList[i] = picList[i]+QiNiuConfig.XCX_DETAIL;
			}
			vo.setPicList(picList);
		}
		return R.ok().put("info", vo);
	}
	
	/**
	 * 下架发布信息
	 * @return
	 */
	public R cacelNeedInfo(Long id) throws Exception{
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = new DeviceRentalInNeedInfoDO();
		deviceRentalInNeedInfoDO.setId(id);
		deviceRentalInNeedInfoDO.setStatus(1);
		deviceRentalInNeedInfoMapper.updateByPrimaryKeySelective(deviceRentalInNeedInfoDO);
		putOnProjectInfoService.projectInfoOut(id);
		return R.ok();
	}
	
	/**
	 * 上架发布信息
	 * @return
	 */
	public R releaseNeedInfo(Long id) throws Exception {
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = new DeviceRentalInNeedInfoDO();
		deviceRentalInNeedInfoDO.setId(id);
		deviceRentalInNeedInfoDO.setStatus(2);
		deviceRentalInNeedInfoMapper.updateByPrimaryKeySelective(deviceRentalInNeedInfoDO);
		putOnProjectInfoService.putOnProject(id);
		return R.ok();
	}
	
	/**
	 * 删除发布信息
	 * @param id
	 * @return
	 */
	public R deleteNeedInfo(Long id) throws Exception {
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = new DeviceRentalInNeedInfoDO();
		deviceRentalInNeedInfoDO.setId(id);
		deviceRentalInNeedInfoDO.setStatus(3);
		deviceRentalInNeedInfoMapper.updateByPrimaryKeySelective(deviceRentalInNeedInfoDO);
		putOnProjectInfoService.projectInfoOut(id);
		return R.ok();
	}
}
