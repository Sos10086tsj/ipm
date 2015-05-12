package com.chinesedreamer.ipm.service.system.user.model;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class UserVo {
	//用户信息
	private Long id;
	private String username;
//	private String password;
	private String name;
//	private String status;
//	private String statusDescription;
//	private Date registerDate;
	
	//用户个人信息
	
	//客户信息
	private Long companyId;
	private String companyName;
}
