package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 201511:13:15 AM
 * @version beta
 */
public @Getter @Setter class SelectVo {
	private String value;
	private String label;
	
	public SelectVo(String value,String label){
		this.value = value;
		this.label = label;
	}
}
