package com.yjk.app.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.service.SearchService;
import com.yjk.app.service.impl.PutOnRentInfoServiceImpl;
import com.yjk.app.util.R;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class memberServiceTest {

	@Autowired
	PutOnRentInfoServiceImpl putOnRentInfoServiceImpl;
	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	SearchService searchService;
	

	@Test
	public void test() throws Exception {
/*		List<DeviceDO> selectAll = deviceMapper.selectAll();
		for (DeviceDO deviceDO : selectAll) {
			R r = putOnRentInfoServiceImpl.putOnRent(deviceDO.getId());
			System.out.println(JSON.toJSON(r));
		}*/
		for(int i=0;i<400;i++) {
			putOnRentInfoServiceImpl.putOnRent(27L);
		}

		//searchService.search(1);

		
	}
}
