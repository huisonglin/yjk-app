package com.yjk.app.service;

import com.yjk.app.dto.DeviceRentalInNeedInfoDTO;
import com.yjk.app.util.R;

public interface DeviceRentalInNeedInfoService {

	/**
	 * 添加或者修改发布信息
	 * @param deviceRentalInNeedInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R addOrUpdateNeedInfo(DeviceRentalInNeedInfoDTO deviceRentalInNeedInfoDTO) throws Exception;
	
	/**
	 * 编辑发布信息
	 * @param id
	 */
	public R editNeedInfo(Long id) throws Exception;
	
	/**
	 * 下架发布信息
	 * @return
	 */
	public R cacelNeedInfo(Long id) throws Exception;
	
	/**
	 * 上架发布信息
	 * @return
	 */
	public R releaseNeedInfo(Long id) throws Exception ;
	
	/**
	 * 删除发布信息
	 * @param id
	 * @return
	 */
	public R deleteNeedInfo(Long id) throws Exception;
}
