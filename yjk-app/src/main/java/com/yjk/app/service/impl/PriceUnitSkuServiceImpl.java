package com.yjk.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.PriceUnitSkuMapper;
import com.yjk.app.entity.PriceUnitSkuDO;
import com.yjk.app.service.PriceUnitSkuService;
import com.yjk.app.util.R;
import com.yjk.app.vo.PriceUnitVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class PriceUnitSkuServiceImpl implements PriceUnitSkuService{

	@Autowired
	PriceUnitSkuMapper priceUnitSkuMapper;
	
	
	public R priceUnitSku() {
		
		Example example = new Example(PriceUnitSkuDO.class);
		example.setOrderByClause("sort desc");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status",1);
		List<PriceUnitSkuDO> priceUnitSkuDOs = priceUnitSkuMapper.selectByExample(example);
		List<PriceUnitVO> vos = new ArrayList<>();
		for (PriceUnitSkuDO priceUnitSkuDO : priceUnitSkuDOs) {
			PriceUnitVO v = new PriceUnitVO();
			v.setId(priceUnitSkuDO.getId());
			v.setFuelManager(priceUnitSkuDO.getFuelManager());
			v.setUnitName(priceUnitSkuDO.getUnitName());
			vos.add(v);
		}
		return R.ok().put("info", vos);
		
	}
}