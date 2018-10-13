package com.yjk.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:43:26
 */
@Table(name = "`yjk_device`")
public class DeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
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
	//1普通
	private Integer type;
	//1未删除 2已删除
	private Integer status;
	//0未发布 1已发布
	private Integer saleStatus;
	//规格ID
	private Long specId;
	//二级机型ID
	private Long twoStageModeId;
	//机型ID
	private Long modeId;
	//0未发布 1已发布
	private Integer rentStatus;
	//备注
	private String remark;
	//新机价格
	private BigDecimal newMachinePrice;
	//自我估值
	private BigDecimal selfEstimate;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	
	

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
	/**
	 * 设置：主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：设备名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * 获取：设备名称
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * 设置：出厂日期
	 */
	public void setManufacture(Date manufacture) {
		this.manufacture = manufacture;
	}
	/**
	 * 获取：出厂日期
	 */
	public Date getManufacture() {
		return manufacture;
	}
	/**
	 * 设置：工作时间
	 */
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	/**
	 * 获取：工作时间
	 */
	public Integer getWorkTime() {
		return workTime;
	}
	/**
	 * 设置：设备图片
	 */
	public void setPics(String pics) {
		this.pics = pics;
	}
	/**
	 * 获取：设备图片
	 */
	public String getPics() {
		return pics;
	}
	/**
	 * 设置：会员ID
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员ID
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：1普通
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1普通
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：1未删除 2已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1未删除 2已删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：0未发布 1已发布
	 */
	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}
	/**
	 * 获取：0未发布 1已发布
	 */
	public Integer getSaleStatus() {
		return saleStatus;
	}
	/**
	 * 设置：规格ID
	 */
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	/**
	 * 获取：规格ID
	 */
	public Long getSpecId() {
		return specId;
	}
	/**
	 * 设置：二级机型ID
	 */
	public void setTwoStageModeId(Long twoStageModeId) {
		this.twoStageModeId = twoStageModeId;
	}
	/**
	 * 获取：二级机型ID
	 */
	public Long getTwoStageModeId() {
		return twoStageModeId;
	}
	/**
	 * 设置：机型ID
	 */
	public void setModeId(Long modeId) {
		this.modeId = modeId;
	}
	/**
	 * 获取：机型ID
	 */
	public Long getModeId() {
		return modeId;
	}
	/**
	 * 设置：0未发布 1已发布
	 */
	public void setRentStatus(Integer rentStatus) {
		this.rentStatus = rentStatus;
	}
	/**
	 * 获取：0未发布 1已发布
	 */
	public Integer getRentStatus() {
		return rentStatus;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
