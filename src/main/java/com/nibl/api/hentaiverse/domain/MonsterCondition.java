package com.nibl.api.hentaiverse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MonsterCondition implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "parameter")
	private String parameter;
	
	@Column(name = "condition")
	private String condition;
	
	@Column(name = "values")
	private List<String> values;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition.toLowerCase();
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getParameter());
		sb.append("|");
		sb.append(this.getCondition());
		sb.append("|");
		sb.append(this.getValues().toString());
		
		return sb.toString();
	}
}
