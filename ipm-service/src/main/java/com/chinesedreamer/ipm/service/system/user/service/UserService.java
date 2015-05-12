package com.chinesedreamer.ipm.service.system.user.service;

import com.chinesedreamer.ipm.service.system.user.model.UserVo;


public interface UserService{
//	/**
//	 * 创建用户，只能由管理员创建
//	 * @param vo
//	 */
//	public void createUser(UserVo vo);
//	
//	/**
//	 * 修改密码
//	 * @param username
//	 * @param newPassword
//	 */
//	public void updateUserPassword(String username, String newPassword);
//	
//	public void udpateUserInfo();
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 */
	public void userLogin(String username, String password);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 */
	public UserVo getUser(String username);
	
	/**
	 * 创建用户
	 * @param username
	 * @param password
	 * @param name
	 * @param companyCode
	 */
	public void createUser(String username, String password, String name, Long companyId);
}
