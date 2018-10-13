package com.yjk.app.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(DevConditional.class)
public class DevEnv {

	
	public DevEnv() {
		System.out.println("\n\n\n\n\n\n\n\n我是env被实例化了\n\n\n\n\n\n\n\n\n");
	}
}
