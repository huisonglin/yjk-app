package com.yjk.app.service.wx.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;

/**
 * 拨打电话付费通知
 * @author huisonglin
 *
 */
@NotificationType(type = 1)
public class WxPayTemplateNotifyHandle implements WxTemplateNotify{

	@Autowired
	ValueOperations<String, String> valueOperations;
	@Override
	public void doSendTemplateMessage(NotifyRequest request) {
		valueOperations.set("templateNotify", "SUCCESS");
		System.out.println("你好，我是专门处理订单的模板消息");
	}



	
}
