package com.yjk.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.yjk.app.service.NewDeviceNameDictService;
import com.yjk.app.util.R;
import com.yjk.app.vo.SelectSpecVO;
import com.yjk.app.vo.SelectSubTypeVO;
import com.yjk.app.vo.SpecVO;
import com.yjk.common.dao.RentalModelMapper;
import com.yjk.common.dao.RentalSpecMapper;
import com.yjk.common.dao.RentalTwoStageModeMapper;
import com.yjk.common.entity.ModelDO;
import com.yjk.common.entity.RentalModelDO;
import com.yjk.common.entity.SpecDO;
import com.yjk.common.entity.TwoStagemodelDO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class NewDeviceNameDictServiceImpl implements NewDeviceNameDictService{

	@Override
	public R getModelList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R getTwoStageModelByModelId(Long modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R getSpecByTwoStageModelId(Long twoStageModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R getSubTypes(Long modelId) {
		// TODO Auto-generated method stub
		return null;
	}/*


    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
	@Autowired
	RentalModelMapper modelMapper;
	@Autowired
	RentalTwoStageModeMapper twoStagemodelMapper;
	@Autowired
	RentalSpecMapper specMapper;
	
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
	
	@Cacheable(value="getSubTypes")
	public R getSubTypes(Long modelId) {
		List<SelectSubTypeVO> subTypes =new ArrayList<>();
		Example example = new Example(TwoStagemodelDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("modelId", modelId);
		List<TwoStagemodelDO> twoStagemodelList = twoStagemodelMapper.selectByExample(example);
		for (TwoStagemodelDO twoStagemodelDO : twoStagemodelList) {
			SelectSubTypeVO sv = new SelectSubTypeVO();
			sv.setText(twoStagemodelDO.getName());
			Example specExample = new Example(SpecDO.class);
			Criteria specCriteria = specExample.createCriteria();
			specCriteria.andEqualTo("twoStageModeId",twoStagemodelDO.getId());
			List<SelectSpecVO> lvs = new ArrayList<>();
			SelectSpecVO ingnor = new SelectSpecVO();
			ingnor.setId("0" + "-" + twoStagemodelDO.getName() + "-" + twoStagemodelDO.getId());
			ingnor.setText("不限");
			lvs.add(ingnor);
			List<SpecDO> specList = specMapper.selectByExample(specExample);
			for (SpecDO specDO : specList) {
				SelectSpecVO lv = new SelectSpecVO();
				lv.setId(specDO.getId() + "-" + twoStagemodelDO.getName() + "-" + twoStagemodelDO.getId());
				lv.setText(specDO.getName());
				lvs.add(lv);
			}
			sv.setChildren(lvs);
			subTypes.add(sv);
		}
		return R.ok().put("info", subTypes);
	}

*/}
