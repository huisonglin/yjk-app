package com.yjk.app.service;

import java.lang.reflect.InvocationTargetException;

import com.yjk.app.dto.ReleasePurchaseInfoDTO;
import com.yjk.app.util.R;


public interface DeviceSeekToPurchaseInfoService {

	
	/**
	 * 发布求购信息
	 * @param releasePurchaseInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R releasePurchaseInfo(ReleasePurchaseInfoDTO releasePurchaseInfoDTO) throws Exception;
	

	/**
	 * 取消发布求购信息
	 * @param id
	 * @return
	 */
	public R cacelReleasePurchaseInfo(Long id) ;
	/**
	 * 删除发布求购信息
	 * @param id
	 * @return
	 */
	public R delReleasePurchaseInfo(Long id);
	
	/**
	 * 编辑求购信息
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public R eidtReleasePurchaseInfo(Long id) throws Exception ;
	
	public R updateReleasePurchaseInfo(ReleasePurchaseInfoDTO releasePurchaseInfoDTO) throws Exception;
}
