package com.chinesedreamer.ipm.service.system.user.service;

import com.chinesedreamer.ipm.domain.base.service.IpmService;
import com.chinesedreamer.ipm.domain.system.user.model.User;
import com.chinesedreamer.ipm.service.system.user.model.UserVo;

public interface UserService extends IpmService<User, Long>{
	/**
	 * 创建用户，只能由管理员创建
	 * @param vo
	 */
	public void createUser(UserVo vo);
	
	/**
	 * 修改密码
	 * @param username
	 * @param newPassword
	 */
	public void updateUserPassword(String username, String newPassword);
	
	public void udpateUserInfo();
}
