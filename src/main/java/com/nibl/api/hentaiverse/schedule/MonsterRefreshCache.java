package com.nibl.api.hentaiverse.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nibl.api.hentaiverse.service.MonsterCacheService;

@Component("MonsterScheduler")
public class MonsterRefreshCache {

	private static Logger log = LoggerFactory.getLogger(MonsterRefreshCache.class);

    @Autowired
    MonsterCacheService monsterCacheService;

    @Scheduled(fixedRateString = "${nibl.cache.refresh.time}")
    public void refreshMonsterCache() {
        log.info("Start Refresh Monster Cache");
        monsterCacheService.refreshCache();
        log.info("Finish Refresh Monster Cache");
    }

}
