package com.yjk.app.service.impl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;

public class PutOnProjectInfoServiceImpl {

	@Autowired
	SolrClient solrClient;
	
	
	public R putOnProject(Long id) throws Exception {
		
		purchaseItemInfo purchaseItemInfo = new purchaseItemInfo();
		//TODO
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(purchaseItemInfo);
		solrClient.add(doc);
		solrClient.commit();
		return R.ok();
	}
	
	public  R projectInfoOut(Long id) throws Exception {
		solrClient.deleteById(id.toString());
		solrClient.commit();
		return R.ok();
	}
	
	public static class purchaseItemInfo{
		/**
		 * 主键ID
		 */
		Long id;
		
		
		/**
		 * 列表名称
		 */
		String name;
		
		/**
		 * 经纬度
		 */
		String lonAndlat;
		
		/**
		 * 信用等级
		 */
		String starLeve;
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
