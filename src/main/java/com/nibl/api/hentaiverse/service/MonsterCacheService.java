package com.nibl.api.hentaiverse.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nibl.api.hentaiverse.domain.Monster;
import com.nibl.api.hentaiverse.repository.MonsterRepository;

@Component("MonsterCache")
public class MonsterCacheService {
	
	private static Logger log = LoggerFactory.getLogger(MonsterCacheService.class);
	
	@Autowired
	private MonsterRepository monsterRepository;
    
    // TODO figure out better caching mechanism
    private static Set<Monster> allMonsters = null;
    public Set<Monster> getCache() {
    	if( allMonsters == null ) {
    		refreshCache();
    	}
    	return allMonsters;
    }
    
    public void refreshCache() {
    	log.debug("GETTING ALL MONSTERS FROM DB");
    	allMonsters = monsterRepository.getMonsters();
    }
    
    public void invalidateCache() {
    	log.debug("INVALIDATE MONSTER CACHE");
    	allMonsters = null;
    }
}
