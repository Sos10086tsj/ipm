package com.chinesedreamer.ipm.domain.supp.dictionary.model;

public enum DictionaryStatus {
	DEFAULT("无效"), ACTIVE("服务中"),INACTIVE("停用"),DELETED("删除");
	private final String info;
	
	private DictionaryStatus(String info){
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
}
