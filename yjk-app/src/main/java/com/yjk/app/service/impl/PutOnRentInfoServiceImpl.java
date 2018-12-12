package com.yjk.app.service.impl;

import java.util.Date;
import java.util.Random;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.config.SolrEnvironmentConfig;
import com.yjk.app.dao.DeviceMapper;
import com.yjk.app.dao.DeviceRentOutInfoMapper;
import com.yjk.app.dao.MemberMapper;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.entity.DeviceRentOutInfoDO;
import com.yjk.app.entity.MemberDO;
import com.yjk.app.service.PutOnRentInfoService;
import com.yjk.app.util.R;
import com.yjk.app.util.SolrUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 上架发布出售
 * @author huisonglin
 *
 */
@Service
public class PutOnRentInfoServiceImpl implements PutOnRentInfoService{


	@Autowired
	SolrClient solrClient;

	@Autowired
	DeviceMapper deviceMapper;
	
	@Autowired
	DeviceRentOutInfoMapper deviceRentOutInfoMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	SolrEnvironmentConfig solrEnvironmentConfig;
	
	/**
	 * 上架出租信息
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	@Async
	public R putOnRent(Long deviceRentOutInfoId) throws Exception {
		DeviceRentOutInfoDO deviceRentOutInfoDO = deviceRentOutInfoMapper.selectByPrimaryKey(deviceRentOutInfoId);
		DeviceDO device = deviceMapper.selectByPrimaryKey(deviceRentOutInfoDO.getDeviceId());
		deviceRentOutInfoDO.setNewstime(new Date());
		rentItemInfo rentItemInfo = new rentItemInfo();
		rentItemInfo.setId(deviceRentOutInfoId.toString());
		rentItemInfo.setModeId(device.getModeId());
		rentItemInfo.setTwoStageModeId(device.getTwoStageModeId());
		rentItemInfo.setSpecId(device.getSpecId());
		rentItemInfo.setAddress(deviceRentOutInfoDO.getAddress());
		rentItemInfo.setName(device.getDeviceName());
		rentItemInfo.setInfo_position(deviceRentOutInfoDO.getLongitude()+" "+deviceRentOutInfoDO.getLatitude());
		rentItemInfo.setPopularity(PublishingTypeEnum.RENT_OUT.getValue());
		rentItemInfo.setSubject(solrEnvironmentConfig.getEnvironment());
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
	
	
	/**
	 * 取消上架
	 * @param deviceRentOutInfoId
	 * @return
	 * @throws Exception
	 */
	@Async
	public R rentInfoOut(Long deviceRentOutInfoId) throws Exception {
		solrClient.deleteById(deviceRentOutInfoId.toString());
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
		
		String address;
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
		//环境
		String subject;
		
		
		

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

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
