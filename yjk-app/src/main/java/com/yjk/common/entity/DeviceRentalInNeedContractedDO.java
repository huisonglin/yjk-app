package com.yjk.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 工程求租签约表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:23
 */
@Table(name = "`yjk_device_rental_in_need_contracted`")
public class DeviceRentalInNeedContractedDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//工程求租表ID
	private Long deviceRentalInNeedInfoId;
	//发布人ID
	private Long issuerId;
	//工程求租报名表ID
	private Long deviceRentalInNeedEnrollId;
	//报名人ID
	private Long enrolmentId;
	//签约金额
	private BigDecimal money;
	//订单号
	private String orderNo;
	//流水号
	private String loanId;
	//支付方式
	private Integer payWay;
	//状态
	private Integer status;
	//备注
	private String remark;
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
	 * 设置：发布人ID
	 */
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	/**
	 * 获取：发布人ID
	 */
	public Long getIssuerId() {
		return issuerId;
	}
	/**
	 * 设置：工程求租报名表ID
	 */
	public void setDeviceRentalInNeedEnrollId(Long deviceRentalInNeedEnrollId) {
		this.deviceRentalInNeedEnrollId = deviceRentalInNeedEnrollId;
	}
	/**
	 * 获取：工程求租报名表ID
	 */
	public Long getDeviceRentalInNeedEnrollId() {
		return deviceRentalInNeedEnrollId;
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
	 * 设置：签约金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：签约金额
	 */
	public BigDecimal getMoney() {
		return money;
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
	 * 设置：流水号
	 */
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	/**
	 * 获取：流水号
	 */
	public String getLoanId() {
		return loanId;
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
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
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
