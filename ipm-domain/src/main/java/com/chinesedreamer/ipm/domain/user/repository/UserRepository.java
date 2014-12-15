package com.chinesedreamer.ipm.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chinesedreamer.ipm.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
