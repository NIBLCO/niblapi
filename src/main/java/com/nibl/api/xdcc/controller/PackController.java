package com.nibl.api.xdcc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nibl.api.xdcc.service.PackService;
import com.fasterxml.jackson.annotation.JsonView;
import com.nibl.api.util.ContentResponse;
import com.nibl.api.util.ErrorResponse;
import com.nibl.api.util.PageResponse;
import com.nibl.api.xdcc.domain.Pack;
import com.nibl.api.xdcc.view.Views;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "nibl", produces = MediaType.APPLICATION_JSON_VALUE)
public class PackController {

    private static Logger log = LoggerFactory.getLogger(PackController.class);

    @Autowired
    PackService packService;

    @JsonView(Views.Pack.class)
    @ApiOperation(value = "Get Nibl XDCC Bot PackList", nickname = "getBotPackList")
    @ApiResponses(
            value = {
	            		@ApiResponse(code = 200, message = "Good", response = Pack.class),
	                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class)
            		}
            )
    @RequestMapping(method = RequestMethod.GET, value = "/packs/{botId}")
    public ContentResponse<List<Pack>> getBotPacks(
    		@PathVariable Integer botId,
    		HttpServletRequest request) {
    	log.debug("Enter /packs/{botId}. " + request.getQueryString());
    	return new ContentResponse<List<Pack>>( packService.getBotPacks(botId) );
    }
    
    @JsonView(Views.Pack.class)
    @ApiOperation(value = "Get Nibl XDCC Bot PackList with Pagination", nickname = "getBotPackListPage")
    @ApiResponses(
            value = { 
                        @ApiResponse(code = 200, message = "Good", response = Pack.class),
                        @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) 
                    }
            )
    @RequestMapping(method = RequestMethod.GET, value = "/packs/{botId}/page")
    public PageResponse<Pack> getBotPacksPaged(
            @PathVariable Integer botId,
            @PathParam("episodeNumber")
                @RequestParam(value = "episodeNumber", required = false, defaultValue = "0") Integer episodeNumber,
            @PathParam("page")
                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,                
            @PathParam("size")
                @RequestParam(value = "size", required = false, defaultValue = "50") Integer size,
            @PathParam("sort")
                @RequestParam(value = "sort", required = false, defaultValue = "name") String sort,
            @PathParam("direction")
                @RequestParam(value = "direction", required = false, defaultValue = "ASC") Sort.Direction direction,
            HttpServletRequest request) {
        log.debug("Enter /packs/{botId}/page. " + request.getQueryString());
        return new PageResponse<Pack>( 
                packService.getBotPacksPaged(botId,
                        new PageRequest(page, size, new Sort(direction, sort))
                        ), request );
    }

    @JsonView(Views.Pack.class)
    @ApiOperation(value = "Search Nibl XDCC Bot PackList", nickname = "searchBotPackList")
    @ApiResponses(
            value = { 
	            		@ApiResponse(code = 200, message = "Good", response = Pack.class),
	                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) 
            		}
            )
    @RequestMapping(method = RequestMethod.GET, value = "/search/{botId}")
    public ContentResponse<List<Pack>> searchBotPacks(
    		@PathVariable Integer botId,
    		@PathParam("query")
				@RequestParam(value = "query", required = true) String query,
    		@PathParam("episodeNumber")
    			@RequestParam(value = "episodeNumber", required = false, defaultValue = "0") Integer episodeNumber,
    		HttpServletRequest request) {
    	log.debug("Enter /search/{botId}. " + request.getQueryString());
    	return new ContentResponse<List<Pack>>( packService.searchBotPacks(botId, query, episodeNumber) );
    }
    
    @JsonView(Views.Pack.class)
    @ApiOperation(value = "Search Nibl XDCC Bot PackList with Pagination", nickname = "searchBotPackListPaginate")
    @ApiResponses(
            value = { 
                        @ApiResponse(code = 200, message = "Good", response = Pack.class),
                        @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) 
                    }
            )
    @RequestMapping(method = RequestMethod.GET, value = "/search/{botId}/page")
    public PageResponse<Pack> searchBotPacksPaged(
            @PathVariable Integer botId,
            @PathParam("query")
                @RequestParam(value = "query", required = true) String query,
            @PathParam("episodeNumber")
                @RequestParam(value = "episodeNumber", required = false, defaultValue = "0") Integer episodeNumber,
            @PathParam("page")
                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,                
            @PathParam("size")
                @RequestParam(value = "size", required = false, defaultValue = "50") Integer size,
            @PathParam("sort")
                @RequestParam(value = "sort", required = false, defaultValue = "name") String sort,
            @PathParam("direction")
                @RequestParam(value = "direction", required = false, defaultValue = "ASC") Sort.Direction direction,
            HttpServletRequest request) {
        log.debug("Enter /search/{botId}/page. " + request.getQueryString());
        return new PageResponse<Pack>( 
                packService.searchBotPacksPaged(botId, query, episodeNumber, 
                        new PageRequest(page, size, new Sort(direction, sort))
                        ), request );
    }
    
    @JsonView(Views.Pack.class)
    @ApiOperation(value = "Search Nibl XDCC Packs", nickname = "searchPacks")
    @ApiResponses(
            value = {
	            		@ApiResponse(code = 200, message = "Good", response = Pack.class),
	                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class)
            		}
            )
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<?> searchPacks(
    		@PathParam("query")
    			@RequestParam(value = "query", required = true) String query,
    		@PathParam("episodeNumber")
    			@RequestParam(value = "episodeNumber", required = false, defaultValue = "0") Integer episodeNumber,
    		HttpServletRequest request) {
    	log.debug("Enter /search. " + request.getQueryString());
    	return new ContentResponse<List<Pack>>( packService.searchPacks(query, episodeNumber) ).getResponseEntity();
    }
    
    @JsonView(Views.Pack.class)
    @ApiOperation(value = "Search Nibl XDCC Packs with Pagination", nickname = "searchPackListPaginate")
    @ApiResponses(
            value = { 
                        @ApiResponse(code = 200, message = "Good", response = Pack.class),
                        @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) 
                    }
            )
    @RequestMapping(method = RequestMethod.GET, value = "/search/page")
    public PageResponse<Pack> searchPacksPaged(
            @PathParam("query")
                @RequestParam(value = "query", required = true) String query,
            @PathParam("episodeNumber")
                @RequestParam(value = "episodeNumber", required = false, defaultValue = "0") Integer episodeNumber,
            @PathParam("page")
                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,                
            @PathParam("size")
                @RequestParam(value = "size", required = false, defaultValue = "50") Integer size,
            @PathParam("sort")
                @RequestParam(value = "sort", required = false, defaultValue = "name") String sort,
            @PathParam("direction")
                @RequestParam(value = "direction", required = false, defaultValue = "ASC") Sort.Direction direction,
            HttpServletRequest request) {
        log.debug("Enter /search/{botId}/page. " + request.getQueryString());
        return new PageResponse<Pack>( 
                packService.searchPacksPaged(query, episodeNumber, 
                        new PageRequest(page, size, new Sort(direction, sort))
                        ), request );
    }
    
    @JsonView(Views.Pack.class)
	@ApiOperation(value = "Get latest XDCC Packs", nickname = "latestPacks")
	@ApiResponses(
	        value = {
	            		@ApiResponse(code = 200, message = "Good", response = Pack.class)
	        		}
	        )
	@RequestMapping(method = RequestMethod.GET, value = "/latest")
	public ResponseEntity<?> latestPacks(
			@PathParam("size")
				@RequestParam(value = "size", required = false, defaultValue = "50") Integer size,
			HttpServletRequest request) {
		log.debug("Enter /packs/latest. " + request.getQueryString());
		return new ContentResponse<List<Pack>>( packService.latestPacks(size) ).getResponseEntity();
	}

}
