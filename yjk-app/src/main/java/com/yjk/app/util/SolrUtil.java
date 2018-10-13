package com.yjk.app.util;

import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.common.SolrInputDocument;


public class SolrUtil {
	
	public static SolrInputDocument SolrInputDocumentCoverter(Object bean) throws Exception {
		Map describe = BeanUtils.describe(bean);
		Set keySet = describe.keySet();
		keySet.remove("class");
		SolrInputDocument doc = new SolrInputDocument();
		for (Object fieldName : keySet) {
			System.out.println(fieldName+"---"+describe.get(fieldName));
			if(describe.get(fieldName) != null) {
				doc.addField(fieldName.toString(), describe.get(fieldName));
			}
		}
		return doc;
	}
	
}
