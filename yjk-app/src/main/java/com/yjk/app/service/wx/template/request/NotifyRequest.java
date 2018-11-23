package com.yjk.app.service.wx.template.request;

import com.yjk.app.vo.XcxPayNotifyInfoVO;

public class NotifyRequest {

	/**
	 * 通知类型
	 */
	private Integer type;
	
	private XcxPayNotifyInfoVO xcxPayNotifyInfoVO;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public XcxPayNotifyInfoVO getXcxPayNotifyInfoVO() {
		return xcxPayNotifyInfoVO;
	}

	public void setXcxPayNotifyInfoVO(XcxPayNotifyInfoVO xcxPayNotifyInfoVO) {
		this.xcxPayNotifyInfoVO = xcxPayNotifyInfoVO;
	}

	

	
	
}
