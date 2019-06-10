package com.nibl.api.xdcc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.nibl.api.util.PackComparator;
import com.nibl.api.util.StringUtil;
import com.nibl.api.xdcc.domain.Pack;

@Component("Pack")
public class PackService {

    private static Logger log = LoggerFactory.getLogger(PackService.class);

    @Autowired
    BotService botService;

    public List<Pack> getBotPacks(Integer botId) {
    	log.debug("Get packs for bot " + botId);
        return botService.getBot(botId).getPackList();
    }
    
    public Page<Pack> getBotPacksPaged(Integer botId, Pageable pageable){
        
        List<Pack> packs = getBotPacks(botId);
        for(Sort.Order o : pageable.getSort() ) {            
            Collections.sort(packs, new PackComparator(o.getProperty(), o.getDirection().name()));
        }
        
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > packs.size() ? packs.size() : (start + pageable.getPageSize());
        return new PageImpl<Pack>(packs.subList(start, end), pageable, packs.size());
    }

    public List<Pack> searchPacks(String query, Integer episodeNumber) {
    	log.debug("Search packs for all bots. Query: " + query + " / EP#: " + episodeNumber);
    	List<Pack> searchedPacks = new ArrayList<Pack>();

    	Pattern pattern = StringUtil.manipulateQuery(query);

    	botService.
    	getBots()
    	.stream()
    	.forEach(
    			b ->
    				searchedPacks.addAll(
    						b.getPackList()
    						.stream()
    						.filter( p -> pattern.matcher( p.getName() ).find() )
    						.collect( Collectors.toList() )
					)
    			);
    	
    	if( episodeNumber > 0 ) {
    		return
				searchedPacks
				.stream()
				.filter( p -> p.getEpisodeNumber().intValue() == episodeNumber.intValue() )
				.collect(Collectors.toList());
    	}
    	
    	return searchedPacks;
    }
    
    public Page<Pack> searchPacksPaged(String query, Integer episodeNumber, Pageable pageable){
        
        List<Pack> packs = searchPacks(query,episodeNumber);
        for(Sort.Order o : pageable.getSort() ) {            
            Collections.sort(packs, new PackComparator(o.getProperty(), o.getDirection().name()));
        }
        
        
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > packs.size() ? packs.size() : (start + pageable.getPageSize());
        return new PageImpl<Pack>(packs.subList(start, end), pageable, packs.size());
    }
    
    public List<Pack> searchBotPacks(Integer botId, String query, Integer episodeNumber) {
    	log.debug("Search packs for bot # " + botId + ". Query: " + query + " / EP#: " + episodeNumber);
    	List<Pack> searchedPacks = new ArrayList<Pack>();
    	
    	Pattern pattern = StringUtil.manipulateQuery(query);
    	
    	searchedPacks = 
    			botService.
    			getBot( botId )
		    	.getPackList()
				.stream()
				.filter( p -> pattern.matcher( p.getName() ).find() )
				.collect( Collectors.toList() );
    	
    	if( episodeNumber > 0 ) {
    		return
				searchedPacks
				.stream()
				.filter( p -> p.getEpisodeNumber().intValue() == episodeNumber.intValue() )
				.collect(Collectors.toList());
    	}
    	
    	return searchedPacks;
    }
    
    public Page<Pack> searchBotPacksPaged(Integer botId, String query, Integer episodeNumber, Pageable pageable){
        
        List<Pack> packs = searchBotPacks(botId,query,episodeNumber);
        for(Sort.Order o : pageable.getSort() ) {            
            Collections.sort(packs, new PackComparator(o.getProperty(), o.getDirection().name()));
        }
        
        
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > packs.size() ? packs.size() : (start + pageable.getPageSize());
        return new PageImpl<Pack>(packs.subList(start, end), pageable, packs.size());
    }
    
    public List<Pack> latestPacks(Integer size) {
    	log.debug("Get " + size + " latest packs");
    	
    	return botService.
    	getBots() 
    	.stream()
    	.map(b -> b.getPackList())
    	.flatMap(Collection::stream)
    	.sorted(new Comparator<Pack>(){
            public int compare(Pack a, Pack b){
                if( a.getLastModified().before(b.getLastModified()) ) return 1;
                if( a.getLastModified().after(b.getLastModified()) ) return -1;
                return 0;
            }
        })
    	.limit(size)
    	.collect(Collectors.toList());
    }
    
}
