package com.yjk.app.service.wx.template.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.core.JmsTemplate;

import com.alibaba.fastjson.JSON;
import com.yjk.app.common.Constants;
import com.yjk.app.dto.TemplateDTO;
import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;
import com.yjk.app.util.JSONUtil;
import com.yjk.app.vo.UnifiedorderAttachVO;
import com.yjk.app.vo.XcxPayNotifyInfoVO;

/**
 * 付费通知模板
 * @author huisonglin
 *
 */
@NotificationType(type = 1)
public class WxPayTemplateNotifyHandle implements WxTemplateNotify{

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	@Override
	public void doSendTemplateMessage(NotifyRequest request) {
		TemplateDTO templateDTO = new TemplateDTO();
		XcxPayNotifyInfoVO xcxPayNotifyInfoVO = request.getXcxPayNotifyInfoVO();
		String prepayId = valueOperations.get(Constants.PREPAY_ID+xcxPayNotifyInfoVO.getOut_trade_no());
		templateDTO.setEmphasis_keyword("keyword1");
		templateDTO.setTemplate_id("zy0h2iLzUeSgCH12DPMPTM011GT4Dk5FQ1W6KVYsamI");
		templateDTO.setForm_id(prepayId);
		templateDTO.setTouser(xcxPayNotifyInfoVO.getOpenid());
		Map<String, Map<String, String>> data = new HashMap<>();
		BigDecimal money = new BigDecimal(xcxPayNotifyInfoVO.getTotal_fee()).divide(new BigDecimal("100"), 2,BigDecimal.ROUND_HALF_UP);
		
		
		Map<String, String> keyword1vlaue = new HashMap<>();
		keyword1vlaue.put("value", money.toString());
		data.put("keyword1",keyword1vlaue);
		
		String attach = xcxPayNotifyInfoVO.getAttach();
		UnifiedorderAttachVO unifiedorderAttachVO = JSONUtil.parse(attach, UnifiedorderAttachVO.class);
		
		Map<String, String> keyword2vlaue = new HashMap<>();
		keyword2vlaue.put("value", unifiedorderAttachVO.getGoodzName());
		data.put("keyword2", keyword2vlaue);
		
		
		Map<String, String> keyword3vlaue = new HashMap<>();
		keyword3vlaue.put("value", xcxPayNotifyInfoVO.getOut_trade_no());
		data.put("keyword3", keyword3vlaue);
		
		
		Map<String, String> keyword4vlaue = new HashMap<>();
		String format;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(xcxPayNotifyInfoVO.getTime_end());
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format = sdf2.format(date);
			keyword4vlaue.put("value",format);
			data.put("keyword4", keyword4vlaue);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		Map<String, String> keyword5vlaue = new HashMap<>();
		keyword5vlaue.put("value", unifiedorderAttachVO.getKindlyReminder());
		data.put("keyword5", keyword5vlaue);

		templateDTO.setData(data);
		jmsTemplate.convertAndSend(new ActiveMQQueue("xcxTmeplateNotify"),JSON.toJSONString(templateDTO));
	}



	
}
