package com.chinesedreamer.ipm.service.system.company.model;

import java.util.Date;

import com.chinesedreamer.ipm.service.system.company.info.model.CompanyInfoVo;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:22:50 AM
 * @version beta
 */
public class CompanyVo {
	//客户
	private Long id;
	private String code;
	private String name;
	private String status;
	private String statusDescription;
	private Integer vip;
	private Date registerDate;
	
	//客户信息
	private CompanyInfoVo infoVo;

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public Integer getVip() {
		return vip;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public CompanyInfoVo getInfoVo() {
		return infoVo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setInfoVo(CompanyInfoVo infoVo) {
		this.infoVo = infoVo;
	}
	
}
