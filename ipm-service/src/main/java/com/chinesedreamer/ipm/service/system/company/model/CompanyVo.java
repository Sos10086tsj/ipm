package com.chinesedreamer.ipm.service.system.company.model;

import java.util.Date;

import com.chinesedreamer.ipm.service.system.company.info.model.CompanyInfoVo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:22:50 AM
 * @version beta
 */
public @Getter @Setter class CompanyVo {
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
}
