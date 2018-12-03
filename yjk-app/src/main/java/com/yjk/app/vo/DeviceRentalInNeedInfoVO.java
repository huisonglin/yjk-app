package com.yjk.app.vo;

import java.util.Date;

public class DeviceRentalInNeedInfoVO{

	//主键ID
	private Long id;
	//会员ID
	private Long memberId;
	//施工地点
	private String adress;
	//详细地址
	private String addressDetail;
	//图片集
	private String pics;
	
	private String[] picList;
	//名称
	private String name;
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	//机型ID
	private Long modeId;
	//二级机型ID
	private Long twoStageModeId;
	//规格ID
	private Long specId;
	//求购数量
	private Integer needNumber;
	//工期
	private String term;
	//进场时间
	private Date inTime;
/*	//备注
	private String remark;
	//运输费
	private String transportation;
	//税费
	private String taxation;*/
	//设备进场付款
	private String equipmentArrivalPayment;
	//按月进度付款
	private String monthlyPayment;
	//设备退场结清尾款
	private String exitPayment;
	
	
	private String contactName;
	
	private String contactMobile;
	
	
	public String[] getPicList() {
		return picList;
	}
	public void setPicList(String[] picList) {
		this.picList = picList;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getModeId() {
		return modeId;
	}
	public void setModeId(Long modeId) {
		this.modeId = modeId;
	}
	public Long getTwoStageModeId() {
		return twoStageModeId;
	}
	public void setTwoStageModeId(Long twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public Integer getNeedNumber() {
		return needNumber;
	}
	public void setNeedNumber(Integer needNumber) {
		this.needNumber = needNumber;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
/*	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getTaxation() {
		return taxation;
	}
	public void setTaxation(String taxation) {
		this.taxation = taxation;
	}*/
	public String getEquipmentArrivalPayment() {
		return equipmentArrivalPayment;
	}
	public void setEquipmentArrivalPayment(String equipmentArrivalPayment) {
		this.equipmentArrivalPayment = equipmentArrivalPayment;
	}
	public String getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(String monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public String getExitPayment() {
		return exitPayment;
	}
	public void setExitPayment(String exitPayment) {
		this.exitPayment = exitPayment;
	}
	
	
}
