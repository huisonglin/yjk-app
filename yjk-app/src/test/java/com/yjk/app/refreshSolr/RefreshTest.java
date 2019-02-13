/*package com.yjk.app.refreshSolr;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.service.PutOnRentInfoService;
import com.yjk.common.dao.DeviceRentOutInfoMapper;
import com.yjk.common.entity.DeviceRentOutInfoDO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RunWith(SpringRunner.class)  
@SpringBootTest 
public class RefreshTest {
	
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	
	@Autowired
	PutOnRentInfoService putOnRentInfoService;
	

	@Test
	public void refresh() throws Exception {
		Example example = new Example(DeviceRentOutInfoDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status",2).andEqualTo("memberId",125);
		List<DeviceRentOutInfoDO> deviceRentOutInfoDOs = deviceRentOutInfoMapper.selectByExample(example);
		for (DeviceRentOutInfoDO deviceRentOutInfoDO : deviceRentOutInfoDOs) {
			System.out.println(deviceRentOutInfoDO);
			putOnRentInfoService.putOnRent(deviceRentOutInfoDO.getId());
			
		}
	}
}
*/