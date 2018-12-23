package com.yjk.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 会员信息详情表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:45:08
 */
@Table(name = "`yjk_member_info`")
public class MemberInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//会员ID
	private Long memberId;
	//身份证正面
	private String frontOfIdentityCard;
	//身份证反面
	private String backOfIdentityCard;
	//营业执照图片
	private String businessLicence;
	//公司名称
	private String companyname;
	//法人姓名
	private String legalPersonName;
	//法人身份证
	private String legalPersonIdcard;
	//法人手机号
	private String legalPersonMobile;
	//会员年龄
	private Integer age;
	//所属地区
	private String address;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
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
	 * 设置：身份证正面
	 */
	public void setFrontOfIdentityCard(String frontOfIdentityCard) {
		this.frontOfIdentityCard = frontOfIdentityCard;
	}
	/**
	 * 获取：身份证正面
	 */
	public String getFrontOfIdentityCard() {
		return frontOfIdentityCard;
	}
	/**
	 * 设置：身份证反面
	 */
	public void setBackOfIdentityCard(String backOfIdentityCard) {
		this.backOfIdentityCard = backOfIdentityCard;
	}
	/**
	 * 获取：身份证反面
	 */
	public String getBackOfIdentityCard() {
		return backOfIdentityCard;
	}
	/**
	 * 设置：营业执照图片
	 */
	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}
	/**
	 * 获取：营业执照图片
	 */
	public String getBusinessLicence() {
		return businessLicence;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyname() {
		return companyname;
	}
	/**
	 * 设置：法人姓名
	 */
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	/**
	 * 获取：法人姓名
	 */
	public String getLegalPersonName() {
		return legalPersonName;
	}
	/**
	 * 设置：法人身份证
	 */
	public void setLegalPersonIdcard(String legalPersonIdcard) {
		this.legalPersonIdcard = legalPersonIdcard;
	}
	/**
	 * 获取：法人身份证
	 */
	public String getLegalPersonIdcard() {
		return legalPersonIdcard;
	}
	/**
	 * 设置：法人手机号
	 */
	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
	}
	/**
	 * 获取：法人手机号
	 */
	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}
	/**
	 * 设置：会员年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：会员年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：所属地区
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：所属地区
	 */
	public String getAddress() {
		return address;
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
