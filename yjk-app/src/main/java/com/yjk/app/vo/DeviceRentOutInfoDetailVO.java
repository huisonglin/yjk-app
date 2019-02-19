package com.yjk.app.vo;

import java.util.Date;
import java.util.List;

import com.yjk.app.config.QiNiuConfig;

public class DeviceRentOutInfoDetailVO {

	private Long id;
	
	private Long memberId;
	
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
	
	private List<ValueUnitNameVO> price;
	
	private Date manufacture;
	
	//出租备注
	private String remark;
	
	
	private String distance;
	
	private String active;
	//发布时间
	private Date newstime;
	//信息类型 1出租
	private Integer type = 1;
	
	
	
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

	

	public List<ValueUnitNameVO> getPrice() {
		return price;
	}
	public void setPrice(List<ValueUnitNameVO> price) {
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
