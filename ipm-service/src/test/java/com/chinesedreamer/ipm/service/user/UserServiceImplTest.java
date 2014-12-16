package com.chinesedreamer.ipm.service.user;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.ipm.common.base.SpringTest;
import com.chinesedreamer.ipm.domain.sys.user.entity.User;
import com.chinesedreamer.ipm.service.sys.user.service.UserService;

public class UserServiceImplTest extends SpringTest{

	@Resource
	private UserService userService;
	
	@Test
	public void testGetUser() {
		User u = this.userService.getUser(1l);
		assertNotNull(u);
		System.out.println(u.getUsername());
	}

}
