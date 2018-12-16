package com.yjk.app.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ProConditional.class)
public class ProEnv {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ProEnv() {
		logger.info("\n\n\n\\n\n\n我是pro我被实例化了哦\n\n\n\n\n\n");
	}
}
