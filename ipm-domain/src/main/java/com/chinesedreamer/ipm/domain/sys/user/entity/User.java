package com.chinesedreamer.ipm.domain.sys.user.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.ipm.domain.base.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.sys.customer.entity.Customer;
import com.chinesedreamer.ipm.domain.sys.user.constant.UserStatus;
import com.chinesedreamer.ipm.domain.sys.user.util.UserUtil;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sys_user")
public @Getter @Setter class User extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5207690929461213864L;

	/**
	 * 用户名
	 */
	@Column
	private String username;
	public void setUsername(String username){
		//can not update username
		return;
	}
	
	/**
	 * 随机盐
	 */
	@Column
	private String salt = UserUtil.generateSalt();
	public void setSalt(String salt){
		//can not update salt
		return;
	}
	
	/**
	 * 密码
	 */
	@Column
	private String password;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ACTIVE;
	
	/**
	 * 客户code
	 */
	@Column(name = "cust_code")
	private String custCode;

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate = new Date();
	
	@Column(name = "create_user")
	private Long createUserId;
	
	@Column(name = "last_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	
	@Column(name = "last_update_user")
	private Long lastUpdateUserId;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
	private UserProfile userProfile;
}
