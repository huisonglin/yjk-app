package com.yjk.app.vo;

public class UnifiedorderAttachVO {

	private String apikey;
	
	private String queueName;
	//商品名称
	private String gn;
	//温馨提示
	private String kr;

	

	
	public String getGn() {
		return gn;
	}

	public void setGn(String gn) {
		this.gn = gn;
	}

	public String getKr() {
		return kr;
	}

	public void setKr(String kr) {
		this.kr = kr;
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

	public UnifiedorderAttachVO(String apikey, String queueName, String gn, String kr) {
		super();
		this.apikey = apikey;
		this.queueName = queueName;
		this.gn = gn;
		this.kr = kr;
	}

	
	
	
	
}
