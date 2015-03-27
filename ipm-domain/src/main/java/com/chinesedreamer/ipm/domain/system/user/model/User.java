package com.chinesedreamer.ipm.domain.system.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.system.company.model.Company;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 201512:59:40 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_user")
public @Getter @Setter class User extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3247381729984860506L;

	@Column
	private String username;
	
	@Column
	private String salt;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column(name = "pinyin")
	private String pinYin;
	
	@Column(name = "pinyin_first_letter")
	private String pinYinFirstLetter;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;
	
	@Column(name = "register_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registerDate;
	
	@Column(name = "company_id")
	private Long companyId;
	
	//ORM
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Company company;
}
