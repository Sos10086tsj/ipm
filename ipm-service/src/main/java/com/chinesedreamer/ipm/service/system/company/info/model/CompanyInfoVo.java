package com.chinesedreamer.ipm.service.system.company.info.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 201511:36:49 AM
 * @version beta
 */
public @Getter @Setter class CompanyInfoVo {
	private Long id;
	private Long companyId;
	private Long scale;
	private String scaleDescription;
	private Long industry;
	private String industryDescription;
	private String email;
	private String phone;
}
