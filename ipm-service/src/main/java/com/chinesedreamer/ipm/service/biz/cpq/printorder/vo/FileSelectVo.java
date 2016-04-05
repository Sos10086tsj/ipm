package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20152:21:55 PM
 * @version beta
 */
public class FileSelectVo {
	private String value;
	private String label;
	private String clothingType;
	
	public FileSelectVo(String value,String label,String clothingType){
		this.value = value;
		this.label = label;
		this.clothingType = clothingType;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public String getClothingType() {
		return clothingType;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setClothingType(String clothingType) {
		this.clothingType = clothingType;
	}
	
	
}
