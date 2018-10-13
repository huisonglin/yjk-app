package com.yjk.app.dto;

import java.math.BigDecimal;

public class ReleasePurchaseInfoDTO {

	
	//id
	private Long id;
	//名称
	private String name;
	//二级机型ID
	private BigDecimal twoStageModeId;
	//机型ID
	private Long modeId;
	//规格ID
	private Long specId;
	//求购地点
	private String adress;
	//纬度
	private Double latitude;
	//成色要求
	private String useDegree;
	//经度
	private Double longitude;
	//期望价格
	private BigDecimal expectedPrice;
	//备注
	private String remark;
	//1手动发布 2语音发布
	private Integer type;
	//语音内容
	private String voice;
	//会员Id
	private Long memberId;
	
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTwoStageModeId() {
		return twoStageModeId;
	}
	public void setTwoStageModeId(BigDecimal twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}
	public Long getModeId() {
		return modeId;
	}
	public void setModeId(Long modeId) {
		this.modeId = modeId;
	}
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getUseDegree() {
		return useDegree;
	}
	public void setUseDegree(String useDegree) {
		this.useDegree = useDegree;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getExpectedPrice() {
		return expectedPrice;
	}
	public void setExpectedPrice(BigDecimal expectedPrice) {
		this.expectedPrice = expectedPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	

}
