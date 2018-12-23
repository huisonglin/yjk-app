package com.yjk.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;



/**
 * 会员表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 11:45:05
 */
@Table(name = "`yjk_member`")
public class MemberDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会员ID
	@Id
	@KeySql(useGeneratedKeys=true)
	private Long id;
	//用户昵称
	private String nickName;
	//用户头像
	private String headImage;
	//密码
	private String password;
	//手机号
	private String moble;
	//小程序的openId
	private String xcxOpenId;
	//1默认
	private Integer type;
	//我的邀请码
	private String inviteCode;
	//企业认证
	private Integer corporageCertification;
	//实名认证
	private Integer persionCertification;
	//邀请人ID
	private Long inviterId;
	//0禁用 1启用
	private Integer status;
	//公司名称
	private String companyname;
	//登录次数
	private Integer loginCount;
	//聊天ID
	private String talkId;
	//信用分
	private Integer creditScore;
	//注册时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//剩余拨打次数
	private Integer remainCallCount;
	
	

	
	public Integer getRemainCallCount() {
		return remainCallCount;
	}
	public void setRemainCallCount(Integer remainCallCount) {
		this.remainCallCount = remainCallCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 设置：会员ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：会员ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：用户头像
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	/**
	 * 获取：用户头像
	 */
	public String getHeadImage() {
		return headImage;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：手机号
	 */
	public void setMoble(String moble) {
		this.moble = moble;
	}
	/**
	 * 获取：手机号
	 */
	public String getMoble() {
		return moble;
	}
	/**
	 * 设置：小程序的openId
	 */
	public void setXcxOpenId(String xcxOpenId) {
		this.xcxOpenId = xcxOpenId;
	}
	/**
	 * 获取：小程序的openId
	 */
	public String getXcxOpenId() {
		return xcxOpenId;
	}
	/**
	 * 设置：1默认
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1默认
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：我的邀请码
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 获取：我的邀请码
	 */
	public String getInviteCode() {
		return inviteCode;
	}
	/**
	 * 设置：企业认证
	 */
	public void setCorporageCertification(Integer corporageCertification) {
		this.corporageCertification = corporageCertification;
	}
	/**
	 * 获取：企业认证
	 */
	public Integer getCorporageCertification() {
		return corporageCertification;
	}
	/**
	 * 设置：实名认证
	 */
	public void setPersionCertification(Integer persionCertification) {
		this.persionCertification = persionCertification;
	}
	/**
	 * 获取：实名认证
	 */
	public Integer getPersionCertification() {
		return persionCertification;
	}
	/**
	 * 设置：邀请人ID
	 */
	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}
	/**
	 * 获取：邀请人ID
	 */
	public Long getInviterId() {
		return inviterId;
	}
	/**
	 * 设置：0禁用 1启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0禁用 1启用
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：登录次数
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 * 获取：登录次数
	 */
	public Integer getLoginCount() {
		return loginCount;
	}
	/**
	 * 设置：聊天ID
	 */
	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}
	/**
	 * 获取：聊天ID
	 */
	public String getTalkId() {
		return talkId;
	}
	/**
	 * 设置：信用分
	 */
	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}
	/**
	 * 获取：信用分
	 */
	public Integer getCreditScore() {
		return creditScore;
	}
	/**
	 * 设置：注册时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：注册时间
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
