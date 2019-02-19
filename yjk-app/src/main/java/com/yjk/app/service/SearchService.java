package com.yjk.app.service;

import com.yjk.app.dto.SearchDTO;
import com.yjk.app.util.R;

public interface SearchService {

	public R search(SearchDTO searchDTO) throws Exception ;
	
	public R fitMe(Integer type ,Long id,SearchDTO searchDTO) throws Exception;
}
