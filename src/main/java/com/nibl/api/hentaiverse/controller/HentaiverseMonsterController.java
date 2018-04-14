package com.nibl.api.hentaiverse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nibl.api.hentaiverse.domain.Monster;
import com.nibl.api.hentaiverse.domain.MonsterCondition;
import com.nibl.api.hentaiverse.service.MonsterService;
import com.nibl.api.hentaiverse.views.Views;
import com.nibl.api.util.ContentResponse;
import com.nibl.api.util.ErrorResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "hentaiverse", produces = MediaType.APPLICATION_JSON_VALUE)
public class HentaiverseMonsterController {

    private static Logger log = LoggerFactory.getLogger(HentaiverseMonsterController.class);

    @Autowired
    MonsterService monsterService;

    @JsonView(Views.FullMonster.class)
    @ApiOperation(value = "Get Active HentaiVerse Monsters", nickname = "getActiveMonsters")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "Good", response = Monster.class, responseContainer = "List"),
                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/activemonsters")
    public ContentResponse<List<Monster>> getMonsters(
			@RequestParam(value = "numMonths", required = false, defaultValue = "2") Integer numMonths,
			HttpServletRequest request) {
        log.debug("Enter /monster. " + request.getQueryString());
        return new ContentResponse<List<Monster>>(monsterService.getActiveMonsters(numMonths));
    }
    
    @JsonView(Views.FullMonster.class)
    @ApiOperation(value = "Get All HentaiVerse Monsters", nickname = "getAllMonsters")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "Good", response = Monster.class),
                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.POST, value = "/monsters", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContentResponse<List<Monster>> getAllMonsters(
    		@RequestBody List<MonsterCondition> monsterConditions,
    		HttpServletRequest request) {
        return new ContentResponse<List<Monster>>(monsterService.getMonsters(monsterConditions));
    }
    
    @JsonView(Views.FullMonster.class)
    @ApiOperation(value = "Get HentaiVerse Monsters by Monster Ids", nickname = "getMonstersByIds")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "Good", response = Monster.class),
                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/monsters/{monsterIds}")
    public ContentResponse<List<Monster>> getMonsters(@PathVariable Long[] monsterIds, HttpServletRequest request) {
        log.debug("Enter /monsters/{monsterIds}. " + request.getQueryString());
        return new ContentResponse<List<Monster>>(monsterService.findByMonsterIds(monsterIds));
    }
    
    @JsonView(Views.Monster.class)
    @ApiOperation(value = "Save HentaiVerse Monster", nickname = "saveMonster")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "Good", response = Monster.class),
                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.PUT, value = "/monster", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContentResponse<Monster> setMonster(
				@RequestBody Monster monster,
				HttpServletRequest request) throws Exception {
        log.debug("Enter PUT /monster. " + request.getQueryString());
        return new ContentResponse<Monster>(monsterService.saveMonster(monster));
    }

}
