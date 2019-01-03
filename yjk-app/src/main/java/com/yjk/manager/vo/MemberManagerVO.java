package com.yjk.manager.vo;

import java.util.Date;

public class MemberManagerVO {

	private Long id;
	
	private String nickName;
	
	private String headImage;
	
	private String moble;
	
	private String remainCallCount;
	
	private String  createTime;
	
	private String rentCount;
	
	private String rentalCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getMoble() {
		return moble;
	}

	public void setMoble(String moble) {
		this.moble = moble;
	}

	public String getRemainCallCount() {
		return remainCallCount;
	}

	public void setRemainCallCount(String remainCallCount) {
		this.remainCallCount = remainCallCount;
	}

	

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRentCount() {
		return rentCount;
	}

	public void setRentCount(String rentCount) {
		this.rentCount = rentCount;
	}

	public String getRentalCount() {
		return rentalCount;
	}

	public void setRentalCount(String rentalCount) {
		this.rentalCount = rentalCount;
	}
	
	
	
}
