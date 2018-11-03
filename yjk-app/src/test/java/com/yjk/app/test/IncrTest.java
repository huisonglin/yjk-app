package com.yjk.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjk.app.common.SelfIncreasingIdService;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class IncrTest {

	@Autowired
	SelfIncreasingIdService selfIncreasingIdService;
	
	@Test
	public void test() {
		for(int i= 0 ;i<100;i++) {
			System.out.println(selfIncreasingIdService.generateId());
		}

	}
}
