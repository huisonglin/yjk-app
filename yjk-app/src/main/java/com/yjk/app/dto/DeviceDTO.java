package com.yjk.app.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceDTO {

	private Long id;
	//设备名称
	private String deviceName;
	//出厂日期
	private Date manufacture;
	//工作时间
	private Integer workTime;
	//设备图片
	private String pics;
	//会员ID
	private Long memberId;
	//规格ID
	private Long specId;
	//二级机型ID
	private Long twoStageModeId;
	//机型ID
	private Long modeId;
	//新机价格
	private BigDecimal newMachinePrice;
	//自我估值
	private BigDecimal selfEstimate;
	//备注
	private String remark;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Date getManufacture() {
		return manufacture;
	}
	public void setManufacture(Date manufacture) {
		this.manufacture = manufacture;
	}
	public Integer getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
	public BigDecimal getNewMachinePrice() {
		return newMachinePrice;
	}
	public void setNewMachinePrice(BigDecimal newMachinePrice) {
		this.newMachinePrice = newMachinePrice;
	}
	public BigDecimal getSelfEstimate() {
		return selfEstimate;
	}
	public void setSelfEstimate(BigDecimal selfEstimate) {
		this.selfEstimate = selfEstimate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
}
