package com.yjk.app.dto;

public class BindMobileDTO {

	/**
	 * 小程序的openId
	 */
	private String openId;
	/**
	 * 绑定的手机号码
	 */
	private String mobile;
	/**
	 * 验证码
	 */
	private String verificationCode;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
}
