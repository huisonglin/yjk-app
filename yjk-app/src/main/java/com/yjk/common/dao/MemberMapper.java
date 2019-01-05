package com.yjk.common.dao;


import java.util.List;

import com.yjk.common.entity.MemberDO;
import com.yjk.manager.dto.SearchUserDTO;
import com.yjk.manager.vo.MemberManagerVO;
import com.yjk.manager.vo.StatisticsVO;

import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<MemberDO>{

	List<MemberManagerVO> userList(SearchUserDTO dto);
	
	StatisticsVO statistics();
	
}
