package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 10, 20151:56:58 PM
 * @version beta
 */
public @Getter @Setter class ManufactoryInfo {
	private String name;
	private String address1;
	private String address2;
	
	public ManufactoryInfo(String name,String address1,String address2){
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
	}
}
