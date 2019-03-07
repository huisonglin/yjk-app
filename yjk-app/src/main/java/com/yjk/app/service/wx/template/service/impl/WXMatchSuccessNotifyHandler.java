/**
 * 
 */package com.yjk.app.service.wx.template.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjk.app.activemq.consumer.JmsUtil;
import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.TemplateDTO;
import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;
import com.yjk.app.util.DatetimeUtil;
import com.yjk.app.util.PayUtil;
import com.yjk.app.vo.MatchSuccessVO;
import com.yjk.common.dao.MemberMapper;

/** 
* @author : 刘尊亮
* @date 创建时间：2019年2月26日 下午4:20:49 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */
 @NotificationType(type = 6)
public class WXMatchSuccessNotifyHandler  implements WxTemplateNotify{

		@Autowired
		MemberMapper memberMapper;
		
		@Autowired
		WeiXinConfig weiXinConfig;
		
		@Autowired
		PayUtil payUtil;
		
		@Autowired
		JmsUtil JmsUtil;
		
		@Override
		public void doSendTemplateMessage(NotifyRequest request) throws Exception {
			TemplateDTO templateDTO = new TemplateDTO();
			 MatchSuccessVO matchSuccessVO = request.getMatchSuccessVO();
			templateDTO.setTemplate_id("R_nO55ImI1_0YefMFR9fW5q7ciB34N4WqKvvbZ18ang");
			templateDTO.setMemberId(matchSuccessVO.getMemberId());
			templateDTO.setTouser(matchSuccessVO.getXcxOpenId());
			templateDTO.setPage(matchSuccessVO.getPage()); //小程序的路径
			templateDTO.setAccess_token(payUtil.xcxAccessToken());
			Map<String, Map<String, String>> data = new HashMap<>();
			
			//匹配对象
			Map<String, String> keyword1vlaue = new HashMap<>(); //金额
			keyword1vlaue.put("value", matchSuccessVO.getDeviceName());
			data.put("keyword1",keyword1vlaue);
			
			//地点
			Map<String, String> keyword2vlaue = new HashMap<>(); 
			keyword2vlaue.put("value", matchSuccessVO.getAddress());
			data.put("keyword2", keyword2vlaue);
			
			//审核结果
			Map<String, String> keyword3vlaue = new HashMap<>(); //订单号
			String time = DatetimeUtil.getStringDateShort(matchSuccessVO.getPublishTime());
			keyword3vlaue.put("value", time);
			data.put("keyword3", keyword3vlaue);
			
			//审核结果
			Map<String, String> keyword4vlaue = new HashMap<>(); //订单号
			keyword4vlaue.put("value", matchSuccessVO.getRemark());
			data.put("keyword4", keyword4vlaue);
			
			templateDTO.setData(data);
			
			JmsUtil.sendTemplateMsg(new ActiveMQQueue("xcxTmeplateNotify"),templateDTO);
		}
}
