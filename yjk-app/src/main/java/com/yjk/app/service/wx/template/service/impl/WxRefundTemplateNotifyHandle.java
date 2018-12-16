package com.yjk.app.service.wx.template.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.core.JmsTemplate;
import com.alibaba.fastjson.JSON;
import com.yjk.app.common.Constants;
import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.RefundTemplateInfoVO;
import com.yjk.app.dto.TemplateDTO;
import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;
import com.yjk.app.util.PayUtil;


@NotificationType(type = 2)
public class WxRefundTemplateNotifyHandle implements WxTemplateNotify{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	WeiXinConfig weiXinConfig;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@Autowired
	PayUtil payUtil;
	
	@Override
	public void doSendTemplateMessage(NotifyRequest request) throws Exception {
		TemplateDTO templateDTO = new TemplateDTO();
		RefundTemplateInfoVO refundTemplateInfoVO = request.getRefundTemplateInfoVO();
		String prepayId = valueOperations.get(Constants.PREPAY_ID+refundTemplateInfoVO.getOrderNo());

		templateDTO.setTemplate_id("aDOWlVPWMnGCWg-vbDeUjwTuzVIr4dtA5l52jXN321A");
		templateDTO.setForm_id(prepayId);
		templateDTO.setTouser(refundTemplateInfoVO.getOpenId());
		templateDTO.setPage(weiXinConfig.getXcxPage()); //小程序的路径
		templateDTO.setAccess_token(payUtil.xcxAccessToken());
		Map<String, Map<String, String>> data = new HashMap<>();
		
		
		BigDecimal money = new BigDecimal(refundTemplateInfoVO.getRefundMoeny()).divide(new BigDecimal("100"), 2,BigDecimal.ROUND_HALF_UP);
		Map<String, String> keyword1vlaue = new HashMap<>(); //金额
		keyword1vlaue.put("value", "￥"+money.toString()+"元");
		data.put("keyword1",keyword1vlaue);
		
		
		Map<String, String> keyword2vlaue = new HashMap<>(); //商品名称
		keyword2vlaue.put("value", refundTemplateInfoVO.getGoodzName());
		data.put("keyword2", keyword2vlaue);
		
		
		Map<String, String> keyword3vlaue = new HashMap<>(); //订单号
		keyword3vlaue.put("value", refundTemplateInfoVO.getOrderNo());
		data.put("keyword3", keyword3vlaue);
		
		
		Map<String, String> keyword4vlaue = new HashMap<>();
			keyword4vlaue.put("value",refundTemplateInfoVO.getReason());//退款原因
			data.put("keyword4", keyword4vlaue);


		
		Map<String, String> keyword5vlaue = new HashMap<>(); //微信提示
		keyword5vlaue.put("value", refundTemplateInfoVO.getKindlyReminder());
		data.put("keyword5", keyword5vlaue);

		templateDTO.setData(data);
		
		templateDTO.setEmphasis_keyword("keyword1.DATA"); //该字段放大
		logger.info(JSON.toJSONString(templateDTO));
		jmsTemplate.convertAndSend(new ActiveMQQueue("xcxTmeplateNotify"),JSON.toJSONString(templateDTO));
	}

}
