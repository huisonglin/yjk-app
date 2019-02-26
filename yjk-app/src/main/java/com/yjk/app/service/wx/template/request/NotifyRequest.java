package com.yjk.app.service.wx.template.request;

import com.yjk.app.vo.AuditingResultVO;
import com.yjk.app.vo.MatchSuccessVO;
import com.yjk.app.vo.ReceiveComplainVO;
import com.yjk.app.vo.WxComplainVO;
import com.yjk.app.vo.XcxPayNotifyInfoVO;

import com.yjk.app.dto.RefundTemplateInfoVO;
public class NotifyRequest {

	/**
	 * 通知类型
	 */
	private Integer type;
	
	private XcxPayNotifyInfoVO xcxPayNotifyInfoVO;
	
	private RefundTemplateInfoVO RefundTemplateInfoVO;
	
	private WxComplainVO wxComplainVO;
	
	private ReceiveComplainVO receiveComplainVO;
	
	private AuditingResultVO auditingResultVO;
	
	private MatchSuccessVO matchSuccessVO;
	
	
	
	

	public MatchSuccessVO getMatchSuccessVO() {
		return matchSuccessVO;
	}

	public void setMatchSuccessVO(MatchSuccessVO matchSuccessVO) {
		this.matchSuccessVO = matchSuccessVO;
	}

	public AuditingResultVO getAuditingResultVO() {
		return auditingResultVO;
	}

	public void setAuditingResultVO(AuditingResultVO auditingResultVO) {
		this.auditingResultVO = auditingResultVO;
	}

	public ReceiveComplainVO getReceiveComplainVO() {
		return receiveComplainVO;
	}

	public void setReceiveComplainVO(ReceiveComplainVO receiveComplainVO) {
		this.receiveComplainVO = receiveComplainVO;
	}

	public WxComplainVO getWxComplainVO() {
		return wxComplainVO;
	}

	public void setWxComplainVO(WxComplainVO wxComplainVO) {
		this.wxComplainVO = wxComplainVO;
	}

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
