package com.nibl.api.anilist.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * Series have a title, start and end dates and the season in which they aired.
 *
 * @author Brandon Risberg
 * @since 2/16/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Series {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title_romaji")
	private String rom_title;

	@JsonProperty("title_english")
	private String eng_title;

	@JsonProperty("title_japanese")
	private String jap_title;

	@JsonProperty("type")
	private String type;

	@JsonProperty("start_date_fuzzy")
	private Timestamp startDateFuzzy;

	@JsonProperty("end_date_fuzzy")
	private Timestamp endDateFuzzy;

	@JsonProperty("season")
	private Integer season;

	@JsonProperty("series_type")
	private String seriesType;

	@JsonProperty("synonyms")
	private List<String> synonyms;

	@JsonProperty("genres")
	private List<String> genres;

	@JsonProperty("adult")
	private Boolean adult;

	@JsonProperty("average_score")
	private Double averageScore;

	@JsonProperty("popularity")
	private Integer popularity;

	@JsonProperty("updated_at")
	private Timestamp updatedAt;

	@JsonProperty("hashtag")
	private String hashtag;

	@JsonProperty("image_url_sml")
	private String imageUrlSml;

	@JsonProperty("image_url_med")
	private String imageUrlMed;

	@JsonProperty("image_url_lge")
	private String imageUrlLge;

	@JsonProperty("image_url_banner")
	private String imageUrlBanner;

	@JsonProperty("total_episodes")
	private Integer totalEpisodes;

	@JsonProperty("airing_status")
	private String airingStatus;

	@JsonProperty("airing")
	private Airing airing;

	public Series() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRom_title() {
		return rom_title;
	}

	public void setRom_title(String rom_title) {
		this.rom_title = rom_title;
	}

	public String getEng_title() {
		return eng_title;
	}

	public void setEng_title(String eng_title) {
		this.eng_title = eng_title;
	}

	public String getJap_title() {
		return jap_title;
	}

	public void setJap_title(String jap_title) {
		this.jap_title = jap_title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getStartDateFuzzy() {
		return startDateFuzzy;
	}

	public void setStartDateFuzzy(Timestamp startDateFuzzy) {
		this.startDateFuzzy = startDateFuzzy;
	}

	public Timestamp getEndDateFuzzy() {
		return endDateFuzzy;
	}

	public void setEndDateFuzzy(Timestamp endDateFuzzy) {
		this.endDateFuzzy = endDateFuzzy;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(String seriesType) {
		this.seriesType = seriesType;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getImageUrlSml() {
		return imageUrlSml;
	}

	public void setImageUrlSml(String imageUrlSml) {
		this.imageUrlSml = imageUrlSml;
	}

	public String getImageUrlMed() {
		return imageUrlMed;
	}

	public void setImageUrlMed(String imageUrlMed) {
		this.imageUrlMed = imageUrlMed;
	}

	public String getImageUrlLge() {
		return imageUrlLge;
	}

	public void setImageUrlLge(String imageUrlLge) {
		this.imageUrlLge = imageUrlLge;
	}

	public String getImageUrlBanner() {
		return imageUrlBanner;
	}

	public void setImageUrlBanner(String imageUrlBanner) {
		this.imageUrlBanner = imageUrlBanner;
	}

	public Integer getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(Integer totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}

	public String getAiringStatus() {
		return airingStatus;
	}

	public void setAiringStatus(String airingStatus) {
		this.airingStatus = airingStatus;
	}

	public Airing getAiring() {
		return airing;
	}
	
	public void setAiring(Airing airing) {
		this.airing = airing;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Series))
			return false;

		Series series = (Series) o;

		return eng_title.equals(series.eng_title);

	}

	@Override
	public int hashCode() {
		return eng_title.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Series[title=");
		sb.append(eng_title);
		sb.append("]");

		return sb.toString();
	}
	
}