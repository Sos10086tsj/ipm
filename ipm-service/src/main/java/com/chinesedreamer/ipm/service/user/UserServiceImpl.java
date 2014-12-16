package com.chinesedreamer.ipm.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.user.entity.User;
import com.chinesedreamer.ipm.domain.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserRepository repository;

	@Override
	public User getUser(Long id) {
		return this.repository.findOne(id);
	}

}
