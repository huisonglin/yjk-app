package com.yjk.app.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(DevConditional.class)
public class DevEnv {

	private Logger logger = LoggerFactory.getLogger(getClass());
	public DevEnv() {
		logger.info("\n\n\n\n\n\n\n\n我是env被实例化了\n\n\n\n\n\n\n\n\n");
	}
}
