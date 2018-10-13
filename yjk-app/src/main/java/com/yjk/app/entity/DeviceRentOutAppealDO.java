package com.yjk.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 设备出租申诉表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:43:56
 */
@Table(name = "`yjk_device_rent_out_appeal`")
public class DeviceRentOutAppealDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private String id;
	//发布人ID
	private String issuerId;
	//设备出租信息表ID
	private String deviceRentOutInfoId;
	//发布人申诉状态
	private String issuerAppealState;
	//发布人申诉内容
	private String issuerAppealContent;
	//报名人ID
	private String enrolmentId;
	//设备出租报名表ID
	private String deviceRentOutEnrollId;
	//报名人申诉状态
	private String enrolmentAppealState;
	//报名人申诉内容
	private String enrolmentAppealContent;
	//创建时间
	private String createTime;
	//更新时间
	private String updateTime;

	/**
	 * 设置：主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：发布人ID
	 */
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	/**
	 * 获取：发布人ID
	 */
	public String getIssuerId() {
		return issuerId;
	}
	/**
	 * 设置：设备出租信息表ID
	 */
	public void setDeviceRentOutInfoId(String deviceRentOutInfoId) {
		this.deviceRentOutInfoId = deviceRentOutInfoId;
	}
	/**
	 * 获取：设备出租信息表ID
	 */
	public String getDeviceRentOutInfoId() {
		return deviceRentOutInfoId;
	}
	/**
	 * 设置：发布人申诉状态
	 */
	public void setIssuerAppealState(String issuerAppealState) {
		this.issuerAppealState = issuerAppealState;
	}
	/**
	 * 获取：发布人申诉状态
	 */
	public String getIssuerAppealState() {
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
	public void setEnrolmentId(String enrolmentId) {
		this.enrolmentId = enrolmentId;
	}
	/**
	 * 获取：报名人ID
	 */
	public String getEnrolmentId() {
		return enrolmentId;
	}
	/**
	 * 设置：设备出租报名表ID
	 */
	public void setDeviceRentOutEnrollId(String deviceRentOutEnrollId) {
		this.deviceRentOutEnrollId = deviceRentOutEnrollId;
	}
	/**
	 * 获取：设备出租报名表ID
	 */
	public String getDeviceRentOutEnrollId() {
		return deviceRentOutEnrollId;
	}
	/**
	 * 设置：报名人申诉状态
	 */
	public void setEnrolmentAppealState(String enrolmentAppealState) {
		this.enrolmentAppealState = enrolmentAppealState;
	}
	/**
	 * 获取：报名人申诉状态
	 */
	public String getEnrolmentAppealState() {
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
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public String getUpdateTime() {
		return updateTime;
	}
}
