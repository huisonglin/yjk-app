package com.yjk.app.service.impl;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;

public class PutOnPurchaseInfoServiceImpl {

	@Autowired
	SolrClient solrClient;
	
	public  R PutOnPurchase(Long id) throws Exception {
		
		purchaseItemInfo purchaseItemInfo = new purchaseItemInfo();
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(purchaseItemInfo);
		solrClient.add(doc);
		solrClient.commit();
		return R.ok();
	}
	
	public R purchaseOut(Long id) throws Exception {
		solrClient.deleteById(id.toString());
		solrClient.commit();
		return R.ok();
	}
	
	public static class purchaseItemInfo{
		
		/**
		 * 主键
		 */
		String id;
		/**
		 * 名称
		 */
		String name;
		/**
		 * 使用程度
		 */
		String useDegree;
		/**
		 * 信用等级
		 */
		String starLeve;
		/**
		 * 经纬度
		 */
		String lonAndlat;
		/**
		 * 列表类型
		 */
		Integer type;  
		/**
		 * 机型ID
		 */
		Long modeId;
		/**
		 * 地址
		 */
		String address;
		/**
		 * 价格
		 */
		Double price;
		
		
	}
}
