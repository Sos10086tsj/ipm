package com.chinesedreamer.ipm.domain.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract  class IpmVersionEntity<ID extends Serializable> extends IpmEntity<ID>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 434358638393316554L;
	
	@Version
	@Column(name = "version")
	private Long version = 0l;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
