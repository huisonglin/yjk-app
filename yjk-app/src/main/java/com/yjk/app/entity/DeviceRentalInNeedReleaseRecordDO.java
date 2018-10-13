package com.yjk.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 发布工程记录表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:36
 */
@Table(name = "`yjk_device_rental_in_need_release_record`")
public class DeviceRentalInNeedReleaseRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//求租表ID
	private Long deviceRentalInNeedInfoId;
	//定金
	private BigDecimal earnest;
	//1未支付 2已支付3已退还
	private Integer status;
	//订单号
	private String orderNo;
	//流水号
	private String loanNo;
	//1微信 2支付宝
	private Integer payWay;
	//用户id
	private Long memberId;
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
	 * 设置：求租表ID
	 */
	public void setDeviceRentalInNeedInfoId(Long deviceRentalInNeedInfoId) {
		this.deviceRentalInNeedInfoId = deviceRentalInNeedInfoId;
	}
	/**
	 * 获取：求租表ID
	 */
	public Long getDeviceRentalInNeedInfoId() {
		return deviceRentalInNeedInfoId;
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
	 * 设置：1未支付 2已支付3已退还
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1未支付 2已支付3已退还
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：1微信 2支付宝
	 */
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	/**
	 * 获取：1微信 2支付宝
	 */
	public Integer getPayWay() {
		return payWay;
	}
	/**
	 * 设置：用户id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getMemberId() {
		return memberId;
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
