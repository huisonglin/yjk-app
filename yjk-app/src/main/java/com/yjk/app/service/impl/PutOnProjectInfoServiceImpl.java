package com.yjk.app.service.impl;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.dao.DeviceRentalInNeedInfoMapper;
import com.yjk.app.dao.MemberMapper;
import com.yjk.app.entity.DeviceRentalInNeedInfoDO;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;

@Service
public class PutOnProjectInfoServiceImpl {

	@Autowired
	SolrClient solrClient;
	@Autowired
	DeviceRentalInNeedInfoMapper deviceRentalInNeedInfoMapper;
	@Autowired
	MemberMapper memberMapper;
	
	
	public R putOnProject(Long id) throws Exception {
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = deviceRentalInNeedInfoMapper.selectByPrimaryKey(id);
		MemberDO member = memberMapper.selectByPrimaryKey(deviceRentalInNeedInfoDO.getMemberId());
		ProjectItemInfo item = new ProjectItemInfo();
		item.setId(deviceRentalInNeedInfoDO.getId().toString());
		item.setLonAndlat(deviceRentalInNeedInfoDO.getLongitude()+" "+deviceRentalInNeedInfoDO.getLatitude());
		item.setName(deviceRentalInNeedInfoDO.getName());
		item.setModeId(deviceRentalInNeedInfoDO.getModeId());
		item.setPopularity(PublishingTypeEnum.RENTAL_IN_NEED.getValue());
		item.setStarLeve(member.getCreditScore());
		item.setKeywords(deviceRentalInNeedInfoDO.getAdress());
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(item);
		solrClient.add(doc);
		solrClient.commit();
		return R.ok();
	}
	
	public  R projectInfoOut(Long id) throws Exception {
		solrClient.deleteById(id.toString());
		solrClient.commit();
		return R.ok();
	}
	
	public static class ProjectItemInfo{
		/**
		 * 主键ID
		 */
		String id;
		
		
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
		Integer starLeve;
		/**
		 * 列表类型
		 */
		Integer popularity;  
		/**
		 * 机型ID
		 */
		Long modeId;
		/**
		 * 地址
		 */
		String keywords;
		/**
		 * 价格
		 */
		String description;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public Integer getPopularity() {
			return popularity;
		}
		public void setPopularity(Integer popularity) {
			this.popularity = popularity;
		}
		public Long getModeId() {
			return modeId;
		}
		public void setModeId(Long modeId) {
			this.modeId = modeId;
		}
		public String getKeywords() {
			return keywords;
		}
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
	}
}
