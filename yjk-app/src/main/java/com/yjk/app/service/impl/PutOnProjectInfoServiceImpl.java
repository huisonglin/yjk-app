package com.yjk.app.service.impl;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.dao.DeviceRentalInNeedInfoMapper;
import com.yjk.app.dao.MemberMapper;
import com.yjk.app.entity.DeviceRentalInNeedInfoDO;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.service.PutOnProjectInfoService;
import com.yjk.app.service.PutOnRentInfoService;
import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;

/**
 * 发布工程
 * @author huisonglin
 *
 */
@Service
public class PutOnProjectInfoServiceImpl implements PutOnProjectInfoService {

	@Autowired
	SolrClient solrClient;
	@Autowired
	DeviceRentalInNeedInfoMapper deviceRentalInNeedInfoMapper;
	@Autowired
	MemberMapper memberMapper;
	
	@Async
	public R putOnProject(Long id) throws Exception {
		DeviceRentalInNeedInfoDO deviceRentalInNeedInfoDO = deviceRentalInNeedInfoMapper.selectByPrimaryKey(id);
		ProjectItemInfo item = new ProjectItemInfo();
		item.setId(deviceRentalInNeedInfoDO.getId().toString());
		item.setInfo_position(deviceRentalInNeedInfoDO.getLongitude()+" "+deviceRentalInNeedInfoDO.getLatitude());
		item.setName(deviceRentalInNeedInfoDO.getName());
		item.setModeId(deviceRentalInNeedInfoDO.getModeId());
		item.setTwoStageModeId(deviceRentalInNeedInfoDO.getTwoStageModeId());
		item.setSpecId(deviceRentalInNeedInfoDO.getSpecId());
		item.setPopularity(PublishingTypeEnum.RENTAL_IN_NEED.getValue());
		item.setAddress(deviceRentalInNeedInfoDO.getAdress());
		if(deviceRentalInNeedInfoDO.getPics() != null) {
			item.setUrl(deviceRentalInNeedInfoDO.getPics().split("#")[0]);
		}
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(item);
		solrClient.add(doc);
		solrClient.commit();
		return R.ok();
	}
	@Async
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
		String info_position;
		
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
		String address;
		
		/**
		 * 二级机型ID
		 */
		Long twoStageModeId;
		/**
		 * 规格ID
		 */
		Long specId;
		/**
		 * 图片
		 */
		String url;
		
		
		
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Long getTwoStageModeId() {
			return twoStageModeId;
		}
		public void setTwoStageModeId(Long twoStageModeId) {
			this.twoStageModeId = twoStageModeId;
		}
		public Long getSpecId() {
			return specId;
		}
		public void setSpecId(Long specId) {
			this.specId = specId;
		}
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
		
		public String getInfo_position() {
			return info_position;
		}
		public void setInfo_position(String info_position) {
			this.info_position = info_position;
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
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}

		
	}
}
