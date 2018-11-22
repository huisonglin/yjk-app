package com.yjk.app.service.wx.template.service.impl;

import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;

@NotificationType(type = 2)
public class WxRefundTemplateNotifyHandle implements WxTemplateNotify{

	@Override
	public void doSendTemplateMessage(NotifyRequest request) {
		System.out.println("你好，我是专门处理退款的模板消息");
	}

}
