package com.nibl.api.xdcc.domain;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibl.api.xdcc.view.Views;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "updatepacklist_bots", catalog = "ooinuza")
public class Bot implements Serializable {

	private static final long serialVersionUID = 2914470029841180883L;

	@Id
    @JsonView(Views.Bot.class)
    @Column(name = "id", nullable = false)
    private Integer id;

    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @JsonView(Views.Bot.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.InternalBot.class)
    @Column(name = "url")
    private String url;

    @JsonView(Views.InternalBot.class)
    @Column(name = "type")
    private String type;

    @JsonView(Views.Bot.class)
    @Column(name = "owner")
    private String owner;

    @JsonView(Views.InternalBot.class)
    @Column(name = "status_id")
    private Integer statusId;

    @JsonView(Views.InternalBot.class)
    @Column(name = "last_seen")
    @ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastSeen;

    @JsonView(Views.Bot.class)
    @Column(name = "last_processed")
    @ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastProcessed;

    @JsonView(Views.InternalBot.class)
    @Column(name = "informative")
    private Integer informative;

    @JsonView(Views.Bot.class)
    @Column(name = "batchenable")
    private Integer batchEnable;

    @JsonView(Views.InternalBot.class)
    @Column(name = "external")
    private Integer external;

    @JsonView(Views.InternalBot.class)
    @Column(name = "parser_id")
    private Integer parserId;

    @Transient
    @JsonView(Views.Bot.class)
    private Integer packSize;

    // ----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    // ----------------------------------------------------------------------
    @JsonView(Views.FullBot.class)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="bot_id", referencedColumnName="id")
    private List<Pack> packList;

    // ----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    // ----------------------------------------------------------------------
    public Bot(Integer id, String name, String url, String type, String owner, Integer statusId, 
    		Timestamp lastSeen, Timestamp lastProcessed, Integer informative, Integer batchEnable,
    		Integer external, Integer parserId) {
        super();
        this.setId(id);
        this.setName(name);
        this.setUrl(url);
        this.setType(type);
        this.setOwner(owner);
        this.setStatusId(statusId);
        this.setLastSeen(lastSeen);
        this.setLastProcessed(lastProcessed);
        this.setInformative(informative);
        this.setBatchEnable(batchEnable);
        this.setExternal(external);
        this.setParserId(parserId);
    }

    public Bot() {
        super();
    }

    // ----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    // ----------------------------------------------------------------------
    public static Bot getUsersFromJsonString(String jsonString)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Bot.class);
    }

    // ----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    // ----------------------------------------------------------------------
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // ----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    // ----------------------------------------------------------------------
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Timestamp getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Timestamp lastSeen) {
		this.lastSeen = lastSeen;
	}

	public Timestamp getLastProcessed() {
		return lastProcessed;
	}

	public void setLastProcessed(Timestamp lastProcessed) {
		this.lastProcessed = lastProcessed;
	}

	public Integer getInformative() {
		return informative;
	}

	public void setInformative(Integer informative) {
		this.informative = informative;
	}

	public Integer getBatchEnable() {
		return batchEnable;
	}

	public void setBatchEnable(Integer batchEnable) {
		this.batchEnable = batchEnable;
	}

	public Integer getExternal() {
		return external;
	}

	public void setExternal(Integer external) {
		this.external = external;
	}

	public Integer getParserId() {
		return parserId;
	}

	public void setParserId(Integer parserId) {
		this.parserId = parserId;
	}

	public List<Pack> getPackList() {
		return this.packList;
	}

	public Integer getPackSize() {
		if( this.getPackList() != null ) {
			return this.getPackList().size();
		} else {
			return 0;
		}
	}

    // ----------------------------------------------------------------------
    // toString METHOD
    // ----------------------------------------------------------------------
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(this.getId());
        sb.append("]:");
        sb.append(this.getName());
        sb.append("|");
        sb.append(this.getUrl());
        sb.append("|");
        sb.append(this.getType());
        sb.append("|");
        sb.append(this.getOwner());
        sb.append("|");
        sb.append(this.getStatusId());
        sb.append("|");
        sb.append(this.getLastSeen());
        sb.append("|");
        sb.append(this.getLastProcessed());
        sb.append("|");
        sb.append(this.getInformative());
        sb.append("|");
        sb.append(this.getBatchEnable());
        sb.append("|");
        sb.append(this.getExternal());
        sb.append("|");
        sb.append(this.getParserId());
        return sb.toString();
    }

}
