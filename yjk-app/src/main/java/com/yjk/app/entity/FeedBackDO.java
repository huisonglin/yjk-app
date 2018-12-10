package com.yjk.app.entity;

import java.util.Date;

import javax.persistence.Table;

@Table(name = "`yjk_feedback`")
public class FeedBackDO {

	   
		 //主键ID
	      private  Long id;
	     
		 //会员ID
	      private  Long memberId;
	     
		 //反馈内容
	      private  String content;
	     
		 //反馈时间
	      private  Date createTime;
	     
	      
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
	     
	      public  String  getContent(){
	      		return  this.content;
	      };
	      public  void  setContent(String content){
	      	this.content=content;
	      }  
	     
	      public  Date  getCreateTime(){
	      		return  this.createTime;
	      };
	      public  void  setCreateTime(Date createTime){
	      	this.createTime=createTime;
	      }  
	     
}
