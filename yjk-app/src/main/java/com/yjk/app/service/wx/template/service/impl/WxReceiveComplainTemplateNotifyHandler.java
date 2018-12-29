package com.yjk.app.service.wx.template.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yjk.app.common.Constants;
import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.TemplateDTO;
import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;
import com.yjk.app.util.PayUtil;
import com.yjk.app.vo.ReceiveComplainVO;
import com.yjk.common.dao.MemberMapper;
import com.yjk.common.entity.MemberDO;

@NotificationType(type = 4)
public class WxReceiveComplainTemplateNotifyHandler implements WxTemplateNotify{

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	PayUtil payUtil;
	
	@Autowired
	WeiXinConfig weiXinConfig;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public void doSendTemplateMessage(NotifyRequest request) throws Exception {
		try {
			TemplateDTO templateDTO = new TemplateDTO();
			ReceiveComplainVO receiveComplainVO = request.getReceiveComplainVO();
			String formId = valueOperations.get(Constants.FORM_ID+receiveComplainVO.getMemberId());
			templateDTO.setTemplate_id("EFj3q2pjuoPEN4yo1mERKGO9cXkJMNr1d43rYbFhGjI");
			templateDTO.setForm_id(formId);
			
			MemberDO memberDO = memberMapper.selectByPrimaryKey(receiveComplainVO.getMemberId());
			templateDTO.setTouser(memberDO.getXcxOpenId());
			templateDTO.setPage(weiXinConfig.getXcxPage()); //小程序的路径
			templateDTO.setAccess_token(payUtil.xcxAccessToken());
			Map<String, Map<String, String>> data = new HashMap<>();
			
			//投诉时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, String> keyword1vlaue = new HashMap<>(); 
			keyword1vlaue.put("value", sdf.format(receiveComplainVO.getComplainTime()));
			data.put("keyword1",keyword1vlaue);
			
			//投诉原因
			Map<String, String> keyword2vlaue = new HashMap<>(); 
			keyword2vlaue.put("value", receiveComplainVO.getReason());
			data.put("keyword2", keyword2vlaue);
			
			//提醒
			Map<String, String> keyword3vlaue = new HashMap<>(); 
			keyword3vlaue.put("value", receiveComplainVO.getReason());
			data.put("keyword3", keyword3vlaue);
			
			templateDTO.setData(data);
			
			jmsTemplate.convertAndSend(new ActiveMQQueue("xcxTmeplateNotify"),JSON.toJSONString(templateDTO));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
