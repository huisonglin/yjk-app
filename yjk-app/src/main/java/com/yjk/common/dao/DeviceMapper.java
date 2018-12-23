package com.yjk.common.dao;

import java.util.List;

import com.yjk.app.dto.MyListDTO;
import com.yjk.app.vo.MyListVO;
import com.yjk.common.entity.DeviceDO;

import tk.mybatis.mapper.common.Mapper;

public interface DeviceMapper extends Mapper<DeviceDO>{

	List<MyListVO> myList(MyListDTO mylistDTO);
}
