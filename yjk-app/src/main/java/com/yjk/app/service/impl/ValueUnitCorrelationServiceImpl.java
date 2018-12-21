package com.yjk.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjk.app.dao.PriceUnitSkuMapper;
import com.yjk.app.dao.ValueUnitCorrelationMapper;
import com.yjk.app.entity.PriceUnitSkuDO;
import com.yjk.app.entity.ValueUnitCorrelationDO;
import com.yjk.app.service.ValueUnitCorrelationService;
import com.yjk.app.vo.ValueUnitNameVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ValueUnitCorrelationServiceImpl implements ValueUnitCorrelationService{

	@Autowired
	ValueUnitCorrelationMapper valueUnitCorrelationMapper;
	

	//保存价格
	public void saveValueUnitCorrelation(String price,Long infoId) {
		if(StringUtils.isBlank(price)) {
			return;
		}
		//删除之前的关联表
		Example example = new Example(ValueUnitCorrelationDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("infoId",infoId);
		valueUnitCorrelationMapper.deleteByExample(example);
		
		String[] prices = price.split("#");
		if(prices != null && prices.length > 0) {
			for (String pricesAndvalue : prices) {
				if(StringUtils.isNotBlank(pricesAndvalue)) {
					String[] pv = pricesAndvalue.split("-");
					ValueUnitCorrelationDO  vc = new ValueUnitCorrelationDO();
					vc.setInfoId(infoId);
					vc.setPrice(new BigDecimal(Long.valueOf(pv[0])));
					vc.setPriceUnitSkuId(Long.valueOf(pv[1]));
					vc.setCreateTime(new Date());
					vc.setUpdateTime(new Date());
					valueUnitCorrelationMapper.insertSelective(vc);
				}
			}
		}
	}
	
	@Autowired
	PriceUnitSkuMapper priceUnitSkuMapper;
	
	public List<ValueUnitNameVO> showPriceName(Long infoId) {
		Example example = new Example(ValueUnitCorrelationDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("infoId",infoId);
		List<ValueUnitCorrelationDO> valueUnitCorrelationDOs = valueUnitCorrelationMapper.selectByExample(example);
		if(valueUnitCorrelationDOs == null || valueUnitCorrelationDOs.size() < 1) {
			return null;
		}
		List<ValueUnitNameVO> vos = new ArrayList<>();
		for (ValueUnitCorrelationDO valueUnitCorrelationDO : valueUnitCorrelationDOs) {
			ValueUnitNameVO vo = new ValueUnitNameVO();
			vo.setPrice(valueUnitCorrelationDO.getPrice());
			Long priceUnitSkuId = valueUnitCorrelationDO.getPriceUnitSkuId();
			PriceUnitSkuDO priceUnitSkuDO = priceUnitSkuMapper.selectByPrimaryKey(priceUnitSkuId);
			StringBuilder builder = new StringBuilder(priceUnitSkuDO.getUnitName()).append("(").append(priceUnitSkuDO.getFuelManager()).append(")");
			vo.setUnitName(builder.toString());
			vos.add(vo);
		}
		return vos;
	}
	
	public String showPrice(Long infoId) {
		Example example = new Example(ValueUnitCorrelationDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("infoId",infoId);
		List<ValueUnitCorrelationDO> valueUnitCorrelationDOs = valueUnitCorrelationMapper.selectByExample(example);
		if(valueUnitCorrelationDOs == null || valueUnitCorrelationDOs.size() < 1) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (ValueUnitCorrelationDO valueUnitCorrelationDO : valueUnitCorrelationDOs) {
			sb.append(valueUnitCorrelationDO.getPrice()).append("-").append(valueUnitCorrelationDO.getPriceUnitSkuId()).append("#");
		}
		String price = sb.toString();
		if(price!=null&&!"".equals(price)) {
			return price.substring(0,price.length()-1);
		}
		return null;
	}
}
