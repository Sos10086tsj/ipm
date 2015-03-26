package com.chinesedreamer.ipm.domain.system.company.info.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.supp.dictionary.model.DictionaryType;
import com.chinesedreamer.ipm.domain.system.company.model.Company;

/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:22:54 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_company_info")
public @Getter @Setter class CompanyInfo extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8140118347792089825L;

	/**
	 * 客户外键
	 */
	@Column(name = "company_code")
	private String companyCode;
	
	/**
	 * 规模
	 */
	@Column(name = "scale")
	@Enumerated(EnumType.ORDINAL)
	private DictionaryType scale;
	
	/**
	 * 行业
	 */
	@Column(name = "industry")
	@Enumerated(EnumType.ORDINAL)
	private DictionaryType industry;
	
	/**
	 * 联系人邮箱
	 */
	@Column(name = "contact_email")
	private String contactEmail;
	
	/**
	 * 联系人电话
	 */
	@Column(name = "contact_phone")
	private String contactPhone;
	
	/** 外键 **/
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_code", referencedColumnName = "code", insertable = false, updatable = false)
	private Company company;
}
