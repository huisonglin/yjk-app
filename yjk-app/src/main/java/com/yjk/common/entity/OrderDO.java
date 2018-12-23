package com.yjk.common.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_order`")
public class OrderDO {
	   
	 //主键ID
	  @Id
	  @KeySql(useGeneratedKeys=true)
      private  Long id;
     
	 //用户ID
      private  Long memberId;
     
	 //金额
      private  BigDecimal money;
     
	 //订单号
      private  String orderNo;
     
	 //交易号
      private  String transactionId;
     
	 //类型
      private  Integer type;
     
	 //信息ID
      private  Long infoId;
     
	 //1待支付  2已支付 3已退款
      private  Integer status;
     
	 //支付时间
      private  Date payTime;
     
	 //退款时间
      private  Date refundTime;
     
	 //创建时间
      private  Date createTime;
     
	 //更新时间
      private  Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
     
      
      
     
    
}