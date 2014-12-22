package com.chinesedreamer.ipm.web.index.helper;

import javax.servlet.http.HttpServletRequest;

import com.chinesedreamer.ipm.domain.sys.user.entity.User;
import com.chinesedreamer.ipm.domain.sys.user.entity.UserProfile;
import com.chinesedreamer.ipm.web.index.vo.IndexVo;

public class IndexHelper {
	public static User converUser(HttpServletRequest request){
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//user profile
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String email = request.getParameter("email").trim();
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(firstName);
		userProfile.setLastName(lastName);
		userProfile.setEmail(email);
		return user;
	}
	
	public static IndexVo converIndexVo(User user){
		IndexVo vo = new IndexVo();
		vo.setName(user.getUserProfile().getFirstName() + user.getUserProfile().getLastName());
		vo.setUsername(user.getUsername());
		return vo;
	}
}
