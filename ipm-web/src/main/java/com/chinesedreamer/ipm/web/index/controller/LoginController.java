package com.chinesedreamer.ipm.web.index.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinesedreamer.ipm.service.system.user.service.UserService;
import com.chinesedreamer.ipm.web.exception.ParameterException;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20153:00:52 PM
 * @version beta
 */
@RequestMapping(value = "login")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	
	private String usernameParam = "username";
	private String passwordParam = "password";
	
	/**
	 * 用户登录
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model){
		return "login";
	}
	
	/**
	 * 提交登录
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submitLogin(Model model, HttpServletRequest request){
		String username = request.getParameter(this.usernameParam);
		String password = request.getParameter(this.passwordParam);
		//1. check username and passowrd not null
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			this.logger.error("access system with illegal username or password. username:{};password:{}.", username, password);
			throw new ParameterException("access system with illegal username or password. username:" + username + ";password:" + password);
		}
		//2. 登录
		try {
			this.userService.userLogin(username, password);
		} catch (Exception e) {
			this.logger.error("login error", e);
			model.addAttribute("errorMessage", e.getMessage());
			return "/error/exception";
		}
		model.addAttribute("user", this.userService.getUser(username));
		logger.info("user:{} login success.", username);
		return "index";
	}
}
