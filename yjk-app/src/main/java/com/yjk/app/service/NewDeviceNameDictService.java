package com.yjk.app.service;

import com.yjk.app.util.R;

public interface NewDeviceNameDictService {

	public R getModelList();
	
	public R getTwoStageModelByModelId(Long modelId);
	
	public R getSpecByTwoStageModelId(Long twoStageModel);
	
	public R getSubTypes(Long modelId);
}
