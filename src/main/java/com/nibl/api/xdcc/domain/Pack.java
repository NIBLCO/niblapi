package com.nibl.api.xdcc.domain;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibl.api.xdcc.view.Views;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "updatepacklist_packs", catalog = "ooinuza")
public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(Views.InternalBot.class)
    @Column(name = "id", nullable = false)
    private Long id;

    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @JsonView(Views.Pack.class)
    @Column(name = "bot_id")
    private Integer botId;

    @JsonView(Views.Pack.class)
    @Column(name = "number")
    private Integer number;

    @JsonView(Views.Pack.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Pack.class)
    @Column(name = "size")
    private String size;

    @JsonView(Views.Pack.class)
    @Column(name = "episode_number")
    private Integer episodeNumber;

    @JsonView(Views.Pack.class)
    @Column(name = "last_modified")
    @ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastModified;

    // ----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    // ----------------------------------------------------------------------
    public Pack(Long id, Integer botId, Integer number, String name, String size, Integer episodeNumber,
    		Timestamp lastModified) {
        super();
        this.setId(id);
        this.setBotId(botId);
        this.setNumber(number);
        this.setName(name);
        this.setSize(size);
        this.setEpisodeNumber(episodeNumber);
        this.setLastModified(lastModified);
    }

    public Pack() {
        super();
    }

    // ----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    // ----------------------------------------------------------------------

    public static Pack getUsersFromJsonString(String jsonString)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Pack.class);
    }

    // ----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    // ----------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // ----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    // ----------------------------------------------------------------------
    public Integer getBotId() {
        return botId;
    }

    public void setBotId(Integer botId) {
        this.botId = botId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    // ----------------------------------------------------------------------
    // toString METHOD
    // ----------------------------------------------------------------------
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(this.getId());
        sb.append("]:");
        sb.append(this.getBotId());
        sb.append("|");
        sb.append(this.getNumber());
        sb.append("|");
        sb.append(this.getName());
        sb.append("|");
        sb.append(this.getSize());
        sb.append("|");
        sb.append(this.getEpisodeNumber());
        sb.append("|");
        sb.append(this.getLastModified());
        return sb.toString();
    }

}
