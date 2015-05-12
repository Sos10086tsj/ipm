package com.chinesedreamer.ipm.service.system.user.exception;

import com.chinesedreamer.ipm.common.base.IpmException;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20153:48:08 PM
 * @version beta
 */
public class UsernameOrPasswordNotMatchException extends IpmException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932970096223292004L;

	public UsernameOrPasswordNotMatchException(String message) {
		super(message);
	}

}
