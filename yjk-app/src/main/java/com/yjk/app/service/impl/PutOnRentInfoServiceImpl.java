package com.yjk.app.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.exception.RRException;
import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 上架发布出售
 * @author huisonglin
 *
 */
@Service
public class PutOnRentInfoServiceImpl {


	@Autowired
	SolrClient solrClient;

	@Autowired
	DeviceMapper deviceMapper;
	
	/**
	 * 上架出租信息
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	public R putOnRent(Long deviceId) throws Exception {
		
/*		Device device = deviceMapper.selectByPrimaryKey(deviceId);
		if(device == null) {
			throw new RRException("该设备不存在");
		}*/
		rentItemInfo rentItemInfo = new rentItemInfo();
		rentItemInfo.setId(10l);
		rentItemInfo.setName("小天才早教机3");
		rentItemInfo.setUrl("http://www.pic.com/2.jpg");
		rentItemInfo.setLast_modified(new Date());
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(rentItemInfo);
		//doc.addField("last_modified", new Date());
		solrClient.add(doc);
		solrClient.commit();	
		return R.ok();
	}
	
	
	
	public R rentInfoOut(Long device) throws Exception {
		solrClient.deleteById(device.toString());
		solrClient.commit();
		return R.ok();
	}
	
	
	
	
	
	public static class rentItemInfo{
		
		//主键id
		Long id;
		//名称
		String name;	
		//图片地址
		String url;
		//经纬度
		String lonAndlat;
		//星级		
		Integer starLeve;
		//列表类型
		Integer type;   //1设备出租 2设备出售  3工程发布  4紧急求购
		//规格ID
		Long specId;
		//机型ID
		Long modeId;
		//二级机型ID
		Long twoStageModeId;
		//发布日期
		Date last_modified;
		
		
		
		
		

		public Date getLast_modified() {
			return last_modified;
		}

		public void setLast_modified(Date last_modified) {
			this.last_modified = last_modified;
		}

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
