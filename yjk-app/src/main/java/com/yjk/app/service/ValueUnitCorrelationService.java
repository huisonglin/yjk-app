package com.yjk.app.service;

import java.util.List;

import com.yjk.app.vo.ValueUnitNameVO;

public interface ValueUnitCorrelationService {

	//保存价格
	public void saveValueUnitCorrelation(String price,Long infoId);
	//获取价格
	public List<ValueUnitNameVO> showPriceName(Long infoId);
	//获取原始价格信息
	public String showPrice(Long infoId);
}
