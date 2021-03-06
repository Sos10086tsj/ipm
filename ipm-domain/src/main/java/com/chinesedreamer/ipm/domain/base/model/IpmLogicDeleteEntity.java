package com.chinesedreamer.ipm.domain.base.model;

import java.io.Serializable;

import javax.persistence.Column;
/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20154:56:33 PM
 * @version beta
 */
public abstract class IpmLogicDeleteEntity<ID extends Serializable> extends IpmEntity<ID>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5671224425420703646L;

	@Column(name = "deleted",columnDefinition = "TINYINT(1)")
	private Boolean deleted;

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	

}
