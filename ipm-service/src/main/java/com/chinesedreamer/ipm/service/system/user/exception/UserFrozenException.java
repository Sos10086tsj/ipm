package com.chinesedreamer.ipm.service.system.user.exception;

import com.chinesedreamer.ipm.common.base.IpmException;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20153:54:32 PM
 * @version beta
 */
public class UserFrozenException extends IpmException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4869821228235260755L;

	public UserFrozenException(String message) {
		super(message);
	}

}
