package com.yjk.app.service.wx.template.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.core.JmsTemplate;
import com.alibaba.fastjson.JSON;
import com.yjk.app.activemq.consumer.JmsUtil;
import com.yjk.app.common.Constants;
import com.yjk.app.config.WeiXinConfig;
import com.yjk.app.dto.TemplateDTO;
import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;
import com.yjk.app.util.PayUtil;
import com.yjk.app.vo.WxComplainVO;
import com.yjk.common.dao.MemberMapper;
import com.yjk.common.entity.MemberDO;

@NotificationType(type = 3)
public class WxComplainTemplateNotifyHandler implements WxTemplateNotify{

	@Autowired
	JmsUtil  JmsUtil;
	
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
			WxComplainVO wxComplainVO = request.getWxComplainVO();
			String formId = valueOperations.get(Constants.FORM_ID+wxComplainVO.getMemberId());
			templateDTO.setTemplate_id("eVBr3BBMcC5tVVHyTHaLihKtSFsiF_DGuw5FvopIRQM");
			templateDTO.setForm_id(formId);
			
			MemberDO memberDO = memberMapper.selectByPrimaryKey(wxComplainVO.getMemberId());
			templateDTO.setMemberId(wxComplainVO.getMemberId());
			templateDTO.setTouser(memberDO.getXcxOpenId());
			templateDTO.setPage(weiXinConfig.getXcxPage()); //小程序的路径
			templateDTO.setAccess_token(payUtil.xcxAccessToken());
			Map<String, Map<String, String>> data = new HashMap<>();
			
			//投诉内容
			Map<String, String> keyword1vlaue = new HashMap<>(); //金额
			keyword1vlaue.put("value", wxComplainVO.getComplainContent());
			data.put("keyword1",keyword1vlaue);
			
			//投诉结果
			Map<String, String> keyword2vlaue = new HashMap<>(); 
			keyword2vlaue.put("value", wxComplainVO.getComplainResult());
			data.put("keyword2", keyword2vlaue);
			
			//温馨提示
			Map<String, String> keyword3vlaue = new HashMap<>(); //订单号
			keyword3vlaue.put("value", wxComplainVO.getRemark());
			data.put("keyword3", keyword3vlaue);
			
			templateDTO.setData(data);
			
			JmsUtil.sendTemplateMsg(new ActiveMQQueue("xcxTmeplateNotify"),templateDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
