package com.yjk.app.service.impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;
import com.yjk.common.dao.DeviceMapper;

@Service
public class PutOnSaleInfoServiceImpl {

	@Autowired
	SolrClient solrClient;
	
	@Autowired
	DeviceMapper deviceMapper;
	
	public R putOnSale(Long deviceId) throws Exception {
		
		SaleItemInfo saleItemInfo = new SaleItemInfo();
		//do
		
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(saleItemInfo);
		solrClient.add(doc);
		solrClient.commit();		
		return R.ok();
		
	}
	
	public R saleInfoOut(Long id) throws Exception {
		solrClient.deleteById(id.toString());
		solrClient.commit();
		return R.ok();
		
		
	}
	
	public static class SaleItemInfo{
		/**
		 * 主键
		 */
		Long id;
		/**
		 * 设备名称
		 */
		String name;
		/**
		 * 	图片地址
		 */
		String url;
		/**
		 * 经纬度
		 */
		String lonAndlat;
		/**
		 * 	    信用等级
		 */
		Integer starLeve;
		/**
		 * 		列表类型
		 */
		Integer type;   //1设备出租 2设备出售  3工程发布  4紧急求购

		/**
		 *规格ID 
		 */
		Long specId;
		/**
		 * 		机型ID
		 */
		Long modeId;

		/**
		 * 二级机型ID
		 */
		Long twoStageModeId;
		
		

		public Long getSpecId() {
			return specId;
		}

		public void setSpecId(Long specId) {
			this.specId = specId;
		}

		public Long getModeId() {
			return modeId;
		}

		public void setModeId(Long modeId) {
			this.modeId = modeId;
		}

		public Long getTwoStageModeId() {
			return twoStageModeId;
		}

		public void setTwoStageModeId(Long twoStageModeId) {
			this.twoStageModeId = twoStageModeId;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getLonAndlat() {
			return lonAndlat;
		}

		public void setLonAndlat(String lonAndlat) {
			this.lonAndlat = lonAndlat;
		}

		public Integer getStarLeve() {
			return starLeve;
		}

		public void setStarLeve(Integer starLeve) {
			this.starLeve = starLeve;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		
		
		
	}
}
