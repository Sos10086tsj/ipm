package com.chinesedreamer.ipm.domain.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract  class IpmVersionEntity<ID extends Serializable> extends IpmEntity<ID>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 434358638393316554L;
	
	@Version
	@Column(name = "version")
	private @Getter @Setter Long version = 0l;
}
