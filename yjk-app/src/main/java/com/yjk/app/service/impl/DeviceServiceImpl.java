package com.yjk.app.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.dto.DeviceDTO;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.service.DeviceService;
import com.yjk.app.util.R;
import com.yjk.app.vo.DeviceVO;
import com.yjk.app.vo.MyListVO;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceMapper deviceMapper;
	
	
	/**
	 * 添加设备接口
	 */
	public R addDevice(DeviceDTO addDeviceDTO) {
		DeviceDO deviceDO = new DeviceDO();
		deviceDO.setPics(addDeviceDTO.getPics());
		deviceDO.setDeviceName(addDeviceDTO.getDeviceName());
		deviceDO.setManufacture(addDeviceDTO.getManufacture());
		deviceDO.setNewMachinePrice(addDeviceDTO.getNewMachinePrice());
		deviceDO.setWorkTime(addDeviceDTO.getWorkTime());
		deviceDO.setSelfEstimate(addDeviceDTO.getSelfEstimate());
		deviceDO.setMemberId(addDeviceDTO.getMemberId());
		deviceDO.setModeId(addDeviceDTO.getModeId());
		deviceDO.setTwoStageModeId(addDeviceDTO.getTwoStageModeId());
		deviceDO.setSpecId(addDeviceDTO.getSpecId());
		deviceDO.setCreateTime(new Date());
		deviceDO.setUpdateTime(new Date());
		deviceDO.setType(1);//普通
		deviceDO.setStatus(1);//未删除
		deviceDO.setSaleStatus(0);//未发布
		deviceDO.setRentStatus(0);//未发布
		deviceDO.setRemark(addDeviceDTO.getRemark());
		deviceMapper.insertSelective(deviceDO);
		return R.ok().put("info", deviceDO.getId());
	}
	
	public R updateDevcie(DeviceDTO editDeviceDTO) {
		DeviceDO deviceDO = new DeviceDO();
		deviceDO.setId(editDeviceDTO.getId());
		deviceDO.setPics(editDeviceDTO.getPics());
		deviceDO.setDeviceName(editDeviceDTO.getDeviceName());
		deviceDO.setManufacture(editDeviceDTO.getManufacture());
		deviceDO.setNewMachinePrice(editDeviceDTO.getNewMachinePrice());
		deviceDO.setWorkTime(editDeviceDTO.getWorkTime());
		deviceDO.setSelfEstimate(editDeviceDTO.getSelfEstimate());
		deviceDO.setModeId(editDeviceDTO.getModeId());
		deviceDO.setTwoStageModeId(editDeviceDTO.getTwoStageModeId());
		deviceDO.setSpecId(editDeviceDTO.getSpecId());
		deviceDO.setUpdateTime(new Date());
		deviceMapper.updateByPrimaryKeySelective(deviceDO);
		return R.ok();
	}
	
	public R editDevice(Long id) {
		DeviceDO deviceDO = deviceMapper.selectByPrimaryKey(id);
		DeviceVO deviceVO = new DeviceVO();
		deviceVO.setDeviceName(deviceDO.getDeviceName());
		deviceVO.setId(id);
		deviceVO.setManufacture(deviceDO.getManufacture());
		deviceVO.setModeId(deviceDO.getModeId());
		deviceVO.setNewMachinePrice(deviceDO.getNewMachinePrice());
		deviceVO.setPics(deviceDO.getPics());
		deviceVO.setRemark(deviceDO.getRemark());
		deviceVO.setSelfEstimate(deviceDO.getSelfEstimate());
		deviceVO.setSpecId(deviceDO.getSpecId());
		deviceVO.setTwoStageModeId(deviceDO.getTwoStageModeId());
		deviceVO.setWorkTime(deviceDO.getWorkTime());
		return R.ok().put("info", deviceVO);
	}
	
	public R myList(Long memberId) {
		List<MyListVO> myList = deviceMapper.myList(memberId);
		for (MyListVO myListVO : myList) {
			if(StringUtils.isNotBlank(myListVO.getPics())) {
				myListVO.setPics(myListVO.getPics().split("#")[0]);
			}
		}
		return R.ok().put("info", myList);
	}
	
	
}
