package com.yjk.app.vo;

import java.util.Date;

public class AuditingResultVO {

	private Long memberId;
	
	private String deviceName;
	
	private Date AuditingTime;
	
	private String AuditingReulst;
	
	private String remark;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Date getAuditingTime() {
		return AuditingTime;
	}

	public void setAuditingTime(Date auditingTime) {
		AuditingTime = auditingTime;
	}

	public String getAuditingReulst() {
		return AuditingReulst;
	}

	public void setAuditingReulst(String auditingReulst) {
		AuditingReulst = auditingReulst;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
