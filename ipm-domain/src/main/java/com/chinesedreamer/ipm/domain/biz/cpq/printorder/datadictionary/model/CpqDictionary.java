package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ipm_biz_cpq_data_dictionary")
public @Getter @Setter class CpqDictionary extends IpmEntity<Long>{

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
}
