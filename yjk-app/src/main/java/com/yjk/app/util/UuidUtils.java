/**   
 * @Title: UUID.java 
 * @Package com.bootdo.common.utils 
 * @author userLi  
 * @date 2017年10月17日 下午4:04:46  
*/
package com.yjk.app.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * @ClassName: UUID  
 * @author userLi
 * @date 2017年10月17日 下午4:04:46 
*/

public class UuidUtils {
	
	/**
	 * 生成32位随机加密字符串
	 * @Title: get32UUID  
	 * @param @return   
	 * @author userLi   
	 * @return String   
	 * @date 2017年10月17日 下午4:11:22  
	 * @throws
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
	
	
	/**
	 * 验证邮箱
	 * @Title: checkEmail  
	 * @param @param email
	 * @param @return   
	 * @author userLi   
	 * @return boolean   
	 * @date 2017年10月17日 下午4:18:16  
	 * @throws
	 */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号
	  * @Title: checkMobileNumber  
	  * @param @param mobileNumber
	  * @param @return   
	  * @author userLi   
	  * @return boolean   
	  * @date 2017年10月17日 下午4:18:27  
	  * @throws
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	 public synchronized static String getTimeNum() {
			StringBuffer sb = new StringBuffer();
			Calendar cal = new GregorianCalendar();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int date = cal.get(Calendar.DATE);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			sb.append(year);
			if (month < 10) {
				sb.append("0" + month);
			} else {
				sb.append(month);
			}
			if (date < 10) {
				sb.append("0" + date);
			} else {
				sb.append(date);
			}
			if (hour < 10) {
				sb.append("0" + hour);
			} else {
				sb.append(hour);
			}
			if (minute < 10) {
				sb.append("0" + minute);
			} else {
				sb.append(minute);
			}
			if (second < 10) {
				sb.append("0" + second);
			} else {
				sb.append(second);
			}
			return sb.toString();
		}
	 
	 
	 
	
}	
