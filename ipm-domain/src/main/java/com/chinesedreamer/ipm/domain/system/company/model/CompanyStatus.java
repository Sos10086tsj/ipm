package com.chinesedreamer.ipm.domain.system.company.model;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:01:16 PM
 * @version beta
 */
public enum CompanyStatus {
	DEFAULT("无效客户"),ACTIVE("服务中"),INACTIVE("停用"),FROZEEN("禁用"),DELETED("删除的");
	
	private final @Getter String info;
	
	private CompanyStatus(String info){
		this.info = info;
	}
}
