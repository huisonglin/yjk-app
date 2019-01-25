/**
 * 
 */package com.yjk.app.activemq.consumer;
/** 
* @author : 刘尊亮
* @date 创建时间：2019年1月25日 下午4:12:50 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
/**
 * @author Administrator
 *
 */

import javax.jms.Destination;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yjk.app.dto.TemplateDTO;
import com.yjk.app.interceptor.XcxFormIdInterceptor;

@Component
public class JmsUtil {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ListOperations<String, Object> listOperations;
	
	public void sendTemplateMsg(Destination destination ,TemplateDTO templateDTO) {
		if (templateDTO.getForm_id() != null) {
			String formId = (String)listOperations.leftPop(XcxFormIdInterceptor.FORM_ID+templateDTO.getMemberId());
			if (StringUtils.isNotBlank(formId)) {
				templateDTO.setForm_id(formId);
				jmsTemplate.convertAndSend(destination, JSON.toJSONString(templateDTO));
			}

		}

	}
}
