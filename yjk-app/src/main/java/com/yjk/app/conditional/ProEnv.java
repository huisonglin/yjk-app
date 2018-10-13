package com.yjk.app.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ProConditional.class)
public class ProEnv {

	public ProEnv() {
		System.out.println("\n\n\n\\n\n\n我是pro我被实例化了哦\n\n\n\n\n\n");
	}
}
