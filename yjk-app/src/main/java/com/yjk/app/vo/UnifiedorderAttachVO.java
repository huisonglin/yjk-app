package com.yjk.app.vo;

public class UnifiedorderAttachVO {

	private String apikey;
	
	private String queueName;
	//商品名称
	private String goodzName;
	//温馨提示
	private String kindlyReminder;

	

	public String getGoodzName() {
		return goodzName;
	}

	public void setGoodzName(String goodzName) {
		this.goodzName = goodzName;
	}

	public String getKindlyReminder() {
		return kindlyReminder;
	}

	public void setKindlyReminder(String kindlyReminder) {
		this.kindlyReminder = kindlyReminder;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public UnifiedorderAttachVO() {
		
	}
	public UnifiedorderAttachVO(String apiKey, String queueName) {
		super();
		this.apikey = apiKey;
		this.queueName = queueName;
	}
	
	
}
