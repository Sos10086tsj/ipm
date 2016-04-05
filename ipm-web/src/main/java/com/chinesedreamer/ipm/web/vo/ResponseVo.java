package com.chinesedreamer.ipm.web.vo;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20154:40:04 PM
 * @version beta
 */
public class ResponseVo {
	private Boolean success;
	private String errorMessage;
	private Object data;
	public Boolean getSuccess() {
		return success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
