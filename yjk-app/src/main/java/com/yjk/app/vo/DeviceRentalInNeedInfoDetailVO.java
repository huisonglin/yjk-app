package com.yjk.app.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjk.app.config.QiNiuConfig;

public class DeviceRentalInNeedInfoDetailVO {


	//主键ID
	private Long id;
	//施工地点
	private String adress;
	//详细地址
	private String addressDetail;
	//图片集
	private String pics;
	//名称
	private String name;
	//求购数量
	private Integer needNumber;
	//工期
	private String term;
	//进场时间
	private Date inTime;
	//备注
	private String remark;
	//设备进场付款
	private String equipmentArrivalPayment;
	//按月进度付款
	private String monthlyPayment;
	//设备退场结清尾款
	private String exitPayment;
	//单价
	private String price;
	
	 private List<ValueUnitNameVO>  arrayPrice;
	//联系人姓名
	private String contactName;
	//联系人手机号
	private String contactMobile;
	//距离
	private String distance;
	
	private String active;
	
	private Long memberId;
	
	private Date newstime;
	//信息类型 2求租
	private Integer type = 2;
	
	
	private String originalPic; 
	
	private String isCollection = "0";//默认未收藏
	
	
	

	public String getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(String isCollection) {
		this.isCollection = isCollection;
	}
	public String getOriginalPic() {
		if(pics != null && !"".equals(pics)) {
			String[] urls = pics.split("#");
			if(urls.length > 0) {
				return urls[0];
			}
		}
		return null;
	}
	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	}
	
	public List<ValueUnitNameVO> getArrayPrice() {
		return arrayPrice;
	}
	public void setArrayPrice(List<ValueUnitNameVO> arrayPrice) {
		this.arrayPrice = arrayPrice;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getNewstime() {
		return newstime;
	}
	public void setNewstime(Date newstime) {
		this.newstime = newstime;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getActive() {
		if(active == null) {
			return "一小时之前活跃";
		}
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String[] getPics() {
		if(pics != null && !"".equals(pics)) {
			String[] urls = pics.split("#");
			for(int i=0;i<urls.length;i++) {
				urls[i]=urls[i]+QiNiuConfig.XCX_DETAIL;
			}
			return urls;
		}
		return null;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
}
