package com.chinesedreamer.ipm.domain.system.company.info.model.vo;

import lombok.Setter;

import lombok.Getter;

public @Getter @Setter class CompanyInfoVo {
	private Long companyId;
	private Long scale;
	private String scaleDescription;
	private Long industry;
	private String industryDescription;
	private String contactEmail;
	private String contactPhone;
}
