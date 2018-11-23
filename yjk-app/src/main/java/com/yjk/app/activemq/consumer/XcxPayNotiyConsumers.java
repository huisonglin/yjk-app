package com.yjk.app.activemq.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.yjk.app.util.JSONUtil;
import com.yjk.app.vo.XcxPayNotifyInfoVO;

@Component
public class XcxPayNotiyConsumers {

	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@JmsListener(destination="${wx.xcx.xcxNotifyQueueName}")
	public void consumer(String message) {		
		XcxPayNotifyInfoVO xcxPayNotifyInfoVO = JSONUtil.parse(message, XcxPayNotifyInfoVO.class);
		valueOperations.set(xcxPayNotifyInfoVO.getOut_trade_no(), message);
	}
}
