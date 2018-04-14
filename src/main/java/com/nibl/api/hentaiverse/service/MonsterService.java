package com.nibl.api.hentaiverse.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nibl.api.hentaiverse.domain.Monster;
import com.nibl.api.hentaiverse.domain.Monster.MonsterAttributeMapper;
import com.nibl.api.hentaiverse.domain.MonsterCondition;

@Component("Monster")
public class MonsterService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private static Logger log = LoggerFactory.getLogger(MonsterService.class);

    @Autowired
    MonsterCacheService monsterCacheService;

    @Cacheable("monsters")
    public Set<Monster> getMonsters() {
        log.debug("Get all monsters from CacheService");
        return monsterCacheService.getCache();
    }

    public Boolean monsterConditionFilter(Monster monster, List<MonsterCondition> monsterConditions) {

        // This group of conditions must inclusively succeed
        for (MonsterCondition monsterCondition : monsterConditions) {

            Object obj = null;
            try {
                MonsterAttributeMapper m = Monster.MonsterAttributeMapper
                        .fromString(monsterCondition.getParameter().toLowerCase());
                obj = m.getValue(monster);
            } catch (Exception e) {
                // Invalid condition passed in, so return false
                // TODO move this check further up to determine if parameters are acceptable
                return false;
            }
            
            if (obj instanceof String) {
                String testValue = obj.toString();
                // String comparison
                switch (monsterCondition.getCondition()) {
                    case ">": 
                    {
                        Boolean matches = monsterCondition.getValues().stream().anyMatch(p -> testValue.toLowerCase().startsWith(p.toLowerCase()));
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "<": 
                    {
                        Boolean matches = monsterCondition.getValues().stream().anyMatch(p -> testValue.toLowerCase().endsWith(p.toLowerCase()));
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "=": 
                    {
                        Boolean matches = monsterCondition.getValues().stream().anyMatch(testValue::equalsIgnoreCase);
                        
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                }

            } else if (obj instanceof Integer) {
                Integer testValue = (Integer) obj;

                // Integer comparisons
                switch (monsterCondition.getCondition()) {
                    case ">": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> testValue > Integer.parseInt(c)).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "<": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> testValue < Integer.parseInt(c)).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "=": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> testValue == Integer.parseInt(c)).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                }

            } else if (obj instanceof Long) {
                Long testValue = (Long) obj;

                // Integer comparisons
                switch (monsterCondition.getCondition()) {
                    case ">": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> testValue > Long.parseLong(c)).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "<": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> testValue < Long.parseLong(c)).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "=": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> testValue == Long.parseLong(c)).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                }

            } else if (obj instanceof Timestamp) {

                Timestamp testValue = (Timestamp) obj;
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfAugment = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                // Integer comparisons
                switch (monsterCondition.getCondition()) {
                    case ">": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> {
                                    try {
                                        try{
                                            return sdf.parse( sdf.format( testValue ) ).after( sdf.parse(c) );
                                        } catch (ParseException e) {
                                            return sdf.parse( sdf.format( testValue ) ).after( sdfAugment.parse(c) );
                                        }
                                    } catch (ParseException e) {
                                        return false;
                                    }
                                } ).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "<": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> {
                                    try {
                                        try{
                                            return sdf.parse( sdf.format( testValue ) ).before( sdf.parse(c) );
                                        } catch (ParseException e) {
                                            return sdf.parse( sdf.format( testValue ) ).before( sdfAugment.parse(c) );
                                        }
                                    } catch (ParseException e) {
                                        return false;
                                    }
                                }).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                    case "=": {
                        Boolean matches = (monsterCondition.getValues().stream()
                                .filter(c -> {
                                    try {
                                        try{
                                            return sdf.parse( sdf.format( testValue ) ).equals( sdf.parse(c) );
                                        } catch (ParseException e) {
                                            return sdf.parse( sdf.format( testValue ) ).equals( sdfAugment.parse(c) );
                                        }
                                    } catch (ParseException e) {
                                        return false;
                                    }
                                }).count() > 0);
                        if (!matches) {
                            return false;
                        }
                    }
                        break;
                }

            }

        }

        return true;
    }

    public List<Monster> getMonsters(List<MonsterCondition> monsterConditions) {
    	try{
	        return getMonsters().stream().filter(m -> monsterConditionFilter(m, monsterConditions))
	                .collect(Collectors.toList());
        } catch(NoSuchElementException e) {
        	return null;
        }
    }

    public List<Monster> getActiveMonsters(Integer numMonths) {
        try{
        	Timestamp timestamp = Timestamp.valueOf(LocalDateTime.from(LocalDateTime.now()).minusMonths(numMonths));	
        	return getMonsters().stream().filter(m -> m.getLastUpdate().before(timestamp)).collect(Collectors.toList());
        } catch(NoSuchElementException e) {
        	return null;
        }
    }

    public Monster findByMonsterId(Long monsterId) {
        log.debug("Get monster #" + monsterId);
        try{
        	return getMonsters().stream().filter(m -> m.getMonsterId().equals(monsterId) ).findFirst().get();
        } catch(NoSuchElementException e) {
        	return null;
        }
    }

    public List<Monster> findByMonsterIds(Long[] monsterIds) {
    	try{
	        return getMonsters().stream().filter(m -> Arrays.asList(monsterIds).contains(m.getMonsterId()))
	                .collect(Collectors.toList());
        } catch(NoSuchElementException e) {
        	return null;
        }
    }

    @Transactional
    public Monster saveMonster(Monster monster) throws Exception {
        if (null == monster) {
            throw new Exception("No monster passed in");
        }

        Monster localMonster = findByMonsterId(monster.getMonsterId());
        if (null == localMonster) { // If monster doesn't exist, assume this is a new monster
            localMonster = new Monster();
            localMonster.setMonsterId(monster.getMonsterId());
            localMonster.setMonsterClass(monster.getMonsterClass());
            localMonster.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        }

        if (null != monster.getMonsterName()) {
            localMonster.setMonsterName(monster.getMonsterName());
        }

        if (null != monster.getPlvl()) {
            localMonster.setPlvl(monster.getPlvl());
        }

        if (null != monster.getTrainer()) {
            localMonster.setTrainer(monster.getTrainer());
        }

        if (null != monster.getAttack()) {
            localMonster.setAttack(monster.getAttack());
        }

        if (null != monster.getFire()) {
            localMonster.setFire(monster.getFire());
        }

        if (null != monster.getCold()) {
            localMonster.setCold(monster.getCold());
        }

        if (null != monster.getElec()) {
            localMonster.setElec(monster.getElec());
        }

        if (null != monster.getWind()) {
            localMonster.setWind(monster.getWind());
        }

        if (null != monster.getHoly()) {
            localMonster.setHoly(monster.getHoly());
        }

        if (null != monster.getDark()) {
            localMonster.setDark(monster.getDark());
        }

        if (null != monster.getCrushing()) {
            localMonster.setCrushing(monster.getCrushing());
        }

        if (null != monster.getSlashing()) {
            localMonster.setSlashing(monster.getSlashing());
        }

        if (null != monster.getPiercing()) {
            localMonster.setPiercing(monster.getPiercing());
        }

        localMonster.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (null != localMonster.getId()) {
            em.merge(localMonster); // update
        } else {
            em.persist(localMonster); // create
        }

        em.getTransaction().commit();
        em.close();
        
        // TODO update cache instead of invalidating
        monsterCacheService.invalidateCache();

        return localMonster;
    }
}
