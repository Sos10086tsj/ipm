//package com.chinesedreamer.ipm.service.sys.user.service;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.chinesedreamer.ipm.domain.sys.user.constant.UserStatus;
//import com.chinesedreamer.ipm.domain.sys.user.entity.User;
//import com.chinesedreamer.ipm.domain.sys.user.entity.UserProfile;
//import com.chinesedreamer.ipm.domain.sys.user.repository.UserProfileRepository;
//import com.chinesedreamer.ipm.domain.sys.user.repository.UserRepository;
//
//@Service
//public class UserServiceImpl implements UserService{
//	@Resource
//	private UserRepository repository;
//	
//	@Resource
//	private UserProfileRepository userProfileRepository;
//
//	@Override
//	public User getUser(Long id) {
//		return this.repository.findOne(id);
//	}
//
//	@Override
//	public User getUser(String username) {
//		return this.repository.findByUsername(username);
//	}
//
//	@Override
//	public User saveUser(User user) {
//		return this.repository.save(user);
////		UserProfile userProfile = user.getUserProfile();
////		userProfile.setUserId(dbUser.getId());
////		this.userProfileRepository.save(userProfile);
////		return dbUser;
//	}
//
//	@Override
//	public User updateUser(User user, User updateUser) {
//		user.setLastUpdateDate(new Date());
//		user.setLastUpdateUserId(updateUser.getId());
//		return this.repository.saveAndFlush(user);
//	}
//
//	@Override
//	public void deleteUser(User user) {
//		this.repository.delete(user);
//	}
//
//	@Override
//	public User updateUserStatus(User user, UserStatus status, User updateUser) {
//		user.setLastUpdateDate(new Date());
//		user.setLastUpdateUserId(updateUser.getId());
//		user.setStatus(status);
//		return this.repository.saveAndFlush(user);
//	}
//
//	@Override
//	public void createUser(User user, Long createUserId) {
//		user.setCreateUserId(createUserId);
//		this.repository.save(user);
//	}
//
//	@Override
//	public void inviteUser(User user, User invitedUser) {
//		invitedUser.setCustCode(user.getCustCode());
//		invitedUser.setLastUpdateDate(new Date());
//		invitedUser.setLastUpdateUserId(user.getId());
//		this.repository.save(invitedUser);
//	}
//
//	@Override
//	public void inviteUsers(User user, List<User> invitedUsers) {
//		for (User invitedUser : invitedUsers) {
//			invitedUser.setCustCode(user.getCustCode());
//			invitedUser.setLastUpdateDate(new Date());
//			invitedUser.setLastUpdateUserId(user.getId());
//		}
//		this.repository.save(invitedUsers);
//	}
//
//	@Override
//	public User leaveCustomer(User user) {
//		user.setCustCode(null);
//		return this.repository.save(user);
//	}
//
//	@Override
//	public User getByEmail(String email) {
//		UserProfile userProfile = this.userProfileRepository.findByEmail(email);
//		if (null == userProfile) {
//			return null;
//		}
//		return this.userProfileRepository.findByEmail(email).getUser();
//	}
//
//}
