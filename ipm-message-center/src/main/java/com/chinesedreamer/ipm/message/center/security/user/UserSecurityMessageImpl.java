package com.chinesedreamer.ipm.message.center.security.user;

import org.springframework.stereotype.Service;

@Service
public class UserSecurityMessageImpl implements UserSecurityMessage{

	@Override
	public void sendRetrivePasswordMessage(String username,String secureEmail) {
		//获得html通知模板
		
	}

}
