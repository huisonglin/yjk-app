package com.yjk.app.service.impl;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.common.SelfIncreasingIdService;
import com.yjk.app.common.TemplateEnum;
import com.yjk.app.config.QiNiuConfig;
import com.yjk.app.dto.DeviceRentOutInfoDTO;
import com.yjk.app.dto.InfoMatchDTO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.service.InfoMatchingService;
import com.yjk.app.service.PutOnRentInfoService;
import com.yjk.app.service.ValueUnitCorrelationService;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.strategy.TemplateMessageStragegy;
import com.yjk.app.util.R;
import com.yjk.app.vo.AuditingResultVO;
import com.yjk.app.vo.DeviceRentOutInfoVO;
import com.yjk.common.dao.DeviceMapper;
import com.yjk.common.dao.DeviceRentOutInfoMapper;
import com.yjk.common.entity.DeviceDO;
import com.yjk.common.entity.DeviceRentOutInfoDO;

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
	@Autowired
	ValueUnitCorrelationService valueUnitCorrelationService;
	@Autowired
	TemplateMessageStragegy templateMessageStragegy;
	@Autowired
	InfoMatchingService infoMatchingService;
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
			DeviceRentOutInfoDO deviceRentOutInfo = deviceRentOutInfoMapper.selectByPrimaryKey(deviceRentOutInfoDO);
			DeviceDO deviceDO = deviceMapper.selectByPrimaryKey(deviceRentOutInfoDO.getId());
			PropertyUtils.copyProperties(deviceDO, deviceRentOutInfoDTO);
			deviceDO.setId(deviceRentOutInfo.getDeviceId());
			deviceMapper.updateByPrimaryKeySelective(deviceDO);
			deviceRentOutInfoMapper.updateByPrimaryKeySelective(deviceRentOutInfoDO);
		}
		valueUnitCorrelationService.saveValueUnitCorrelation(deviceRentOutInfoDTO.getPrice(), deviceRentOutInfoDO.getId());
		putOnRentInfoService.putOnRent(deviceRentOutInfoId);
		
		NotifyRequest notifyRequest = new NotifyRequest();
		notifyRequest.setType(TemplateEnum.AUDITING_RESULT.getValue());
		AuditingResultVO auditingResultVO = new AuditingResultVO();
		auditingResultVO.setAuditingReulst("恭喜您,审核通过");
		auditingResultVO.setAuditingTime(new Date());
		auditingResultVO.setMemberId(deviceRentOutInfoDTO.getMemberId());
		auditingResultVO.setDeviceName("【出租】:"+deviceRentOutInfoDTO.getDeviceName());
		auditingResultVO.setRemark("点击下方，可查看适合您的需求信息");
		notifyRequest.setAuditingResultVO(auditingResultVO);
		templateMessageStragegy.excute(notifyRequest);
		
		//把信息推送给合适的用户
		SendSuitUser(deviceRentOutInfoDTO, deviceRentOutInfoDO, deviceRentOutInfoId);
		
		return R.ok().put("info", deviceRentOutInfoId);
	}


	/**
	 * @param deviceRentOutInfoDTO
	 * @param deviceRentOutInfoDO
	 * @param deviceRentOutInfoId
	 * @throws Exception
	 */
	private void SendSuitUser(DeviceRentOutInfoDTO deviceRentOutInfoDTO, DeviceRentOutInfoDO deviceRentOutInfoDO,
			Long deviceRentOutInfoId) throws Exception {
		InfoMatchDTO infoMatchDTO = new InfoMatchDTO();
		infoMatchDTO.setAddress(deviceRentOutInfoDO.getAddressDetail());
		infoMatchDTO.setDeviceName(deviceRentOutInfoDTO.getDeviceName());
		infoMatchDTO.setInfoId(deviceRentOutInfoId);
		infoMatchDTO.setLatitude(deviceRentOutInfoDTO.getLatitude());
		infoMatchDTO.setLongitude(deviceRentOutInfoDTO.getLongitude());
		infoMatchDTO.setModeId(deviceRentOutInfoDTO.getModeId());
		infoMatchDTO.setTwoStageModeId(deviceRentOutInfoDTO.getTwoStageModeId());
		infoMatchDTO.setSpecId(deviceRentOutInfoDTO.getSpecId());
		infoMatchDTO.setType(1); //出租
		infoMatchDTO.setSelfMemberId(deviceRentOutInfoDTO.getMemberId());
		infoMatchingService.notifyNeedUser(infoMatchDTO);
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
		DeviceDO deviceDO = deviceMapper.selectByPrimaryKey(deviceRentOutInfoDO.getDeviceId());
		
		DeviceRentOutInfoVO deviceRentOutInfoVO = new DeviceRentOutInfoVO();
		PropertyUtils.copyProperties(deviceRentOutInfoVO, deviceDO);
		PropertyUtils.copyProperties(deviceRentOutInfoVO, deviceRentOutInfoDO);
		if(StringUtils.isNotBlank(deviceDO.getPics())) {
			String[] pics = deviceDO.getPics().split("#");
			for(int i=0;i<pics.length;i++) {
				pics[i] = pics[i] + QiNiuConfig.XCX_DETAIL;
			}
			deviceRentOutInfoVO.setPicsList(pics);
		}
		deviceRentOutInfoVO.setPriceName(valueUnitCorrelationService.showPriceName(deviceRentOutInfoDO.getId()));
		deviceRentOutInfoVO.setPrice(valueUnitCorrelationService.showPrice(deviceRentOutInfoDO.getId()));
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
