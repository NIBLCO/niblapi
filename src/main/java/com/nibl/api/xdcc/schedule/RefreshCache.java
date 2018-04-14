package com.nibl.api.xdcc.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nibl.api.xdcc.service.CacheService;

@Component("Scheduler")
public class RefreshCache {

	private static Logger log = LoggerFactory.getLogger(RefreshCache.class);

    @Autowired
    CacheService cacheService;
    
    @Scheduled(fixedRateString = "${nibl.cache.refresh.time}")
    public void refreshCache() {
        log.info("Start Refresh Bot Cache");
        cacheService.refreshCache();
        log.info("Finish Refresh Bot Cache");
    }

}
