package com.chinesedreamer.ipm.service.system.company.service;

import com.chinesedreamer.ipm.domain.base.service.IpmService;
import com.chinesedreamer.ipm.domain.system.company.model.Company;
import com.chinesedreamer.ipm.service.system.company.model.CompanyVo;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:21:54 AM
 * @version beta
 */
public interface CompanyService extends IpmService<Company, Long>{
	/**
	 * 创建客户
	 * @param vo
	 * @return
	 */
	public Company createCompany(CompanyVo vo);
}
