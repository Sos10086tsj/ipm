package com.chinesedreamer.ipm.web.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20154:40:04 PM
 * @version beta
 */
public @Getter @Setter class ResponseVo {
	private Boolean success;
	private String errorMessage;
	private Object data;
}
