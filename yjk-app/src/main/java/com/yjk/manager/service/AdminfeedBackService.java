package com.yjk.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjk.common.dao.FeedBackMapper;
import com.yjk.manager.common.Constants;
import com.yjk.manager.utils.Page;
import com.yjk.manager.utils.PageUtil;
import com.yjk.manager.vo.AdminFeedbackVO;

import net.sf.json.JSONObject;

@Service
public class AdminfeedBackService {

	@Autowired
	FeedBackMapper feedBackMapper;
	
	public String feedBackList(String page) {
		JSONObject result = null;
		int intPage = Integer.parseInt((page == null || "0".equals(page)) ? "1" : page);
        int pageSize = Constants.PAGE_SIZE;
        
		PageHelper.startPage(Integer.parseInt(page), pageSize);
		List<AdminFeedbackVO> releaseList = feedBackMapper.feedBackList();
		PageInfo<AdminFeedbackVO> pageInfo=new PageInfo<>(releaseList);
		Long total = pageInfo.getTotal();
        Page pageObj = PageUtil.createPage(pageSize, Integer.parseInt(total.toString()), intPage);
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("pageCount", pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());
            jsonMap.put("result", releaseList);
        result = JSONObject.fromObject(jsonMap);
        return result.toString();
	}
}
