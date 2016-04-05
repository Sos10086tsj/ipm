package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo;

/**
 * Description: 报表打印前选择的order列表
 * @author Paris
 * @date Jun 8, 201510:51:46 AM
 * @version beta
 */
public class RptOrderSelectVo {
	private String orderNo;
	
	public RptOrderSelectVo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
