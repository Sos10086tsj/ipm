package com.chinesedreamer.ipm.domain.system.config.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.system.config.model.IpmConfig;
import com.chinesedreamer.ipm.domain.system.config.repository.IpmConfigRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20159:42:14 AM
 * @version beta
 */
@Service
public class IpmConfigLogicImpl extends IpmLogicImpl<IpmConfig, Long> implements IpmConfigLogic{
	@Resource
	private IpmConfigRepository repository;

	@Override
	public IpmConfig findByProperty(String property) {
		return this.repository.findByProperty(property);
	}

}
