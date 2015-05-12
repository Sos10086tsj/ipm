package com.chinesedreamer.ipm.service.system.user.exception;

import com.chinesedreamer.ipm.common.base.IpmException;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20154:32:26 PM
 * @version beta
 */
public class DuplicateUserException extends IpmException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2864714117903906449L;

	public DuplicateUserException(String message) {
		super(message);
	}

}
