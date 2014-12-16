package com.chinesedreamer.ipm.service.sys.user.service;

import com.chinesedreamer.ipm.domain.sys.user.constant.UserStatus;
import com.chinesedreamer.ipm.domain.sys.user.entity.User;

public interface UserService {
	/**
	 * 根据ID找用户
	 * @param id
	 * @return
	 */
	public User getUser(Long id);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User getUser(String username);
	
	/**
	 * 保存客户
	 * @param user
	 * @return
	 */
	public User saveUser(User user);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public User updateUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void deleteUser(User user);
	
	/**
	 * 更新用户状态
	 * @param user
	 * @param status
	 * @return
	 */
	public User updateUserStatus(User user, UserStatus status);
}
