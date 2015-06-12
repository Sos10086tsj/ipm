package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.excel;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 12, 20153:56:36 PM
 * @version beta
 */
public @Getter @Setter class PutianmuExcelOrders {
	private String orderNo;
	private String styleNo;
	
	public PutianmuExcelOrders(String orderNo, String styleNo){
		this.orderNo = orderNo;
		this.styleNo = styleNo;
	}
}
