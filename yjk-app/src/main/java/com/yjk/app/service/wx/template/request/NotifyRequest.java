package com.yjk.app.service.wx.template.request;

import com.yjk.app.vo.XcxPayNotifyInfoVO;

import com.yjk.app.dto.RefundTemplateInfoVO;
public class NotifyRequest {

	/**
	 * 通知类型
	 */
	private Integer type;
	
	private XcxPayNotifyInfoVO xcxPayNotifyInfoVO;
	
	private RefundTemplateInfoVO RefundTemplateInfoVO;
	
	

	public RefundTemplateInfoVO getRefundTemplateInfoVO() {
		return RefundTemplateInfoVO;
	}

	public void setRefundTemplateInfoVO(RefundTemplateInfoVO refundTemplateInfoVO) {
		RefundTemplateInfoVO = refundTemplateInfoVO;
	}

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
