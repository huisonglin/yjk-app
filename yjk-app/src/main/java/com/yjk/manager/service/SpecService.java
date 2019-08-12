package com.yjk.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjk.common.dao.SpecMapper;
import com.yjk.common.dao.TwoStagemodelMapper;
import com.yjk.common.entity.SpecDO;
import com.yjk.common.entity.TwoStagemodelDO;
import com.yjk.manager.common.Constants;
import com.yjk.manager.utils.Page;
import com.yjk.manager.utils.PageUtil;

import net.sf.json.JSONObject;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SpecService {



	@Autowired
	SpecMapper specMapper;
	
	public String list(String page,String keyword,Long pid) {
		JSONObject result = null;
		int intPage = Integer.parseInt((page == null || "0".equals(page)) ? "1" : page);
        int pageSize = Constants.PAGE_SIZE;
        
		PageHelper.startPage(Integer.parseInt(page), pageSize);
		Example example = new Example(SpecDO.class);
		example.setOrderByClause("id desc");
		Criteria createCriteria = example.createCriteria();
		if(StringUtils.isNotBlank(keyword)) {
			createCriteria.andLike("name","%"+keyword+"%");
		}
		if(pid != null) {
			createCriteria.andEqualTo("twoStageModeId",pid);
		}
		List<SpecDO> modelList = specMapper.selectByExample(example);
		PageInfo<SpecDO> pageInfo=new PageInfo<>(modelList);
		Long total = pageInfo.getTotal();
        Page pageObj = PageUtil.createPage(pageSize, Integer.parseInt(total.toString()), intPage);
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("pageCount", pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());   
            jsonMap.put("result", modelList);     
        result = JSONObject.fromObject(jsonMap);
        return result.toString();
	}


}
