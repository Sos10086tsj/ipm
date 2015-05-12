package com.chinesedreamer.ipm.service.system.user.exception;

import com.chinesedreamer.ipm.common.base.IpmException;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20153:45:58 PM
 * @version beta
 */
public class UserNotExistException extends IpmException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4889219624188043142L;

	public UserNotExistException(String message) {
		super(message);
	}

}
