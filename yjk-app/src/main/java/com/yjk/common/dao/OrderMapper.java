package com.yjk.common.dao;


import java.util.List;

import com.yjk.common.entity.OrderDO;
import com.yjk.manager.dto.OrderSearchDTO;
import com.yjk.manager.vo.OrderVO;

import tk.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<OrderDO>{

	 List<OrderVO> orderList(OrderSearchDTO dto);
}
