package com.yjk.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 工程求租报名表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:25
 */
@Table(name = "`yjk_device_rental_in_need_enroll`")
public class DeviceRentalInNeedEnrollDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//工程求租表ID
	private Long deviceRentalInNeedInfoId;
	//报名人ID
	private Long enrolmentId;
	//出租台数
	private Integer rentNumber;
	//1未生效 2已报名 3交易中 4已完成 5申诉中
	private Integer status;
	//收取状态
	private Integer stateOfCollection;
	//平台预计担保费（未收取）
	private BigDecimal expectGuaranteeFee;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

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
	 * 设置：工程求租表ID
	 */
	public void setDeviceRentalInNeedInfoId(Long deviceRentalInNeedInfoId) {
		this.deviceRentalInNeedInfoId = deviceRentalInNeedInfoId;
	}
	/**
	 * 获取：工程求租表ID
	 */
	public Long getDeviceRentalInNeedInfoId() {
		return deviceRentalInNeedInfoId;
	}
	/**
	 * 设置：报名人ID
	 */
	public void setEnrolmentId(Long enrolmentId) {
		this.enrolmentId = enrolmentId;
	}
	/**
	 * 获取：报名人ID
	 */
	public Long getEnrolmentId() {
		return enrolmentId;
	}
	/**
	 * 设置：出租台数
	 */
	public void setRentNumber(Integer rentNumber) {
		this.rentNumber = rentNumber;
	}
	/**
	 * 获取：出租台数
	 */
	public Integer getRentNumber() {
		return rentNumber;
	}
	/**
	 * 设置：1未生效 2已报名 3交易中 4已完成 5申诉中
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1未生效 2已报名 3交易中 4已完成 5申诉中
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：收取状态
	 */
	public void setStateOfCollection(Integer stateOfCollection) {
		this.stateOfCollection = stateOfCollection;
	}
	/**
	 * 获取：收取状态
	 */
	public Integer getStateOfCollection() {
		return stateOfCollection;
	}
	/**
	 * 设置：平台预计担保费（未收取）
	 */
	public void setExpectGuaranteeFee(BigDecimal expectGuaranteeFee) {
		this.expectGuaranteeFee = expectGuaranteeFee;
	}
	/**
	 * 获取：平台预计担保费（未收取）
	 */
	public BigDecimal getExpectGuaranteeFee() {
		return expectGuaranteeFee;
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
