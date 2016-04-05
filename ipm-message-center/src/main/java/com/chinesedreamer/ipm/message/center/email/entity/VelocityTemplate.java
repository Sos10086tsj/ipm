package com.chinesedreamer.ipm.message.center.email.entity;

/**
 * Description: an entity to fill up Velocity template
 * @author Paris
 * @date Dec 22, 20144:03:47 PM
 * @version beta
 */
public class VelocityTemplate {
	private String userName;
	private String validationUrl;
	public String getUserName() {
		return userName;
	}
	public String getValidationUrl() {
		return validationUrl;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setValidationUrl(String validationUrl) {
		this.validationUrl = validationUrl;
	}
	
	
}
