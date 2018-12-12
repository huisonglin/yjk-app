package com.yjk.app.service;


public interface InfoDetailService {

	public Object infoDetail(Long id,Integer infoType) throws Exception;
	
	public void dealDetail(Object infoDetail) throws Exception;
}
