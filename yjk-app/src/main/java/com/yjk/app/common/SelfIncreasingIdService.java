package com.yjk.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class SelfIncreasingIdService {

    @Autowired
    ValueOperations<String, String> valueOperations;
    
    public Long generateId() {
    	Long id = valueOperations.increment(Constants.SELF_INCREASING_ID, 1);
    	return id;
    }
}
