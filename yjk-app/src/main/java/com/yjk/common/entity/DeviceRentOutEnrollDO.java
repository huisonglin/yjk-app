package com.yjk.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备出租报名表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:07
 */
@Table(name = "`yjk_device_rent_out_enroll`")
public class DeviceRentOutEnrollDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//设备出租表ID
	private Long deviceRentOutInfoId;
	//报名人ID
	private BigDecimal enrolmentId;
	//1未生效 2已报名 3交易中 4已完成 5申诉中
	private Integer status;
	//流水号
	private String loanNo;
	//订单号
	private String orderNo;
	//定金
	private BigDecimal earnest;
	//定金收取状态
	private Integer stateOfCollection;
	//进场时间
	private Date inTime;
	//施工地点
	private String address;
	//工期
	private String term;
	//意向价格
	private Date expectedPrice;
	//运输费用
	private BigDecimal transportation;
	//税费
	private String taxation;
	//支付方式
	private Integer payWay;
	//设备进场付款
	private String columnEquipmentArrivalPayment14;
	//按月进度付款
	private String monthlyPayment;
	//设备退场结清尾款
	private String exitPayment;
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
	 * 设置：设备出租表ID
	 */
	public void setDeviceRentOutInfoId(Long deviceRentOutInfoId) {
		this.deviceRentOutInfoId = deviceRentOutInfoId;
	}
	/**
	 * 获取：设备出租表ID
	 */
	public Long getDeviceRentOutInfoId() {
		return deviceRentOutInfoId;
	}
	/**
	 * 设置：报名人ID
	 */
	public void setEnrolmentId(BigDecimal enrolmentId) {
		this.enrolmentId = enrolmentId;
	}
	/**
	 * 获取：报名人ID
	 */
	public BigDecimal getEnrolmentId() {
		return enrolmentId;
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
	 * 设置：流水号
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	/**
	 * 获取：流水号
	 */
	public String getLoanNo() {
		return loanNo;
	}
	/**
	 * 设置：订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：定金
	 */
	public void setEarnest(BigDecimal earnest) {
		this.earnest = earnest;
	}
	/**
	 * 获取：定金
	 */
	public BigDecimal getEarnest() {
		return earnest;
	}
	/**
	 * 设置：定金收取状态
	 */
	public void setStateOfCollection(Integer stateOfCollection) {
		this.stateOfCollection = stateOfCollection;
	}
	/**
	 * 获取：定金收取状态
	 */
	public Integer getStateOfCollection() {
		return stateOfCollection;
	}
	/**
	 * 设置：进场时间
	 */
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	/**
	 * 获取：进场时间
	 */
	public Date getInTime() {
		return inTime;
	}
	/**
	 * 设置：施工地点
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：施工地点
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：工期
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * 获取：工期
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * 设置：意向价格
	 */
	public void setExpectedPrice(Date expectedPrice) {
		this.expectedPrice = expectedPrice;
	}
	/**
	 * 获取：意向价格
	 */
	public Date getExpectedPrice() {
		return expectedPrice;
	}
	/**
	 * 设置：运输费用
	 */
	public void setTransportation(BigDecimal transportation) {
		this.transportation = transportation;
	}
	/**
	 * 获取：运输费用
	 */
	public BigDecimal getTransportation() {
		return transportation;
	}
	/**
	 * 设置：税费
	 */
	public void setTaxation(String taxation) {
		this.taxation = taxation;
	}
	/**
	 * 获取：税费
	 */
	public String getTaxation() {
		return taxation;
	}
	/**
	 * 设置：支付方式
	 */
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	/**
	 * 获取：支付方式
	 */
	public Integer getPayWay() {
		return payWay;
	}
	/**
	 * 设置：设备进场付款
	 */
	public void setColumnEquipmentArrivalPayment14(String columnEquipmentArrivalPayment14) {
		this.columnEquipmentArrivalPayment14 = columnEquipmentArrivalPayment14;
	}
	/**
	 * 获取：设备进场付款
	 */
	public String getColumnEquipmentArrivalPayment14() {
		return columnEquipmentArrivalPayment14;
	}
	/**
	 * 设置：按月进度付款
	 */
	public void setMonthlyPayment(String monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	/**
	 * 获取：按月进度付款
	 */
	public String getMonthlyPayment() {
		return monthlyPayment;
	}
	/**
	 * 设置：设备退场结清尾款
	 */
	public void setExitPayment(String exitPayment) {
		this.exitPayment = exitPayment;
	}
	/**
	 * 获取：设备退场结清尾款
	 */
	public String getExitPayment() {
		return exitPayment;
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
