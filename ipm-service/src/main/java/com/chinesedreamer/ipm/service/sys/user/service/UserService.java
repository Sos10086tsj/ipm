//package com.chinesedreamer.ipm.service.sys.user.service;
//
//import java.util.List;
//
//import com.chinesedreamer.ipm.domain.sys.user.constant.UserStatus;
//import com.chinesedreamer.ipm.domain.sys.user.entity.User;
//
//public interface UserService {
//	/**
//	 * 根据ID找用户
//	 * @param id
//	 * @return
//	 */
//	public User getUser(Long id);
//	/**
//	 * 根据用户名查找用户
//	 * @param username
//	 * @return
//	 */
//	public User getUser(String username);
//	
//	/**
//	 * 保存客户
//	 * @param user
//	 * @return
//	 */
//	public User saveUser(User user);
//	
//	/**
//	 * 更新用户
//	 * @param user
//	 * @return
//	 */
//	public User updateUser(User user, User updateUser);
//	
//	/**
//	 * 删除用户
//	 * @param user
//	 */
//	public void deleteUser(User user);
//	
//	/**
//	 * 更新用户状态
//	 * @param user
//	 * @param status
//	 * @return
//	 */
//	public User updateUserStatus(User user, UserStatus status, User updateUser);
//	
//	/**
//	 * 创建新用户
//	 * @param user
//	 * @param createUserId
//	 */
//	public void createUser(User user, Long createUserId);
//	
//	/**
//	 * 邀请用户加入组织
//	 * @param user
//	 * @param invitedUser
//	 */
//	public void inviteUser(User user, User invitedUser);
//	
//	/**
//	 * 邀请多个用户加入组织
//	 * @param user
//	 * @param invitedUsers
//	 */
//	public void inviteUsers(User user, List<User> invitedUsers);
//	
//	/**
//	 * 退出客户组
//	 * @param user
//	 * @return
//	 */
//	public User leaveCustomer(User user);
//
//	/**
//	 * 根据邮箱查找用户
//	 * @param email
//	 * @return
//	 */
//	public User getByEmail(String email);
//}
