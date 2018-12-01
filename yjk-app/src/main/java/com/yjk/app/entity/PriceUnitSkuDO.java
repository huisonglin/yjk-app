package com.yjk.app.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "`yjk_price_unit_sku`")
public class PriceUnitSkuDO {

	   
	 //主键ID
   @Id
   @KeySql(useGeneratedKeys=true)
   private  Long id;
  
	 //单位名称
   private  String unitName;
  
	 //燃油负责方
   private  String fuelManager;
  
	 //排序
   private  Long sort;
  
	 //状态
   private  Long status;
  
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
  
   public  String  getUnitName(){
   		return  this.unitName;
   };
   public  void  setUnitName(String unitName){
   	this.unitName=unitName;
   }  
  
   public  String  getFuelManager(){
   		return  this.fuelManager;
   };
   public  void  setFuelManager(String fuelManager){
   	this.fuelManager=fuelManager;
   }  
  
   public  Long  getSort(){
   		return  this.sort;
   };
   public  void  setSort(Long sort){
   	this.sort=sort;
   }  
  
   public  Long  getStatus(){
   		return  this.status;
   };
   public  void  setStatus(Long status){
   	this.status=status;
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
