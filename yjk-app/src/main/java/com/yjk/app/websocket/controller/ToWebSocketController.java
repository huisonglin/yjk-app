/**
 * 
 */package com.yjk.app.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author : 刘尊亮
* @date 创建时间：2019年2月18日 下午3:52:45 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */
 @Controller
public class ToWebSocketController {

	 @RequestMapping("app/toSocket")
	 public String toSocket() {
		 return "webSocket/webSocket";
	 }
}
