package com.chinesedreamer.ipm.domain.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.Persistable;


@MappedSuperclass
public abstract @Getter @Setter class IpmEntity<ID extends Serializable> implements Persistable<ID>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 361549063336380570L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;
	
	public boolean isNew(){
		return null == getId();
	}
}
