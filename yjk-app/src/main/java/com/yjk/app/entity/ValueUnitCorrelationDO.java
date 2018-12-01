package com.yjk.app.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;


@Table(name = "`yjk_value_unit_correlation`")
public class ValueUnitCorrelationDO {

	 //主键ID
    @Id
    @KeySql(useGeneratedKeys=true)
    private  Long id;
   
	 //设备出租信息表ID
    private  Long infoId;
   
	 //价格
    private  Double price;
   
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
   
    public  Long  getInfoId(){
    		return  this.infoId;
    };
    public  void  setInfoId(Long infoId){
    	this.infoId=infoId;
    }  
   
    public  Double  getPrice(){
    		return  this.price;
    };
    public  void  setPrice(Double price){
    	this.price=price;
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
