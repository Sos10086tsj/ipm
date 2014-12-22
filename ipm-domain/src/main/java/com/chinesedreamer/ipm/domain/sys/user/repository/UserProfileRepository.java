package com.chinesedreamer.ipm.domain.sys.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chinesedreamer.ipm.domain.sys.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	public UserProfile findByEmail(String email);
}
