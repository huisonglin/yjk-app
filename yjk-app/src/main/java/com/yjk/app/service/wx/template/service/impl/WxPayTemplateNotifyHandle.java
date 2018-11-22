package com.yjk.app.service.wx.template.service.impl;

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

	@Override
	public void doSendTemplateMessage(NotifyRequest request) {
		
		System.out.println("你好，我是专门处理订单的模板消息");
	}



	
}
