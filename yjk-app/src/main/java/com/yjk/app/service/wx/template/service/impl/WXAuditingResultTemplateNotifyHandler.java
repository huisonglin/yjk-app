package com.yjk.app.service.wx.template.service.impl;

import java.text.SimpleDateFormat;
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
import com.yjk.app.vo.AuditingResultVO;
import com.yjk.common.dao.MemberMapper;
import com.yjk.common.entity.MemberDO;

@NotificationType(type = 5) //审核结果通知
public class WXAuditingResultTemplateNotifyHandler implements WxTemplateNotify{

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
		 AuditingResultVO auditingResultVO = request.getAuditingResultVO();
		templateDTO.setTemplate_id("x3_3aAH8F8DbAl3ne4s5eTztxU5yOyFmoKG9R0KgluY");
		
		MemberDO memberDO = memberMapper.selectByPrimaryKey(auditingResultVO.getMemberId());
		templateDTO.setMemberId(auditingResultVO.getMemberId());
		templateDTO.setTouser(memberDO.getXcxOpenId());
		templateDTO.setPage(auditingResultVO.getXcxPage()); //小程序的路径
		templateDTO.setAccess_token(payUtil.xcxAccessToken());
		Map<String, Map<String, String>> data = new HashMap<>();
		
		//机械名称
		Map<String, String> keyword1vlaue = new HashMap<>(); //金额
		keyword1vlaue.put("value", auditingResultVO.getDeviceName());
		data.put("keyword1",keyword1vlaue);
		
		//审核时间
		Map<String, String> keyword2vlaue = new HashMap<>(); 
		String time = DatetimeUtil.getStringDateShort(auditingResultVO.getAuditingTime());
		keyword2vlaue.put("value", time);
		data.put("keyword2", keyword2vlaue);
		
		//审核结果
		Map<String, String> keyword3vlaue = new HashMap<>(); //订单号
		keyword3vlaue.put("value", auditingResultVO.getAuditingReulst());
		data.put("keyword3", keyword3vlaue);
		
		//审核结果
		Map<String, String> keyword4vlaue = new HashMap<>(); //订单号
		keyword4vlaue.put("value", auditingResultVO.getRemark());
		data.put("keyword4", keyword4vlaue);
		
		templateDTO.setData(data);
		
		JmsUtil.sendTemplateMsg(new ActiveMQQueue("xcxTmeplateNotify"),templateDTO);
		
	}

}
