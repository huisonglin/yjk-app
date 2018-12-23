package com.yjk.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yjk.app.service.PriceUnitSkuService;
import com.yjk.app.util.R;
import com.yjk.app.vo.PriceUnitVO;
import com.yjk.common.dao.PriceUnitSkuMapper;
import com.yjk.common.entity.PriceUnitSkuDO;

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
			StringBuilder builder = new StringBuilder(priceUnitSkuDO.getUnitName()).append("(").append(priceUnitSkuDO.getFuelManager()).append(")");
			v.setUnitName(builder.toString());
			vos.add(v);
		}
		return R.ok().put("info", vos);
		
	}
}
