package com.yjk.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.strategy.TemplateMessageStragegy;

@RunWith(SpringRunner.class)  
@SpringBootTest  
public class TemplateMessageTest {

	@Autowired
	TemplateMessageStragegy templateMessageStragegy;
	
	@Test
	public void test() throws Exception {
		NotifyRequest n = new NotifyRequest();
		n.setType(1);
		templateMessageStragegy.excute(n);
	}
}
