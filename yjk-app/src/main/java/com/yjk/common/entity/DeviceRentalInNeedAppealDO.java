package com.yjk.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 工程求租申诉表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:20
 */
@Table(name = "`yjk_device_rental_in_need_appeal`")
public class DeviceRentalInNeedAppealDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//发布人ID
	private Long issuerId;
	//工程求租信息表ID
	private Long deviceRentalInNeedInfoId;
	//发布人申诉状态
	private Integer issuerAppealState;
	//发布人申诉内容
	private String issuerAppealContent;
	//报名人ID
	private Long enrolmentId;
	//工程求租报名表ID
	private Long deviceRentalInNeedEnrollId;
	//报名人申诉状态
	private Integer enrolmentAppealState;
	//报名人申诉内容
	private String enrolmentAppealContent;
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
	 * 设置：工程求租信息表ID
	 */
	public void setDeviceRentalInNeedInfoId(Long deviceRentalInNeedInfoId) {
		this.deviceRentalInNeedInfoId = deviceRentalInNeedInfoId;
	}
	/**
	 * 获取：工程求租信息表ID
	 */
	public Long getDeviceRentalInNeedInfoId() {
		return deviceRentalInNeedInfoId;
	}
	/**
	 * 设置：发布人申诉状态
	 */
	public void setIssuerAppealState(Integer issuerAppealState) {
		this.issuerAppealState = issuerAppealState;
	}
	/**
	 * 获取：发布人申诉状态
	 */
	public Integer getIssuerAppealState() {
		return issuerAppealState;
	}
	/**
	 * 设置：发布人申诉内容
	 */
	public void setIssuerAppealContent(String issuerAppealContent) {
		this.issuerAppealContent = issuerAppealContent;
	}
	/**
	 * 获取：发布人申诉内容
	 */
	public String getIssuerAppealContent() {
		return issuerAppealContent;
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
	 * 设置：报名人申诉状态
	 */
	public void setEnrolmentAppealState(Integer enrolmentAppealState) {
		this.enrolmentAppealState = enrolmentAppealState;
	}
	/**
	 * 获取：报名人申诉状态
	 */
	public Integer getEnrolmentAppealState() {
		return enrolmentAppealState;
	}
	/**
	 * 设置：报名人申诉内容
	 */
	public void setEnrolmentAppealContent(String enrolmentAppealContent) {
		this.enrolmentAppealContent = enrolmentAppealContent;
	}
	/**
	 * 获取：报名人申诉内容
	 */
	public String getEnrolmentAppealContent() {
		return enrolmentAppealContent;
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
