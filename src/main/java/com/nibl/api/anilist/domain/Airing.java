package com.nibl.api.anilist.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Airing {

	@JsonProperty("time")
	private String time;
	
	@JsonProperty("countdown")
	private Integer countdown;
	
	@JsonProperty("next_episode")
	private Integer nextEpisode;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getCountdown() {
		return countdown;
	}

	public void setCountdown(Integer countdown) {
		this.countdown = countdown;
	}

	public Integer getNextEpisode() {
		return nextEpisode;
	}

	public void setNextEpisode(Integer nextEpisode) {
		this.nextEpisode = nextEpisode;
	}
	
	
	
}
