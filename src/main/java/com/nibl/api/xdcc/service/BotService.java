package com.nibl.api.xdcc.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.nibl.api.xdcc.domain.Bot;

@Component("Bot")
public class BotService {

    private static Logger log = LoggerFactory.getLogger(BotService.class);

    @Autowired
    CacheService cacheService;
    
    @Cacheable("bots")
    public Set<Bot> getBots() {
    	log.debug("Get all bots from CacheService");
    	return cacheService.getCache();
    }
    
    public Bot getBot(Integer botId) {
    	log.debug("Get bot #" + botId);
        return getBots()
        		.stream()
        		.filter( b -> b.getId().intValue() == botId.intValue() )
        		.findFirst()
        		.get();
    }
}
