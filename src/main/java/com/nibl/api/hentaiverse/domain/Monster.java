package com.nibl.api.hentaiverse.domain;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibl.api.hentaiverse.views.Views;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "hvmonsterlist", catalog = "hvmonsterlist")
public class Monster implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO change this to use reflection
	public enum MonsterAttributeMapper{
	    MONSTERID("monsterid"),
	    LASTUPDATE("lastupdate"),
	    MONSTERNAME("monstername"),
	    MONSTERCLASS("monsterclass"),
	    PLVL("plvl"),
	    TRAINER("trainer"),
	    ATTACK("attack"),
	    FIRE("fire"),
	    COLD("cold"),
	    ELEC("elec"),
	    WIND("wind"),
	    HOLY("holy"),
	    DARK("dark"),
	    CRUSHING("crushing"),
	    SLASHING("slashing"),
	    PIERCING("piercing");
	    
	    private final String attributeName;
	    
	    MonsterAttributeMapper(String attributeName) {
	        this.attributeName = attributeName;
	    }
	    
	    public static MonsterAttributeMapper fromString(String text) {
	        for (MonsterAttributeMapper b : MonsterAttributeMapper.values()) {
	          if (b.attributeName.equalsIgnoreCase(text)) {
	            return b;
	          }
	        }
	        return null;
	      }
	    
	    public Object getValue(Monster m){
	        switch( this.attributeName ) {
	            case "monsterid":
	                return m.getMonsterId();
	            case "lastupdate":
	                return m.getLastUpdate();
	            case "monstername":
	                return m.getMonsterName();
	            case "monsterclass":
	                return m.getMonsterClass();
	            case "plvl":
	                return m.getPlvl();
	            case "trainer":
	                return m.getTrainer();
	            case "attack":
	                return m.getAttack();
	            case "fire":
	                return m.getFire();
	            case "cold":
	                return m.getCold();
	            case "elec":
	                return m.getElec();
	            case "wind":
	                return m.getWind();
	            case "holy":
	                return m.getHoly();
	            case "dark":
	                return m.getDark();
	            case "crushing":
	                return m.getCrushing();
	            case "slashing":
	                return m.getSlashing();
	            case "piercing":
	                return m.getPiercing();
	        }
	        
	        return null;
	    }
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView(Views.InternalMonster.class)
	@Column(name = "id", nullable = false)
	private Long id;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@JsonView(Views.Monster.class)
	@Column(name = "monsterid")
	private Long monsterId;

	@JsonView(Views.Monster.class)
	@Column(name = "monstername")
	private String monsterName;

	@JsonView(Views.FullMonster.class)
	@Column(name = "class")
	private String monsterClass;

	@JsonView(Views.Monster.class)
	@Column(name = "plvl")
	private Integer plvl;

	@JsonView(Views.Monster.class)
	@Column(name = "trainer")
	private String trainer;

	@JsonView(Views.Monster.class)
	@Column(name = "attack")
	private String attack;

	@JsonView(Views.Monster.class)
	@Column(name = "fire")
	private Integer fire;

	@JsonView(Views.Monster.class)
	@Column(name = "cold")
	private Integer cold;

	@JsonView(Views.Monster.class)
	@Column(name = "elec")
	private Integer elec;

	@JsonView(Views.Monster.class)
	@Column(name = "wind")
	private Integer wind;

	@JsonView(Views.Monster.class)
	@Column(name = "holy")
	private Integer holy;

	@JsonView(Views.Monster.class)
	@Column(name = "dark")
	private Integer dark;

	@JsonView(Views.Monster.class)
	@Column(name = "crushing")
	private Integer crushing;

	@JsonView(Views.Monster.class)
	@Column(name = "slashing")
	private Integer slashing;

	@JsonView(Views.Monster.class)
	@Column(name = "piercing")
	private Integer piercing;

	@JsonView(Views.FullMonster.class)
	@Column(name = "lastupdate")
	@ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastUpdate;

	@JsonView(Views.FullMonster.class)
	@Column(name = "creationdate")
	@ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp creationDate;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Monster(Long id, Long monsterId, String monsterName, String monsterClass, Integer plvl, String trainer,
			String attack, Integer fire, Integer cold, Integer elec, Integer wind, Integer holy, Integer dark,
			Integer crushing, Integer slashing, Integer piercing, Timestamp lastupdate, Timestamp creationdate) {
		super();
		this.setId(id);
		this.setMonsterId(monsterId);
		this.setMonsterName(monsterName);
		this.setMonsterClass(monsterClass);
		this.setPlvl(plvl);
		this.setTrainer(trainer);
		this.setAttack(attack);
		this.setFire(fire);
		this.setCold(cold);
		this.setElec(elec);
		this.setWind(wind);
		this.setHoly(holy);
		this.setDark(dark);
		this.setCrushing(crushing);
		this.setSlashing(slashing);
		this.setPiercing(piercing);
		this.setLastUpdate(lastupdate);
		this.setCreationDate(creationdate);
	}

	public Monster() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------

	public static Monster getUsersFromJsonString(String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, Monster.class);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(Long monsterId) {
		this.monsterId = monsterId;
	}

	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	public String getMonsterClass() {
		return monsterClass;
	}

	public void setMonsterClass(String monsterClass) {
		this.monsterClass = monsterClass;
	}

	public Integer getPlvl() {
		return plvl;
	}

	public void setPlvl(Integer plvl) {
		this.plvl = plvl;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getAttack() {
		return attack;
	}

	public void setAttack(String attack) {
		this.attack = attack;
	}

	public Integer getFire() {
		return fire;
	}

	public void setFire(Integer fire) {
		this.fire = fire;
	}

	public Integer getCold() {
		return cold;
	}

	public void setCold(Integer cold) {
		this.cold = cold;
	}

	public Integer getElec() {
		return elec;
	}

	public void setElec(Integer elec) {
		this.elec = elec;
	}

	public Integer getWind() {
		return wind;
	}

	public void setWind(Integer wind) {
		this.wind = wind;
	}

	public Integer getHoly() {
		return holy;
	}

	public void setHoly(Integer holy) {
		this.holy = holy;
	}

	public Integer getDark() {
		return dark;
	}

	public void setDark(Integer dark) {
		this.dark = dark;
	}

	public Integer getCrushing() {
		return crushing;
	}

	public void setCrushing(Integer crushing) {
		this.crushing = crushing;
	}

	public Integer getSlashing() {
		return slashing;
	}

	public void setSlashing(Integer slashing) {
		this.slashing = slashing;
	}

	public Integer getPiercing() {
		return piercing;
	}

	public void setPiercing(Integer piercing) {
		this.piercing = piercing;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(this.getId());
		sb.append("]:");
		sb.append(this.getMonsterId());
		sb.append("|");
		sb.append(this.getMonsterName());
		sb.append("|");
		sb.append(this.getMonsterClass());
		sb.append("|");
		sb.append(this.getPlvl());
		sb.append("|");
		sb.append(this.getTrainer());
		sb.append("|");
		sb.append(this.getAttack());
		sb.append("|");
		sb.append(this.getFire());
		sb.append("|");
		sb.append(this.getCold());
		sb.append("|");
		sb.append(this.getElec());
		sb.append("|");
		sb.append(this.getWind());
		sb.append("|");
		sb.append(this.getHoly());
		sb.append("|");
		sb.append(this.getDark());
		sb.append("|");
		sb.append(this.getCrushing());
		sb.append("|");
		sb.append(this.getSlashing());
		sb.append("|");
		sb.append(this.getPiercing());
		sb.append("|");
		sb.append(this.getLastUpdate());
		sb.append("|");
		sb.append(this.getCreationDate());
		
		return sb.toString();
	}

}
