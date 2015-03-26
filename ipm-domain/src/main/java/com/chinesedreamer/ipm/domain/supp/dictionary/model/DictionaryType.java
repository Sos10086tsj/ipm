package com.chinesedreamer.ipm.domain.supp.dictionary.model;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:30:05 PM
 * @version beta
 */
public enum DictionaryType {
	DEFAULT("其他"),SCALE("规模"),INDUSTRY("行业");
	
	private final @Getter String info;
	
	private DictionaryType(String info){
		this.info = info;
	}
}
