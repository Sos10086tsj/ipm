package com.chinesedreamer.ipm.domain.system.config.repository;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.system.config.model.IpmConfig;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20159:39:56 AM
 * @version beta
 */
public interface IpmConfigRepository extends IpmRepository<IpmConfig, Long>{
	public IpmConfig findByProperty(String property);
}
