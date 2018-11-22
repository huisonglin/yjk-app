package com.yjk.app.service.wx.template.request;

public class NotifyRequest {

	/**
	 * 通知类型
	 */
	private Integer type;
	
	private String orderNo;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
