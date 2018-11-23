package com.yjk.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeiXinConfig {

	
	@Value("${wx.mch_id}")
	private String mechId;
	
	@Value("${wx.apikey}")
	private String apikey;
	
	@Value("${wx.xcx.appid}")
	private String xcxAppId;
	
	@Value("${wx.xcx.appSecret}")
	private String xcxAppSecret;
	
	@Value("${wx.xcx.accessPayParamUrl}")
	private String xcxAccessPayParamUrl;
	
	@Value("${wx.xcx.accessOpenIdUrl}")
	private String xcxAccessOpenIdUrl;
	
	@Value("${wx.xcx.tradeType}")
	private String tradeType;
	
	@Value("${wx.xcx.decryptUserInfoUrl}")
	private String xcxDecryptUserInfoUrl;
	
	@Value("${wx.xcx.decryptedPhoneNumber}")
	private String xcxDecryptedPhoneNumber;
	
	@Value("${wx.xcx.xcxNotifyQueueName}")
	private String xcxNotifyQueueName;
	
	
	
	
	

	public String getXcxNotifyQueueName() {
		return xcxNotifyQueueName;
	}

	public void setXcxNotifyQueueName(String xcxNotifyQueueName) {
		this.xcxNotifyQueueName = xcxNotifyQueueName;
	}

	public String getXcxDecryptedPhoneNumber() {
		return xcxDecryptedPhoneNumber;
	}

	public void setXcxDecryptedPhoneNumber(String xcxDecryptedPhoneNumber) {
		this.xcxDecryptedPhoneNumber = xcxDecryptedPhoneNumber;
	}

	public String getXcxDecryptUserInfoUrl() {
		return xcxDecryptUserInfoUrl;
	}

	public void setXcxDecryptUserInfoUrl(String xcxDecryptUserInfoUrl) {
		this.xcxDecryptUserInfoUrl = xcxDecryptUserInfoUrl;
	}

	public String getMechId() {
		return mechId;
	}

	public void setMechId(String mechId) {
		this.mechId = mechId;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getXcxAppId() {
		return xcxAppId;
	}

	public void setXcxAppId(String xcxAppId) {
		this.xcxAppId = xcxAppId;
	}

	public String getXcxAppSecret() {
		return xcxAppSecret;
	}

	public void setXcxAppSecret(String xcxAppSecret) {
		this.xcxAppSecret = xcxAppSecret;
	}

	public String getXcxAccessPayParamUrl() {
		return xcxAccessPayParamUrl;
	}

	public void setXcxAccessPayParamUrl(String xcxAccessPayParamUrl) {
		this.xcxAccessPayParamUrl = xcxAccessPayParamUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getXcxAccessOpenIdUrl() {
		return xcxAccessOpenIdUrl;
	}

	public void setXcxAccessOpenIdUrl(String xcxAccessOpenIdUrl) {
		this.xcxAccessOpenIdUrl = xcxAccessOpenIdUrl;
	}

	
	
	
	
	
}
