package com.chinesedreamer.ipm.web.index.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinesedreamer.ipm.message.center.security.user.UserSecurityMessage;

/**
 * Description: login交由shiro控制
 * Copyright: Copyright (c) 2014
 * @author Paris Tao
 * @date Dec 17, 2014
 * @version 1.0
 */
@Controller
public class IndexController {
//	@Resource
//	private UserService userService;
	
	@Resource
	private UserSecurityMessage userSecurityMessage;
	
	/**
	 * 用户注册
	 * @param model
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "register", method = RequestMethod.POST)
//	public String register(Model model, HttpServletRequest request){
//		User user = IndexHelper.converUser(request);
//		User dbUser = this.userService.saveUser(user);
//		model.addAttribute("userVo", IndexHelper.converIndexVo(dbUser));
//		return "/index/login";
//	}
//	
//	@RequestMapping(value = "retrievePassword", method = RequestMethod.GET)
//	public String retrievePassword(Model model, HttpServletRequest request){
//		String email = request.getParameter("secureEmail").trim();
//		User user = this.userService.getByEmail(email);
//		if (null != user) {
//			this.userSecurityMessage.sendRetrivePasswordMessage(user.getUsername(), email);
//		}
//		return "index/retrievePasswordWarning";
//	}
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(Model model){
		return "login";
	}
}
