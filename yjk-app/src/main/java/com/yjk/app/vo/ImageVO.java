package com.yjk.app.vo;

public class ImageVO {

	private String url;
	
	private Long timestamp = System.currentTimeMillis();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
