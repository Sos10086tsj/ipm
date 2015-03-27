package com.chinesedreamer.ipm.service.supp.dictionary.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 201512:34:43 PM
 * @version beta
 */
public @Getter @Setter class DictionaryVo {
	private Long id;
	private String type;
	private String typeDescription;
	private String code;
	private String name;
	private String status;
	private String statusDescription;
}
