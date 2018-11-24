package com.yjk.app.dto;

public class DialingDTO {

	/**
	 * 用户Id
	 */
	private Long memberId;
	/**
	 * 发布信息Id
	 */
	private Long infoId;
	
	/**
	 * openId
	 */
	private String openId;
	
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	
	
	
}
