package com.yjk.app.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtil {
	
	 /**
	  * 获取现在时间
	  * System.currentTimeMillis()
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	 public static Date getNowDate() {
		  Date currentDate = new Date();
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateStr = sDateFormat.format(currentDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date currentTime = sDateFormat.parse(dateStr, pos);
		  return currentTime;
	}
	 
	 public static Long RemainderOfTheDay() {
	        //方法三:LocalDateTime和ChronoUnit为1.8新增
	        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
	        System.out.println("当天剩余秒3：" + seconds);
	        return seconds;
	 }
	 
	 /**
	  * 获取现在时间
	  * 
	  * @return返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getNowDateShort() {
		  Date currentDate = new Date();
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  String dateStr = sDateFormat.format(currentDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date currentTime;
		  currentTime = sDateFormat.parse(dateStr,pos);
		  return currentTime;	 
	 }
	 
	 /**
	  * 获取前天的日期
	  * 
	  * @return返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getBeforeDayShort() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.add(Calendar.DAY_OF_MONTH, -1);   
		  Date BeforeDate = calendar.getTime();
		  
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  String dateStr = sDateFormat.format(BeforeDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date Beforeday;
		  Beforeday = sDateFormat.parse(dateStr,pos);
			
		  return Beforeday;	 
	 }
	 
	 
	 /**
	  * 获取某一天的下一天的日期
	  * 
	  * @return返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getNextDayShort(Date date) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.DAY_OF_MONTH, 1);   
		  Date NextDate = calendar.getTime();
		  
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  String dateStr = sDateFormat.format(NextDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date nextday;
		  nextday = sDateFormat.parse(dateStr,pos);
		  return nextday;	 
	 }
	 
	 /**
	  * 获取距离今天多长的日期
	  * 正为后几天，负为前几天
	  * @return返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getDistanceDayShort(int num) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.add(Calendar.DAY_OF_MONTH, num);   
		  Date BeforeDate = calendar.getTime();
		  
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  String dateStr = sDateFormat.format(BeforeDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date Beforeday;
		  Beforeday = sDateFormat.parse(dateStr,pos);
			
		  return Beforeday;	 
	 }
	 
	 /**
	  * 获取距离今天多长的日期
	  * 正为后几天，负为前几天
	  * @return返回短时间格式 yyyy-MM-dd HH:mm:ss
	  */
	 public static Date getDistanceMinutet(int num) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.add(Calendar.SECOND, num);   
		  Date BeforeDate = calendar.getTime();
		  
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateStr = sDateFormat.format(BeforeDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date Beforeday;
		  Beforeday = sDateFormat.parse(dateStr,pos);
			
		  return Beforeday;	 
	 }
	 
	 /**
	  * 获取距离a某天多长的日期
	  * 正为后几天，负为前几天
	  * @return返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getDistanceDateShort(Date date,int num) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.DAY_OF_MONTH, num);   
		  Date BeforeDate = calendar.getTime();
		  
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  String dateStr = sDateFormat.format(BeforeDate);
		  ParsePosition pos = new ParsePosition(0);
		  Date Beforeday;
		  Beforeday = sDateFormat.parse(dateStr,pos);
			
		  return Beforeday;	 
	 }
	 
 
	 /**
	  * 获取现在日期
	  * 字符串
	  * yyyyMMdd
	  */
	 public static String getStrCurrentDateShort() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	 }
	 
	 /**
	  * 获取现在时间
	  * 字符串
	  * HHmmss
	  */
	 public static String getStrCurrentTimeShort() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	 }
	 
	 /**
	  * 获取现在时间
	  * 字符串
	  * yyMMdd
	  */
	 public static String getStrDateShort6() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	 }
	 
	 
	 /**
	  * 获取现在时间,含中文
	  * 字符串
	  * 格式：XX年XX月XX日XX时XX分
	  */
	 public static String getStrCurrentDateCh() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	 }
	 
	 /**
	  * 获取距离今天多少天的日期字符串
	  * 正为后几天，负为前几天
	  * @param num
	  * @return返回短时间格式 yyyyMMdd
	  */
	 public static String getStrDistanceDateShort(int num) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.add(Calendar.DAY_OF_MONTH, num);   
		  Date BeforeDate = calendar.getTime();
		  
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
		  String dateStr = sDateFormat.format(BeforeDate);
			
		  return dateStr;	 
	 }
	
	 /**
	  * 获取时间
	  * 字符串
	  * yyyy-MM-dd
	  */
	 public static String getStringDateShort(Date date) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(date);
		  return dateString;
	}
	 
	 /**
	  * 获取时间
	  * 字符串
	  * yyyyMMdd
	  */
	 public static String getStringDateShortFormat(Date date) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		  String dateString = formatter.format(date);
		  return dateString;
	}
	 
	 public static String dateToStr(Date dateStr,String dateFormat){
	      return (new SimpleDateFormat(dateFormat).format(dateStr));
	 }
	 
	 /**
	  * 获取时间字符串
	  * 
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	 public static String getStringDate(Date date) {
		  if(date == null){
			  return "";
		  }
		  SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateStr = sDateFormat.format(date);
		  return dateStr;
	} 
	 
	 /**
	  * 字符串转时间格式
	  * 
	  * @return 返回时间类型 yyyy-MM-dd
	  */
	 public static Date stringToDate(String strDate) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 if (StringUtils.hasText(strDate)) {
				try {
					if (strDate.indexOf(":") == -1 && strDate.length() == 10) {
						return dateFormat.parse(strDate);
					} else{
						return null;
					}
				} catch (ParseException ex) {
					return null;
				}
			} else {
				return null;
			}
	}  
	 
	 /**
	  * 字符串转时间格式
	  * 
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	 public static Date stringToLongDate(String strDate) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if (StringUtils.hasText(strDate)) {
				try {
					if (strDate.indexOf(":") > 0 && strDate.length() == 19) {
						return dateFormat.parse(strDate);
					} else{
						return null;
					}
				} catch (ParseException ex) {
					return null;
				}
			} else {
				return null;
			}
	}

	/**
	 *时间转data String格式
	 *
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */

		public static String longToDate(long longTime) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date = new Date(longTime);
					String str = sdf.format(date);
			return str;
       }

}
