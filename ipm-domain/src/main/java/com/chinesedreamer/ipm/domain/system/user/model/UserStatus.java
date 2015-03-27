package com.chinesedreamer.ipm.domain.system.user.model;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20151:03:44 PM
 * @version beta
 */
public enum UserStatus {
	DEFAULT("无效客户"),ACTIVE("服务中"),INACTIVE("停用"),FROZEEN("禁用"),DELETED("删除的");
	
	private final @Getter String info;
	
	private UserStatus(String info){
		this.info = info;
	}
}
