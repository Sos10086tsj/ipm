package com.chinesedreamer.ipm.web.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import com.chinesedreamer.ipm.domain.sys.user.entity.User;


public class ShiroUtil {
	public static boolean checkUserOnline(String sessionId){
        SecurityManager securityManager = SecurityUtils.getSecurityManager();
        Subject subject = new Subject.Builder(securityManager).sessionId(sessionId).buildSubject();
        if(subject != null && subject.isAuthenticated()){
            return true;
        }
        return false;
    }
	
	public static User getUser(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal instanceof  User){
            return (User)principal;
        }
        return null;
    }
	
	public static void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
}
