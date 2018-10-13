package com.yjk.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 工程求租评价表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:44:27
 */
@Table(name = "`yjk_device_rental_in_need_evaluate`")
public class DeviceRentalInNeedEvaluateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//发布人ID
	private Long issuerId;
	//发布人评价内容
	private String issuerContent;
	//发布人评价分
	private Integer issuerScore;
	//发布人评价状态
	private Integer issuerEvaluationState;
	//报名人ID
	private Long enrolmentId;
	//报名人评价内容
	private String enrolmentContent;
	//报名人评价分
	private Integer enrolmentScore;
	//报名人评价状态
	private Integer enrolmentEvaluationState;
	//工程求租信息表ID
	private Long deviceRentalInNeedInfoId;
	//工程求租报名表ID
	private Long deviceRentalInNeedEnrollId;
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
	 * 设置：发布人评价内容
	 */
	public void setIssuerContent(String issuerContent) {
		this.issuerContent = issuerContent;
	}
	/**
	 * 获取：发布人评价内容
	 */
	public String getIssuerContent() {
		return issuerContent;
	}
	/**
	 * 设置：发布人评价分
	 */
	public void setIssuerScore(Integer issuerScore) {
		this.issuerScore = issuerScore;
	}
	/**
	 * 获取：发布人评价分
	 */
	public Integer getIssuerScore() {
		return issuerScore;
	}
	/**
	 * 设置：发布人评价状态
	 */
	public void setIssuerEvaluationState(Integer issuerEvaluationState) {
		this.issuerEvaluationState = issuerEvaluationState;
	}
	/**
	 * 获取：发布人评价状态
	 */
	public Integer getIssuerEvaluationState() {
		return issuerEvaluationState;
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
	 * 设置：报名人评价内容
	 */
	public void setEnrolmentContent(String enrolmentContent) {
		this.enrolmentContent = enrolmentContent;
	}
	/**
	 * 获取：报名人评价内容
	 */
	public String getEnrolmentContent() {
		return enrolmentContent;
	}
	/**
	 * 设置：报名人评价分
	 */
	public void setEnrolmentScore(Integer enrolmentScore) {
		this.enrolmentScore = enrolmentScore;
	}
	/**
	 * 获取：报名人评价分
	 */
	public Integer getEnrolmentScore() {
		return enrolmentScore;
	}
	/**
	 * 设置：报名人评价状态
	 */
	public void setEnrolmentEvaluationState(Integer enrolmentEvaluationState) {
		this.enrolmentEvaluationState = enrolmentEvaluationState;
	}
	/**
	 * 获取：报名人评价状态
	 */
	public Integer getEnrolmentEvaluationState() {
		return enrolmentEvaluationState;
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
