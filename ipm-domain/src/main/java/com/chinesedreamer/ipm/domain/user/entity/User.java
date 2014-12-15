package com.chinesedreamer.ipm.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.IpmEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sys_user")
public @Getter @Setter class User extends IpmEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5207690929461213864L;

	@Column
	private String username;
	public void setUsername(String username){
		//can not update username
		return;
	}
	
	/**
	 * 密码
	 */
	@Column
	private String password;
}
