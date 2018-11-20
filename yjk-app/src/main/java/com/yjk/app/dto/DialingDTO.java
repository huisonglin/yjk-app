package com.yjk.app.dto;

public class DialingDTO {

	/**
	 * 用户Id
	 */
	private String memberId;
	/**
	 * 发布信息Id
	 */
	private String infoId;
	
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
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
	
}
