package com.yjk.app.dao;

import java.util.List;

import com.yjk.app.dto.MyListDTO;
import com.yjk.app.entity.DeviceDO;
import com.yjk.app.vo.MyListVO;

import tk.mybatis.mapper.common.Mapper;

public interface DeviceMapper extends Mapper<DeviceDO>{

	List<MyListVO> myList(MyListDTO mylistDTO);
}
