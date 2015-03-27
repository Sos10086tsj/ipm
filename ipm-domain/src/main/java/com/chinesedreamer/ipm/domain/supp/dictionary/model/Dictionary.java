package com.chinesedreamer.ipm.domain.supp.dictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;

@Entity
@Table(name = "ipm_sys_supp_dictionary")
public @Getter @Setter class Dictionary extends IpmVersionEntity<Long>{

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
}
