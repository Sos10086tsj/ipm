package com.chinesedreamer.ipm.service.system.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.format.PinyinUtil;
import com.chinesedreamer.ipm.common.utils.security.EncryptionUtil;
import com.chinesedreamer.ipm.domain.base.service.impl.IpmServiceImpl;
import com.chinesedreamer.ipm.domain.system.user.logic.UserLogic;
import com.chinesedreamer.ipm.domain.system.user.model.User;
import com.chinesedreamer.ipm.service.system.user.model.UserVo;
import com.chinesedreamer.ipm.service.system.user.service.UserService;

@Service
public class UserServiceImpl extends IpmServiceImpl<User, Long>
	implements UserService{
	
	@Resource
	private UserLogic logic;

	@Override
	public void createUser(UserVo vo) {
		this.logic.save(this.genereateFromVo(vo));
	}
	
	private User genereateFromVo(UserVo vo) {
		User user = new User();
		user.setUsername(vo.getUsername());
		user.setSalt(EncryptionUtil.generateSalt(8));
		user.setPassword(EncryptionUtil.md5L32(vo.getUsername() + vo.getPassword() + user.getSalt()));
		user.setName(vo.getName());
		user.setCompanyId(vo.getCompanyId());
		user.setPinYin(PinyinUtil.getChinesePinyin(vo.getName()));
		user.setPinYinFirstLetter(PinyinUtil.getChineseFirstLetter(vo.getName()));
		return user;
	}

	@Override
	public void updateUserPassword(String username, String newPassword) {
		User user = this.logic.getUser(username);
		user.setPassword(EncryptionUtil.md5L32(user.getUsername() + user.getPassword() + user.getSalt()));
		this.logic.save(user);
	}

	@Override
	public void udpateUserInfo() {
		// TODO Auto-generated method stub
		
	}

}
