package com.yjk.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yjk.app.common.Constants;
import com.yjk.app.service.DeviceCollectionService;
import com.yjk.app.util.R;
import com.yjk.app.vo.CollectionOptionsVO;
import com.yjk.common.dao.DeviceCollectionMapper;
import com.yjk.common.entity.DeviceCollection;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DeviceCollectionServiceImpl implements DeviceCollectionService{

	@Autowired
	DeviceCollectionMapper deviceCollectionMapper;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	public R collectionOptions(CollectionOptionsVO collectionOptionsVO) {
		
		Integer status = collectionOptionsVO.getStatus();
		if(status == 1) { //收藏操作
			DeviceCollection deviceCollection = new DeviceCollection();
			deviceCollection.setCreateTime(new Date());
			deviceCollection.setUpdateTime(new Date());
			deviceCollection.setInfoId(collectionOptionsVO.getInfoId());
			deviceCollection.setInfoType(collectionOptionsVO.getInfoType());
			deviceCollection.setMemberId(collectionOptionsVO.getMemberId());
			deviceCollectionMapper.insert(deviceCollection);
			valueOperations.set(Constants.COLLECTION+collectionOptionsVO.getMemberId()+"_"+collectionOptionsVO.getInfoId(), "1");
		}else {
			if(collectionOptionsVO.getCollectionId() != null) {
				deviceCollectionMapper.deleteByPrimaryKey(collectionOptionsVO.getCollectionId());
			}else {
				Example example = new Example(DeviceCollection.class);
				Criteria criteria = example.createCriteria();
				criteria.andEqualTo("memberId", collectionOptionsVO.getMemberId());
				criteria.andEqualTo("infoId", collectionOptionsVO.getInfoId());
				int result = deviceCollectionMapper.deleteByExample(example);
			}
			redisTemplate.delete(Constants.COLLECTION+collectionOptionsVO.getMemberId()+"_"+collectionOptionsVO.getInfoId());
		}
		
		return R.ok().put("status", collectionOptionsVO.getStatus());
	}
}
