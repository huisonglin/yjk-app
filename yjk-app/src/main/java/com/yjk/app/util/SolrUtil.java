package com.yjk.app.util;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.solr.common.SolrInputDocument;


public class SolrUtil {
	
	
	@SuppressWarnings("rawtypes")
	public static SolrInputDocument SolrInputDocumentCoverter(Object bean) throws Exception {
		Map describe = PropertyUtils.describe(bean);
		Set keySet = describe.keySet();
		keySet.remove("class");
		SolrInputDocument doc = new SolrInputDocument();
		for (Object fieldName : keySet) {
			Object object = describe.get(fieldName);
			if(object != null) {
				if(object instanceof Date) {
					Date date = (Date)object;
					object = new Date(date.getTime() + 28800000l);
				}
				doc.addField(fieldName.toString(), object);
			}
		}
		return doc;
	}
	
	
/*	public static void main(String[] args) throws Exception {
		
		DeviceDO deviceDO = new DeviceDO();
		deviceDO.setCreateTime(new Date());
		SolrUtil.SolrInputDocumentCoverter(deviceDO);
	}*/
}
