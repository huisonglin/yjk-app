package com.yjk.app.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.yjk.app.util.JSONUtil;
import com.yjk.app.vo.XcxPayNotifyInfoVO;

@Component
public class XcxPayNotiyConsumers {

	@JmsListener(destination="xcxPayNotiy")
	public void consumer(String message) {		
		XcxPayNotifyInfoVO xcxPayNotifyInfoVO = JSONUtil.parse(message, XcxPayNotifyInfoVO.class);
	}
}
