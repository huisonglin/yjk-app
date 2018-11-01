package com.yjk.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yjk.app.service.SearchService;
import com.yjk.app.util.Page;
import com.yjk.app.util.PageUtil;
import com.yjk.app.util.PageUtils;
import com.yjk.app.util.R;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SolrClient solrClient;
	
	public static Integer pageSize = 10;
	
	@Override
	public R search(Integer pageNo) throws Exception {
		SolrQuery params = new SolrQuery();
		params.set("q", "*:*");
		params.set("fq", "{!geofilt}");           //距离过滤函数
		params.set("pt", "116.83857057756535 32.95717025299991"); //当前经纬度
		params.set("sfield", "info_position"); //经纬度的字段
		params.set("d", "500"); //就近 d km的所有数据
		params.set("sort", "geodist() asc");  //根据距离排序：由近到远
		params.set("start", (pageNo-1)*pageSize);  //记录开始位置
		params.set("rows", pageSize);  //查询的行数
		params.set("fl", "*,_dist_:geodist(),score");//查询的结果中添加距离和score
		QueryResponse queryResponse = solrClient.query(params);
		SolrDocumentList results = queryResponse.getResults();
		List<SolrDocument> list = new ArrayList<>();
		for (SolrDocument solrDocument : results) {
			System.out.println(JSON.toJSONString(solrDocument));
			Object fieldValue = solrDocument.getFieldValue("last_modified");
			Date newtime = (Date)fieldValue;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(newtime);
			System.out.println(format);
			list.add(solrDocument);
		}
		
        Page pageObj = PageUtil.createPage(pageSize, (int)results.getNumFound(), pageNo);
		PageUtils pageUtils = new PageUtils(list, pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());
		return R.ok().put("info", pageUtils);
	}

}
