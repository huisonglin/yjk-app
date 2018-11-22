package com.yjk.app.service.wx.template.strategy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;

@Component
public class TemplateMessageStragegy{

	@Autowired
	Map<String, Object> beansWithAnnotationMap;
	
	public void excute(NotifyRequest request) {
		WxTemplateNotify wxTemplateNotify=null;
	    for(Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()){  
	    	
	    	Object value = entry.getValue();
	    	System.out.println(value.getClass());
	    	NotificationType annotation = value.getClass().getAnnotation(NotificationType.class);
	    	Integer type = annotation.type();
	    	if(request.getType() == type) {
	    		wxTemplateNotify = (WxTemplateNotify)value;
	    		break;
	    	}
	    }    
	    if(wxTemplateNotify != null) {
	    	wxTemplateNotify.doSendTemplateMessage(request);
	    }
	}

}
