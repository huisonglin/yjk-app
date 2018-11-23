package com.yjk.app.service.wx.template.strategy;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.yjk.app.service.wx.template.annotation.NotificationType;
import com.yjk.app.service.wx.template.request.NotifyRequest;
import com.yjk.app.service.wx.template.service.WxTemplateNotify;

@Component
public class TemplateMessageStragegy{

	
	@Resource(name = "beansWithAnnotationMap")
	Map<String, Object> beansWithAnnotationMap;
	
	public void excute(NotifyRequest request) {
		WxTemplateNotify wxTemplateNotify=null;
		//Map<String, Object> beansWithAnnotationMap = this.applicationContext.getBeansWithAnnotation(NotificationType.class); 
	    for(Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()){  
	    	System.out.println(beansWithAnnotationMap);
	    	Object value = entry.getValue();
	    	NotificationType annotation = entry.getValue().getClass().getAnnotation(NotificationType.class);
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
