package com.chinesedreamer.ipm.domain.system.company.repository;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.system.company.model.Company;

/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:11:04 PM
 * @version beta
 */
public interface CompanyRepository extends IpmRepository<Company, Long>{
	public Company findByCode(String code);
}
