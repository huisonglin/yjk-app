package com.yjk.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.ModelMapper;
import com.yjk.app.dao.SpecMapper;
import com.yjk.app.dao.TwoStagemodelMapper;
import com.yjk.app.entity.ModelDO;
import com.yjk.app.entity.SpecDO;
import com.yjk.app.entity.TwoStagemodelDO;
import com.yjk.app.service.DeviceNameDictService;
import com.yjk.app.util.R;
import com.yjk.app.vo.SpecVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DeviceNameDictServiceImpl implements DeviceNameDictService{

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	TwoStagemodelMapper twoStagemodelMapper;
	@Autowired
	SpecMapper specMapper;
	
	@Cacheable(value="modelList")
	public R getModelList() {
		logger.info("机型列表走数据库了。。。。");
		List<ModelDO> modelList = modelMapper.selectAll();
		return R.ok().put("info", modelList);
	}
	
	@Cacheable(value="twoStagemodelList")
	public R getTwoStageModelByModelId(Long modelId) {
		logger.info("二级机型列表走数据库了。。。。");
		Example example = new Example(TwoStagemodelDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("modelId", modelId);
		List<TwoStagemodelDO> twoStagemodelList = twoStagemodelMapper.selectByExample(example);
		return R.ok().put("info", twoStagemodelList);
	}
	@Cacheable(value="specList")
	public R getSpecByTwoStageModelId(Long twoStageModel) {
		logger.info("规格ID走数据库了。。。。");
		Example example = new Example(SpecDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("twoStageModeId",twoStageModel);
		List<SpecDO> specList = specMapper.selectByExample(example);
		List<SpecVO> specVOs = new ArrayList<SpecVO>();
		for (SpecDO spec : specList) {
			SpecVO vo = new SpecVO();
			vo.setId(spec.getId());
			vo.setName(spec.getName());
			vo.setTwoStageModeId(spec.getTwoStageModeId());
			if(spec.getBrand() != null) {
				vo.setBrands(spec.getBrand().split(","));
			}
			specVOs.add(vo);
		}
		return R.ok().put("info", specVOs);
	}
}