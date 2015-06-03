package com.chinesedreamer.ipm.service.biz.cpq.printorder.constant;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20159:20:20 AM
 * @version beta
 */
public enum CpqExcelType {
	JIANAN("佳楠"),PUTIANMU("普天姆");
	
	private final @Getter String name;
	
	private CpqExcelType(String name){
		this.name = name;
	}
}
