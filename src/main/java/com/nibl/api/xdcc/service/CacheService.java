package com.nibl.api.xdcc.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibl.api.xdcc.domain.Bot;
import com.nibl.api.xdcc.repository.OoinuzaRepository;

@Component("Cache")
public class CacheService {
	
	private static Logger log = LoggerFactory.getLogger(BotService.class);
	
    @Autowired
	private OoinuzaRepository ooinuzaRepository;
    
    // TODO figure out better caching mechanism
    private static Set<Bot> allBots = null;
    public Set<Bot> getCache() {
    	if( allBots == null ) {
    		refreshCache();
    	}
    	return allBots;
    }
    
    public void refreshCache() {
    	log.debug("GETTING ALL BOTS FROM DB");
    	allBots = ooinuzaRepository.getBots();
    }
    
}
