package com.chinesedreamer.ipm.domain.supp.dictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;

@Entity
@Table(name = "ipm_sys_supp_dictionary")
public class Dictionary extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 188931833746450237L;

	/**
	 * 字典类型
	 */
	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private DictionaryType type;
	
	/**
	 * 唯一值
	 */
	@Column
	private String code;
	
	/**
	 * 名字
	 */
	@Column
	private String name;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private DictionaryStatus status = DictionaryStatus.ACTIVE;

	public DictionaryType getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public DictionaryStatus getStatus() {
		return status;
	}

	public void setType(DictionaryType type) {
		this.type = type;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(DictionaryStatus status) {
		this.status = status;
	}
	
	
}
