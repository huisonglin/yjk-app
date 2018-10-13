package com.yjk.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 签约表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:41
 */
@Table(name = "`yjk_device_sale_contracted`")
public class DeviceSaleContractedDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//设备出售表ID
	private Long deviceSaleInfoId;
	//设备出售报名表ID
	private Long deviceSaleEnrollId;
	//出售人ID
	private Long issuerId;
	//报名人ID
	private BigDecimal enrolmentId;
	//签约金额
	private BigDecimal money;
	//流水号
	private String loanNo;
	//支付方式
	private Integer payWay;
	//订单号
	private String orderNo;
	//备注
	private String remark;
	//状态
	private Integer status;
	//更新时间
	private Long createTime;
	//创建时间
	private Long updateTime;

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
	 * 设置：设备出售表ID
	 */
	public void setDeviceSaleInfoId(Long deviceSaleInfoId) {
		this.deviceSaleInfoId = deviceSaleInfoId;
	}
	/**
	 * 获取：设备出售表ID
	 */
	public Long getDeviceSaleInfoId() {
		return deviceSaleInfoId;
	}
	/**
	 * 设置：设备出售报名表ID
	 */
	public void setDeviceSaleEnrollId(Long deviceSaleEnrollId) {
		this.deviceSaleEnrollId = deviceSaleEnrollId;
	}
	/**
	 * 获取：设备出售报名表ID
	 */
	public Long getDeviceSaleEnrollId() {
		return deviceSaleEnrollId;
	}
	/**
	 * 设置：出售人ID
	 */
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	/**
	 * 获取：出售人ID
	 */
	public Long getIssuerId() {
		return issuerId;
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
	 * 设置：更新时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}
}
