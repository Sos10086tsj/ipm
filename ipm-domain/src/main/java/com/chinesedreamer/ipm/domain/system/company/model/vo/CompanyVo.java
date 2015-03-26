package com.chinesedreamer.ipm.domain.system.company.model.vo;

import java.util.Date;

import com.chinesedreamer.ipm.domain.system.company.info.model.vo.CompanyInfoVo;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CompanyVo {
	private String code;
	private String name;
	private Integer status;
	private String statusDescription;
	private Integer vip;
	private String vipDescription;
	private Date registerDate;
	
	//company info
	private CompanyInfoVo info;
}
