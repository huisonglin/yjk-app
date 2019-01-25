package com.yjk.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjk.manager.service.ReleaseManagerService;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class ManagerTest {

	@Autowired
	ListOperations<String, Object> listOperations;
	
	@Test
	public void teset() {
		String leftPop = (String)listOperations.leftPop("11111");
		System.out.println(leftPop);
	}
}
