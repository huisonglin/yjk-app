package com.yjk.app.vo;

import java.util.Date;
import java.util.List;

public class DeviceRentOutInfoVO{

	//主键ID
	private Long id;
	//设备名称
	private String deviceName;
	//图片集
	private String pics;
	
	private String[] picsList;
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
	
	private String addressDetail;
	//出厂日期
	private Date manufacture;
	//联系人手机号
	private String contactMobile;
	//联系人姓名
	private String contactName;
	//规格ID
	private Long specId;
	//二级机型ID
	private Long twoStageModeId;
	//机型ID
	private Long modeId;
	//出租备注
	private String remark;
	
	
	
	public String getPics() {
		return pics;
	}

	private List<ValueUnitNameVO> priceName;
	
	private String price;
	
	

	public String[] getPicsList() {
		return picsList;
	}

	public void setPicsList(String[] picsList) {
		this.picsList = picsList;
	}

	public void setPics(String pics) {
		this.pics = pics;
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

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Date getManufacture() {
		return manufacture;
	}

	public void setManufacture(Date manufacture) {
		this.manufacture = manufacture;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public Long getTwoStageModeId() {
		return twoStageModeId;
	}

	public void setTwoStageModeId(Long twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}

	public Long getModeId() {
		return modeId;
	}

	public void setModeId(Long modeId) {
		this.modeId = modeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ValueUnitNameVO> getPriceName() {
		return priceName;
	}

	public void setPriceName(List<ValueUnitNameVO> priceName) {
		this.priceName = priceName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
	
	
}
