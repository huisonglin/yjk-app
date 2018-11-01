package com.yjk.app.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.dao.DeviceRentOutInfoMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.entity.DeviceRentOutInfoDO;
import com.yjk.app.exception.RRException;
import com.yjk.app.service.DeviceRentOutInfoService;
import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;
import com.yjk.app.util.UuidUtils;

import edu.emory.mathcs.backport.java.util.Arrays;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
	
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	
	/**
	 * 上架出租信息
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	public R putOnRent(Long deviceId) throws Exception {
		
		DeviceDO device = deviceMapper.selectByPrimaryKey(deviceId);
		if(device == null) {
			throw new RRException("该设备不存在");
		}
		Example example = new Example(DeviceRentOutInfoDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("deviceId", 27);
		DeviceRentOutInfoDO deviceRentOutInfoDO = deviceRentOutInfoMapper.selectByExample(example).get(0);
		deviceRentOutInfoDO.setNewstime(new Date());
		rentItemInfo rentItemInfo = new rentItemInfo();
		rentItemInfo.setId(UuidUtils.get32UUID());
		rentItemInfo.setName(device.getDeviceName());
		rentItemInfo.setInfo_position("115."+new Random().nextInt(8000)+" "+"32."+new Random().nextInt(8000));
		rentItemInfo.setPopularity(PublishingTypeEnum.RENT_OUT.getValue());
		if(device.getPics() != null) {
			String[] split = device.getPics().split("#");
			rentItemInfo.setUrl(split[0]);
		}
		rentItemInfo.setLast_modified(deviceRentOutInfoDO.getNewstime());
		SolrInputDocument doc = SolrUtil.SolrInputDocumentCoverter(rentItemInfo);
		solrClient.add(doc);
		solrClient.commit();	
		deviceRentOutInfoMapper.updateByPrimaryKeySelective(deviceRentOutInfoDO);
		return R.ok();
	}
	
	
	
	public R rentInfoOut(Long device) throws Exception {
		solrClient.deleteById(device.toString());
		solrClient.commit();
		return R.ok();
	}
	
	
	
	
	
	public static class rentItemInfo{
		
		//主键id
		String id;
		//名称
		String name;	
		//图片地址
		String url;
		//经纬度
		String info_position;
		//星级		
		Integer starLeve;
		//列表类型
		Integer popularity;   //1设备出租 2设备出售  3工程发布  4紧急求购
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

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
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

		
		
		

		
		
		
	}
	
	
}
