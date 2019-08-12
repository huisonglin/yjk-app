package com.yjk.manager.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ClearCacheService {

	
	@CacheEvict(value = {"modelList","twoStagemodelList","specList","getSubTypes"},allEntries=true)
	public void clearCache() {
		
	}
}
