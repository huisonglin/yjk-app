package com.yjk.app.vo;

public class WxComplainVO {

	private Long memberId;
	
	private String complainContent;
	
	private String complainResult;
	
	private String remark;

	
	

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getComplainContent() {
		return complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	public String getComplainResult() {
		return complainResult;
	}

	public void setComplainResult(String complainResult) {
		this.complainResult = complainResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
