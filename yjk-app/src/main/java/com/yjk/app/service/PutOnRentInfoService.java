package com.yjk.app.service;

import com.yjk.app.util.R;

public interface PutOnRentInfoService {

	
	public R putOnRent(Long deviceRentOutInfoId) throws Exception ;
	
	public R rentInfoOut(Long deviceRentOutInfoId) throws Exception;
}
