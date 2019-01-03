package com.yjk.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjk.common.dao.MemberMapper;
import com.yjk.manager.common.Constants;
import com.yjk.manager.dto.SearchUserDTO;
import com.yjk.manager.utils.Page;
import com.yjk.manager.utils.PageUtil;
import com.yjk.manager.vo.MemberManagerVO;

import net.sf.json.JSONObject;

@Service
public class MemberManagerService {

	@Autowired
	MemberMapper memberMapper;
	
	public String userList(String page,SearchUserDTO dto) {
		JSONObject result = null;
		int intPage = Integer.parseInt((page == null || "0".equals(page)) ? "1" : page);
        int pageSize = Constants.PAGE_SIZE;
        
		PageHelper.startPage(Integer.parseInt(page), pageSize);
		List<MemberManagerVO> releaseList = memberMapper.userList(dto);
		PageInfo<MemberManagerVO> pageInfo=new PageInfo<>(releaseList);
		Long total = pageInfo.getTotal();
        Page pageObj = PageUtil.createPage(pageSize, Integer.parseInt(total.toString()), intPage);
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("pageCount", pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());   
            jsonMap.put("result", releaseList);     
        result = JSONObject.fromObject(jsonMap);
        return result.toString();
	}
}
