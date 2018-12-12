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

import com.yjk.app.config.QiNiuConfig;
import com.yjk.app.config.SolrEnvironmentConfig;
import com.yjk.app.dto.SearchDTO;
import com.yjk.app.service.InfoDetailService;
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
	
	public static Integer pageSize = 25;
	
/*	@Autowired
	InfoDetailService infoDetailService;*/
	
	@Autowired
	SolrEnvironmentConfig solrEnvironmentConfig;
	
	@Override
	public R search(SearchDTO searchDTO) throws Exception {
		SolrQuery params = new SolrQuery();
		StringBuilder builder = new StringBuilder("subject:").append(solrEnvironmentConfig.getEnvironment());
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
				StringBuilder b = new StringBuilder(formatDouble(Double.parseDouble(solrDocument.get("_dist_").toString())));
				b.append("km");
				item.setDistance(b.toString());
			}
			if(solrDocument.get("id") != null) {
				item.setId(solrDocument.get("id").toString());
			}
			if(solrDocument.get("url") != null) {
				item.setPic(solrDocument.get("url").toString()+QiNiuConfig.XCX_INDEX);
			}
			if(solrDocument.get("name")!= null) {
				item.setName(solrDocument.get("name").toString());
			}
			if(solrDocument.get("popularity") != null) {
				item.setType(solrDocument.get("popularity").toString());
			}
/*			Object infoDetail = infoDetailService.infoDetail(Long.valueOf(item.getId()), Integer.parseInt(item.getType()));
			item.setItemDetail(infoDetail);*/
			result.add(item);
		}
		
        Page pageObj = PageUtil.createPage(pageSize, (int)results.getNumFound(), searchDTO.getPageNo());
		PageUtils pageUtils = new PageUtils(result, pageObj.getTotalPage() == 0 ? 1 : pageObj.getTotalPage());
		return R.ok().put("info", pageUtils);
	}
	
    /**
     * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
     * 应该是这样使用：System.out.println(String.format("%.2f", d));
     * @param d
     * @return
     */
    public static String formatDouble(double d) {
        return String.format("%.2f", d);
    }

}
