package com.chinesedreamer.ipm.service.sys.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.sys.user.constant.UserStatus;
import com.chinesedreamer.ipm.domain.sys.user.entity.User;
import com.chinesedreamer.ipm.domain.sys.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserRepository repository;

	@Override
	public User getUser(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public User getUser(String username) {
		return this.repository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		return this.repository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return this.repository.saveAndFlush(user);
	}

	@Override
	public void deleteUser(User user) {
		this.repository.delete(user);
	}

	@Override
	public User updateUserStatus(User user, UserStatus status) {
		user.setStatus(status);
		return this.repository.saveAndFlush(user);
	}

}
