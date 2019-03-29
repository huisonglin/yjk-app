package com.yjk.common.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_device_collection`")
public class DeviceCollection {
	   
	 //主键
  @Id
  @KeySql(useGeneratedKeys=true)
   private  Long id;
  
	 //会员ID
   private  Long memberId;
  
	 //信息ID
   private  Long infoId;
  
	 //信息类型
   private  Integer infoType;
  
	 //创建时间
   private  Date createTime;
  
	 //更新时间
   private  Date updateTime;
  
   
   public  Long  getId(){
   		return  this.id;
   };
   public  void  setId(Long id){
   	this.id=id;
   }  
  
   public  Long  getMemberId(){
   		return  this.memberId;
   };
   public  void  setMemberId(Long memberId){
   	this.memberId=memberId;
   }  
  
   public  Long  getInfoId(){
   		return  this.infoId;
   };
   public  void  setInfoId(Long infoId){
   	this.infoId=infoId;
   }  
  
   public  Integer  getInfoType(){
   		return  this.infoType;
   };
   public  void  setInfoType(Integer infoType){
   	this.infoType=infoType;
   }  
  
   public  Date  getCreateTime(){
   		return  this.createTime;
   };
   public  void  setCreateTime(Date createTime){
   	this.createTime=createTime;
   }  
  
   public  Date  getUpdateTime(){
   		return  this.updateTime;
   };
   public  void  setUpdateTime(Date updateTime){
   	this.updateTime=updateTime;
   }  
  
}
