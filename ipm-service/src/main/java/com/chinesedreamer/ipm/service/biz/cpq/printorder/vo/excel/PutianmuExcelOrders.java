package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.excel;

/**
 * Description: 
 * @author Paris
 * @date Jun 12, 20153:56:36 PM
 * @version beta
 */
public class PutianmuExcelOrders {
	private String orderNo;
	private String styleNo;
	
	public PutianmuExcelOrders(String orderNo, String styleNo){
		this.orderNo = orderNo;
		this.styleNo = styleNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	
	
}
