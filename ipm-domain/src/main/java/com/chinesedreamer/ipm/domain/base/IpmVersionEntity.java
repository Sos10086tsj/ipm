package com.chinesedreamer.ipm.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract @Getter @Setter class IpmVersionEntity<ID extends Serializable> implements Persistable<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 434358638393316554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;
	
	@Version
	@Column(name = "version")
	private Long version = 0l;
	
	public boolean isNew(){
		return null == getId();
	}
}
