package com.yjk.app.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MyListVO {
//b.id,b.name as device_name,b.pics,b.address,b.newstime,b.type,b.member_id,b.status
	
	private Long id;
	
	
	private String deviceName;
	
	private String pics;
	
	private String address;
	
	private Date newstime;
	
	private String type;
	
	private Long memberId;
	
	private String status;
	
	private String contactMobile;
	
	private String xcxOpenId;
	
	private Double longitude; //经度
	
	private Double latitude; //纬度
	

	
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getXcxOpenId() {
		return xcxOpenId;
	}

	public void setXcxOpenId(String xcxOpenId) {
		this.xcxOpenId = xcxOpenId;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getNewstime() {
		return newstime;
	}

	public void setNewstime(Date newstime) {
		this.newstime = newstime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MyListVO [id=" + id + ", deviceName=" + deviceName + ", pics=" + pics + ", address="
				+ address + ", newstime=" + newstime + ", type=" + type + ", memberId=" + memberId + ", status="
				+ status + "]";
	}
	
	
}
