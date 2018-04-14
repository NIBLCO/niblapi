package com.nibl.api.anilist.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nibl.api.anilist.domain.Season;
import com.nibl.api.anilist.domain.Series;
import com.nibl.api.anilist.service.AnilistApiService;
import com.nibl.api.util.ContentResponse;
import com.nibl.api.util.ErrorResponse;
import com.nibl.api.xdcc.controller.BotController;
import com.nibl.api.xdcc.view.Views;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "anilist", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnilistController {

	private static Logger log = LoggerFactory.getLogger(BotController.class);
	
    @Autowired
    AnilistApiService aniListApi;
    
    @JsonView(Views.Bot.class)
    @ApiOperation(value = "Series By Season", nickname = "seriesBySeason")
    @ApiResponses(
            value = { 
            		@ApiResponse(code = 200, message = "Good", response = Series.class, responseContainer = "List"),
                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) 
            		}
            )
    @RequestMapping(method = RequestMethod.GET, value = "/series/season")
    public ContentResponse<List<Series>> aniListTest(
    		@PathParam("year")
    		@RequestParam(value = "year", required = true)  Integer year,
    		@PathParam("season")
    		@ApiParam(required = true, allowableValues = "winter, spring, summer, fall")
    		@RequestParam(value = "season", required = true) String seasonName,
			HttpServletRequest request) {
    	log.debug("Enter /series/season. " + request.getQueryString());
        Season season = new Season();
        season.setYear(year);
        season.setSeasonName(seasonName);
        return new ContentResponse<List<Series>>(aniListApi.fetchAllSeries(season));
    }
    
}
