package com.chinesedreamer.ipm.service.system.user.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.security.EncryptionUtil;
import com.chinesedreamer.ipm.domain.base.service.impl.IpmServiceImpl;
import com.chinesedreamer.ipm.domain.system.user.logic.UserLogic;
import com.chinesedreamer.ipm.domain.system.user.model.User;
import com.chinesedreamer.ipm.domain.system.user.model.UserStatus;
import com.chinesedreamer.ipm.service.system.user.exception.UserFrozenException;
import com.chinesedreamer.ipm.service.system.user.exception.UserNotExistException;
import com.chinesedreamer.ipm.service.system.user.exception.UsernameOrPasswordNotMatchException;
import com.chinesedreamer.ipm.service.system.user.model.UserVo;
import com.chinesedreamer.ipm.service.system.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserLogic logic;

	@Override
	public void userLogin(String username, String password) {
		User user = this.logic.getUser(username);
		if (null == user) {
			throw new UserNotExistException("user " + username + " not exist.");
		}
		if (!user.getStatus().equals(UserStatus.ACTIVE)) {
			throw new UserFrozenException("user " + username + " is not active, please contact system manager to activiate first.");
		}
		if (user.getPassword().equals(EncryptionUtil.md5L32(username + password + user.getSalt()))) {
			throw new UsernameOrPasswordNotMatchException("user " + username + " and password " + password + " not exist.");
		}
	}

	@Override
	public UserVo getUser(String username) {
		User user = this.logic.getUser(username);
		return this.convert2Vo(user);
	}
	
	/**
	 * 将user entity转换成vo
	 * @param user
	 * @return
	 */
	private UserVo convert2Vo(User user) {
		UserVo vo = new UserVo();
		if (null != user) {
			vo.setId(user.getId());
			vo.setUsername(user.getUsername());
			vo.setName(user.getName());
			vo.setCompanyId(user.getCompanyId());
			vo.setCompanyName(user.getCompany().getName());
		}
		return vo;
	}

//	@Override
//	public void createUser(UserVo vo) {
//		this.logic.save(this.genereateFromVo(vo));
//	}
//	
//	private User genereateFromVo(UserVo vo) {
//		User user = new User();
//		user.setUsername(vo.getUsername());
//		user.setSalt(EncryptionUtil.generateSalt(8));
//		user.setPassword(EncryptionUtil.md5L32(vo.getUsername() + vo.getPassword() + user.getSalt()));
//		user.setName(vo.getName());
//		user.setCompanyId(vo.getCompanyId());
//		user.setPinYin(PinyinUtil.getChinesePinyin(vo.getName()));
//		user.setPinYinFirstLetter(PinyinUtil.getChineseFirstLetter(vo.getName()));
//		return user;
//	}
//
//	@Override
//	public void updateUserPassword(String username, String newPassword) {
//		User user = this.logic.getUser(username);
//		user.setPassword(EncryptionUtil.md5L32(user.getUsername() + user.getPassword() + user.getSalt()));
//		this.logic.save(user);
//	}
//
//	@Override
//	public void udpateUserInfo() {
//		// TODO Auto-generated method stub
//		
//	}

}
