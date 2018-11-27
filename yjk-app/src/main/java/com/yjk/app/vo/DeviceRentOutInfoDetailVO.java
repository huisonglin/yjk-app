package com.yjk.app.vo;

import java.util.Date;
import java.util.List;

public class DeviceRentOutInfoDetailVO {

	private Long id;
	
	private String pics;
	
	//出租地址
	private String address;
	
	private String addressDetail;
	
	//设备名称
	private String deviceName;
	
	//联系人手机号
	private String contactMobile;
	//联系人姓名
	private String contactName;
	
	private String price;
	
	private Date manufacture;
	
	//出租备注
	private String remark;
	
	
	private String distance;
	


	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String[] getPics() {
		if(pics != null && !"".equals(pics)) {
			return pics.split("#");
		}
		return null;
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

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getManufacture() {
		return manufacture;
	}

	public void setManufacture(Date manufacture) {
		this.manufacture = manufacture;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
