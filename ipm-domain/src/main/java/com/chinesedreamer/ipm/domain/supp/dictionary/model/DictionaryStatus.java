package com.chinesedreamer.ipm.domain.supp.dictionary.model;

import lombok.Getter;

public enum DictionaryStatus {
	DEFAULT("无效"), ACTIVE("服务中"),INACTIVE("停用"),DELETED("删除");
	private final @Getter String info;
	
	private DictionaryStatus(String info){
		this.info = info;
	}
}
