package com.chinesedreamer.ipm.service.system.user.model;

public class UserVo {
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
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getName() {
		return name;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
