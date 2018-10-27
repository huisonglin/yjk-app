package com.yjk.app.service;


import com.yjk.app.dto.DeviceRentOutInfoDTO;
import com.yjk.app.util.R;

public interface DeviceRentOutInfoService {

	/**
	 * 添加或者修改发布信息
	 * @param deviceRentOutInfoDTO
	 * @return
	 * @throws Exception
	 */
	public R addOrUpdateRentOutInfo(DeviceRentOutInfoDTO deviceRentOutInfoDTO) throws Exception ;
	
	/**
	 * 编辑发布出售信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public R editRentOutInfo(Long id) throws Exception ;
	/**
	 * 下架发布出售信息
	 * @param id
	 * @return
	 */
	public R cacelRentOutInfo(Long id) ;
	/**
	 * 上架发布出售信息
	 * @param id
	 * @return
	 */
	public R releaseRentOutInfo(Long id) ;
	/**
	 * 删除发布出售信息
	 * @param id
	 * @return
	 */
	public R delRentOutInfo(Long id) ;
}
