package com.yjk.app.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumers {

	@JmsListener(destination="myFirstMessage")
	public void consumer(String message) {		
		System.out.println(message);
	}
}
