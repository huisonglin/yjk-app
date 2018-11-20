package com.yjk.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yjk.app.service.impl.FeeServiceImpl;
import com.yjk.app.util.R;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class PayTest {

	@Autowired
	FeeServiceImpl feeServiceImpl;
	
	@Test
	public void test() throws Exception {
		R dialing = feeServiceImpl.Dialing(null);
		System.out.println(JSON.toJSONString(dialing));
	}
}
