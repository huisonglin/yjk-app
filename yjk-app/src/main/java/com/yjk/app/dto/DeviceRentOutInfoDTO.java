package com.yjk.app.dto;

import java.util.Date;

import javax.persistence.Id;

import tk.mybatis.mapper.annotation.KeySql;

public class DeviceRentOutInfoDTO {

	
	//主键ID
	private Long id;
	//会员ID
	private Long memberId;
	//设备ID
	private Long deviceId;
	//出租地址
	private String address;
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	//1手动发布 2语音发布
	private Integer type;
	//语音内容
	private String voice;
	
	private String addressDetail;
	//出租备注
	private String remark;
	
	
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
