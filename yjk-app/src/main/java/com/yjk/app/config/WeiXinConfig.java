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
	
	@Value("${wx.xcx.accessToken}")
	private String accessToken;
	
	@Value("${wx.xcx.accessTokendUrl}")
	private String accessTokendUrl;
	
	@Value("${wx.xcx.decryptedShareInfo}")
	private String xcxDecryptedShareInfo;
	
	@Value("${wx.xcx.page}")
	private String xcxPage;
	
	@Value("${wx.xcx.fee}")
	private String fee;
	
	@Value("${wx.refundUrl}")
	private String refundUrl;
	
	@Value("${wx.certUrl}")
	private String certUrl;

	
	
	

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getXcxDecryptedShareInfo() {
		return xcxDecryptedShareInfo;
	}

	public void setXcxDecryptedShareInfo(String xcxDecryptedShareInfo) {
		this.xcxDecryptedShareInfo = xcxDecryptedShareInfo;
	}

	public String getCertUrl() {
		return certUrl;
	}

	public void setCertUrl(String certUrl) {
		this.certUrl = certUrl;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}

	public String getXcxPage() {
		return xcxPage;
	}

	public void setXcxPage(String xcxPage) {
		this.xcxPage = xcxPage;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokendUrl() {
		return accessTokendUrl;
	}

	public void setAccessTokendUrl(String accessTokendUrl) {
		this.accessTokendUrl = accessTokendUrl;
	}

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
