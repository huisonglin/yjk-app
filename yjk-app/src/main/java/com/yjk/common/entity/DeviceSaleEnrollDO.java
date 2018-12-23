package com.yjk.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备出售报名表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:43
 */
@Table(name = "`yjk_device_sale_enroll`")
public class DeviceSaleEnrollDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//设备出售表ID
	private Long deviceSaleInfoId;
	//报名人ID
	private Long enrolmentId;
	//1未生效 2已报名 3已签约（交易中） 4已完成 5申诉中
	private Integer status;
	//是否选择担保
	private Integer isGuarantee;
	//联系人
	private String linkman;
	//联系电话
	private String contactNumber;
	//收取状态
	private Integer stateOfCollection;
	//担保费
	private BigDecimal guaranteeFee;
	//更新时间
	private Date updateTime;
	//创建时间
	private Date createTime;

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
	 * 设置：1未生效 2已报名 3已签约（交易中） 4已完成 5申诉中
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1未生效 2已报名 3已签约（交易中） 4已完成 5申诉中
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否选择担保
	 */
	public void setIsGuarantee(Integer isGuarantee) {
		this.isGuarantee = isGuarantee;
	}
	/**
	 * 获取：是否选择担保
	 */
	public Integer getIsGuarantee() {
		return isGuarantee;
	}
	/**
	 * 设置：联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * 获取：联系人
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * 设置：联系电话
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * 获取：联系电话
	 */
	public String getContactNumber() {
		return contactNumber;
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
	 * 设置：担保费
	 */
	public void setGuaranteeFee(BigDecimal guaranteeFee) {
		this.guaranteeFee = guaranteeFee;
	}
	/**
	 * 获取：担保费
	 */
	public BigDecimal getGuaranteeFee() {
		return guaranteeFee;
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
}
