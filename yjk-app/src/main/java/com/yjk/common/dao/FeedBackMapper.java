package com.yjk.common.dao;


import java.util.List;

import com.yjk.common.entity.FeedBackDO;
import com.yjk.manager.vo.AdminFeedbackVO;

import tk.mybatis.mapper.common.Mapper;

public interface FeedBackMapper extends Mapper<FeedBackDO>{

	List<AdminFeedbackVO> feedBackList();
}
