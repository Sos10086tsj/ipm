package com.chinesedreamer.ipm.domain.system.user.logic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.system.user.logic.UserLogic;
import com.chinesedreamer.ipm.domain.system.user.model.User;
import com.chinesedreamer.ipm.domain.system.user.repository.UserRepository;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20151:07:18 PM
 * @version beta
 */
@Service
public class UserLogicImpl extends IpmLogicImpl<User, Long> implements UserLogic{
	
	@Resource
	private UserRepository repository;

	@Override
	public User getUser(String username) {
		return this.repository.findByUsername(username);
	}

}
