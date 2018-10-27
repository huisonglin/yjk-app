package com.yjk.app.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yjk.app.service.impl.PutOnRentInfoServiceImpl;
import com.yjk.app.util.R;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class memberServiceTest {

	@Autowired
	PutOnRentInfoServiceImpl putOnRentInfoServiceImpl;
	

	@Test
	public void test() throws Exception {
		R r = putOnRentInfoServiceImpl.putOnRent(7L);
		System.out.println(JSON.toJSON(r));
		
	}
}
