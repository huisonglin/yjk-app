package com.yjk.app.test;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.service.impl.PutOnRentInfoServiceImpl;
import com.yjk.app.util.R;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class memberServiceTest {

	@Autowired
	PutOnRentInfoServiceImpl putOnRentInfoServiceImpl;
	
	@Autowired
	DeviceMapper deviceMapper;
	@Test
	public void test() throws Exception {
		Example example = new Example(DeviceDO.class);
		example.selectProperties("deviceName");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", 25l);
		List<DeviceDO> deviceDOs = deviceMapper.selectByExample(example);
		for (DeviceDO deviceDO : deviceDOs) {
			System.out.println(deviceDO);
		}
	}
	
	
}
