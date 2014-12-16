package com.chinesedreamer.ipm.domain.sys.customer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.sys.customer.constant.CustomerStatus;
import com.chinesedreamer.ipm.domain.sys.customer.constant.CustomerType;

@Entity
@Table(name = "sys_customer")
public @Getter @Setter class Customer extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7888182665776033944L;

	/**
	 * 客户code
	 */
	@Column(name = "cust_code")
	private String custCode;
	public void setCustCode(String custCode){
		return;
	}
	
	/**
	 * 客户名字
	 */
	@Column
	private String name;
	
	/**
	 * 客户类型：适用、签约、vip
	 */
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private CustomerType type;
	
	/**
	 * 有效期起始时间
	 */
	@Column(name = "valid_from")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date validFrom;
	
	/**
	 * 有效期终止时间
	 */
	@Column(name = "valid_to")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date validTo;
	
	/**
	 * 状态：ACTIVE、INACTIVE、FROZEN
	 */
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate = new Date();
	
	/**
	 * 创建用户ID
	 */
	@Column(name = "create_user")
	private Long createUserId;
	
	/**
	 * 最后更新时间
	 */
	@Column(name = "last_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	
	/**
	 * 最后更新用户ID
	 */
	@Column(name = "last_update_user")
	private Long lastUpdateUserId;
}
