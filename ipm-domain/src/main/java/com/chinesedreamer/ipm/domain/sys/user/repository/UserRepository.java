package com.chinesedreamer.ipm.domain.sys.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chinesedreamer.ipm.domain.sys.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username);
}
