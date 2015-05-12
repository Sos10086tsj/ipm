package com.chinesedreamer.ipm.common.base;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20153:19:39 PM
 * @version beta
 */
public abstract class IpmException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9031666610402286973L;
	
	private @Getter String code;
	
	private @Getter String message;
	
	public IpmException(String message, Throwable cause){
		super(message, cause);
		this.message = message;
	}
	
	public IpmException(String message){
		super(message);
		this.message = message;
	}
}
