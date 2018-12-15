package com.yjk.app.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjk.app.common.SelfIncreasingIdService;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.vo.MyListVO;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class IncrTest {

	@Autowired
	SelfIncreasingIdService selfIncreasingIdService;
	@Autowired
	DeviceMapper deviceMapper;
	
	@Test
	public void test() {
/*		for(int i= 0 ;i<100;i++) {
			System.out.println(selfIncreasingIdService.generateId());
		}*/
/*		List<MyListVO> myList = deviceMapper.myList(105l);
		for (MyListVO myListVO : myList) {
			System.out.println(myListVO);
		}*/
	}
}
