package com.chinesedreamer.ipm.web.company.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.ipm.service.system.user.service.UserService;
import com.chinesedreamer.ipm.web.exception.ParameterException;
import com.chinesedreamer.ipm.web.vo.ResponseVo;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20154:15:25 PM
 * @version beta
 */
@Controller
@RequestMapping(value = "compnaymanage")
public class CompanyController {
	
	private Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Resource
	private UserService userService;
	
	private String companyId = "companyId";
	private String usernameParam = "username";
	private String passwordParam = "password";
	private String nameParam = "name";
	
	/**
	 * 进入用户创建页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="createuser", method = RequestMethod.GET)
	public String createuser(Model model, HttpServletRequest request){
		String companyId = request.getParameter(this.companyId);
		if (StringUtils.isEmpty(companyId)) {
			logger.error("could not create user without companyId.");
			throw new ParameterException("could not create user without companyId.");
		}
		model.addAttribute("companyId", companyId);
		return "/company/createUser";
	}
	
	/**
	 * 提交用户创建
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="createuser", method = RequestMethod.POST)
	public ResponseVo submitCreateuser(Model model, HttpServletRequest request){
		ResponseVo vo = new ResponseVo();
		
		String companyId = request.getParameter(this.companyId);
		String username = request.getParameter(this.usernameParam);
		String password = request.getParameter(this.passwordParam);
		String name = request.getParameter(this.nameParam);
		if (StringUtils.isEmpty(companyId) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(name)) {
			logger.error("mandatory parameter of creating user is missing, companyId:{}, username:{}, password:{}, name:{}.", companyId, username, password, name);
			throw new ParameterException("mandatory parameter of creating user is missing, companyId:" 
			+ companyId + ", username:" + username + ", password:" + password + ", name:" + name + ".");
		}
		try {
			this.userService.createUser(username, password, name, Long.parseLong(companyId));
			vo.setSuccess(Boolean.TRUE);
			vo.setData(username);
		} catch (Exception e) {
			vo.setSuccess(Boolean.FALSE);
			vo.setData(e.getMessage());
		}
		return vo;
	}
}
