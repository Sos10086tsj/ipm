package com.chinesedreamer.ipm.domain.system.company.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.system.company.info.model.CompanyInfo;

/**
 * Description: company entity of table ipm_sys_company. this table store info. of companies
 * 
 * @author Paris
 * @date Mar 26, 20154:58:36 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_customer")
public @Getter @Setter class Company extends IpmVersionEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135314647345717126L;

	/**
	 * 每个客户有一个唯一code
	 */
	@Column
	private String code;

	/**
	 * 客户名称
	 */
	@Column
	private String name;

	/**
	 * 客户状态
	 */
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private CompanyStatus status;

	/**
	 * vip等级
	 */
	@Column
	private Integer vip;

	/**
	 * 注册时间
	 */
	@Column(name = "register_date")
	@DateTimeFormat(pattern = "yyyy-MM-HH hh:mm:ss")
	private Date registerDate;
	
	/** 外键 **/
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "company")
	private CompanyInfo companyInfo;
}
