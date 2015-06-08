package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo;

import lombok.Getter;
import lombok.Setter;
/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20152:21:55 PM
 * @version beta
 */
public @Getter @Setter class FileSelectVo {
	private String value;
	private String label;
	private String clothingType;
	
	public FileSelectVo(String value,String label,String clothingType){
		this.value = value;
		this.label = label;
		this.clothingType = clothingType;
	}
}
