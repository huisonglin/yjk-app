package com.yjk.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yjk.app.dto.SearchDTO;
import com.yjk.app.service.SearchService;
import com.yjk.app.util.Page;
import com.yjk.app.util.PageUtil;
import com.yjk.app.util.PageUtils;
import com.yjk.app.util.R;
import com.yjk.app.vo.QueryResultItemVO;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SolrClient solrClient;
	
	public static Integer pageSize = 10;
	
	@Override
	public R search(SearchDTO searchDTO) throws Exception {
		SolrQuery params = new SolrQuery();
		StringBuilder builder = new StringBuilder("*:*");
		if(searchDTO.getType() != null) {
			builder.append(" AND popularity:").append(searchDTO.getType());
		}
		if(searchDTO.getModeId() != null) {
			builder.append(" AND modeId:").append(searchDTO.getModeId());
		}
		if(searchDTO.getTwoStageModeId() != null) {
			builder.append(" AND twoStageModeId:").append(searchDTO.getTwoStageModeId());
		}
		if(searchDTO.getSpecId() != null) {
			builder.append(" AND specId:").append(searchDTO.getSpecId());
		}
		params.set("q", builder.toString());
		params.set("fq", "{!geofilt}");           //距离过滤函数
		params.set("pt", searchDTO.getPositionDTO().getLongitude()+" "+searchDTO.getPositionDTO().getLatitude()); //当前经纬度
		params.set("sfield", "info_position"); //经纬度的字段
		params.set("d", searchDTO.getDistance().toString()); //就近 d km的所有数据
		params.set("sort", "geodist() asc");  //根据距离排序：由近到远
		params.set("start", (searchDTO.getPageNo()-1)*pageSize);  //记录开始位置
		params.set("rows", pageSize);  //查询的行数
		params.set("fl", "*,_dist_:geodist(),score");//查询的结果中添加距离和score
		QueryResponse queryResponse = solrClient.query(params);
		SolrDocumentList results = queryResponse.getResults();
		List<QueryResultItemVO> result = new ArrayList<>();
		for (SolrDocument solrDocument : results) {
			QueryResultItemVO item = new QueryResultItemVO();
			if(solrDocument.get("address") != null) {
				item.setAddress(solrDocument.get("address").toString());
			}
			if(solrDocument.get("_dist_")!= null) {
				item.setDistance(solrDocument.get("_dist_").toString());
			}
			if(solrDocument.get("id") != null) {
				item.setId(solrDocument.get("id").toString());
			}
			if(solrDocument.get("url") != null) {
				item.setPic(solrDocument.get("url").toString());
			}
			if(solrDocument.get("name")!= null) {
				item.setName(solrDocument.get("name").toString());
			}
			if(solrDocument.get("popularity") != null) {
				item.setType(solrDocument.get("popularity").toString());
			}
			
			result.add(item);
		}
		
        Page pageObj = PageUtil.createPage(pageSize, (int)results.getNumFound(), searchDTO.getPageNo());
		PageUtils pageUtils = new PageUtils(result, pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());
		return R.ok().put("info", pageUtils);
	}

}
