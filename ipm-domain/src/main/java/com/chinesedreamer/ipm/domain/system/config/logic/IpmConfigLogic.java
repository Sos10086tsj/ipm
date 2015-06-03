package com.chinesedreamer.ipm.domain.system.config.logic;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.system.config.model.IpmConfig;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20159:42:07 AM
 * @version beta
 */
public interface IpmConfigLogic extends IpmLogic<IpmConfig, Long>{
	public IpmConfig findByProperty(String property);
}
