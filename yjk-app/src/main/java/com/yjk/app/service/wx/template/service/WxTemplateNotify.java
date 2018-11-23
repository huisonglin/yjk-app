package com.yjk.app.service.wx.template.service;

import com.yjk.app.service.wx.template.request.NotifyRequest;

public interface WxTemplateNotify {

	public void doSendTemplateMessage(NotifyRequest request)throws Exception;
	
}
