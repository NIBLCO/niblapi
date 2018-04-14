package com.nibl.api.xdcc.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nibl.api.util.ContentResponse;
import com.nibl.api.util.ErrorResponse;
import com.nibl.api.xdcc.domain.Bot;
import com.nibl.api.xdcc.service.BotService;
import com.nibl.api.xdcc.view.Views;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "nibl", produces = MediaType.APPLICATION_JSON_VALUE)
public class BotController {

    private static Logger log = LoggerFactory.getLogger(BotController.class);

    @Autowired
    BotService botService;

    @JsonView(Views.Bot.class)
    @ApiOperation(value = "Get Nibl XDCC Bots", nickname = "getBots")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "Good", response = Bot.class, responseContainer = "List"),
                    @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/bots")
    public ContentResponse<Set<Bot>> getBots(HttpServletRequest request) {
        log.debug("Enter /bots. " + request.getQueryString());
        return new ContentResponse<Set<Bot>>(botService.getBots());
    }

    @JsonView(Views.FullBot.class)
    @ApiOperation(value = "Get Nibl XDCC Bot", nickname = "getBot")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Good", response = Bot.class),
            @ApiResponse(code = 400, message = "Invalid parameters", response = ErrorResponse.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/bots/{botId}")
    public ContentResponse<Bot> getBot(@PathVariable Integer botId, HttpServletRequest request) {
        log.debug("Enter /bots/{botId}. " + request.getQueryString());
        return new ContentResponse<Bot>(botService.getBot(botId));
    }

}
