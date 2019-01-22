package com.yjk.app.controller;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yjk.app.annotation.Position;
import com.yjk.app.common.Constants;
import com.yjk.app.common.PublishingTypeEnum;
import com.yjk.app.dto.PositionDTO;
import com.yjk.app.dto.SearchDTO;
import com.yjk.app.service.SearchService;
import com.yjk.app.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yjk.app.util.R;
import com.yjk.app.validator.Assert;

@RestController
public class ApiSearchController {

    private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	ValueOperations<String, String> valueOperations;
	
/*	<delete><query>*:*</query></delete>
	<commit/>*/
	
	@RequestMapping("/app/search")
	public R search(SearchDTO searchDTO  ,@Position PositionDTO positionDTO,HttpServletRequest request) throws Exception {
		Assert.isNull(searchDTO.getPageNo(),"请输入页码");
		if(searchDTO.getPageNo() == null || searchDTO.getPageNo() < 1) {
			searchDTO.setPageNo(1);
		}
		Assert.isNull(searchDTO.getDistance(), "检索范围不能为空");
		searchDTO.setPositionDTO(positionDTO);
		String memberId = getMemberId(request);
		dealSearchDTO(searchDTO, memberId,searchDTO.getType());
		R search = searchService.search(searchDTO);
		return search;
	}

	private void dealSearchDTO(SearchDTO searchDTO, String memberId, Integer type) {
		if(memberId != null) {
			StringBuilder searchKey = new StringBuilder(Constants.SEARCH_RECORD);
			searchKey.append("_").append(type).append("_").append(memberId);
			if(searchDTO.getModeId() != null) {
				StringBuilder builder = new StringBuilder(searchDTO.getModeId().toString());
				builder.append("-").append(searchDTO.getTwoStageModeId()).append("-").append(searchDTO.getSpecId());
				valueOperations.set(searchKey.toString(), builder.toString());
			}else {
				String searchRecord = valueOperations.get(searchKey.toString());
				if(searchRecord != null) {
					String[] record = searchRecord.split("-");
					if(record != null && record.length > 2) {
						if(record[0] != null && !"null".equals(record[0])) {
							searchDTO.setModeId(Long.valueOf(record[0]));
						}
						if(record[1] != null && !"null".equals(record[1])) {
							searchDTO.setTwoStageModeId(Long.valueOf(record[1]));
						}
						if(record[2] != null && !"null".equals(record[2])) {
							searchDTO.setSpecId(Long.valueOf(record[2]));
						}
					}
				}
			}
		}
	}

	private String getMemberId(HttpServletRequest request) throws Exception {
		try {
			String memberId = null;
			String token = request.getParameter("token");
			if(StringUtils.isNotBlank(token)) {
				String info = jwtUtils.getClaimByToken(token).getSubject();
				if(StringUtils.isNotBlank(info)) {
				       if(info.contains("#")) { //APP登录
				           String[] sat = info.split("#");
				           String  terminalValue= valueOperations.get(Constants.TERMINAL+"_"+sat[0]);
				           memberId = terminalValue;
				       }else {
				    	   memberId = info;
				       }
				}
			}
			return memberId;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
		
}
