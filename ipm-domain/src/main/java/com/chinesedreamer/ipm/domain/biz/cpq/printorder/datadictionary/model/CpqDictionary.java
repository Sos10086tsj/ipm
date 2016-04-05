package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;

@Entity
@Table(name = "ipm_biz_cpq_data_dictionary")
public class CpqDictionary extends IpmEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7345580607033669928L;

	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private CpqDictionaryType type;
	
	@Column
	private String property;
	
	@Column
	private String value;
	
	@Column
	private Integer seq;

	public CpqDictionaryType getType() {
		return type;
	}

	public String getProperty() {
		return property;
	}

	public String getValue() {
		return value;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setType(CpqDictionaryType type) {
		this.type = type;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	
}
