package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report;

import com.chinesedreamer.ipm.domain.system.config.constant.IpmConfigConstant;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 10, 20151:59:49 PM
 * @version beta
 */
public @Getter @Setter class TitleInfo {
	private String customerName = "";
	private String address1 = "";
	private String address2 = "";
	private String address3 = "";
	private String styleNo = "";
	private String orderNo = "";
	private String description = "";
	private String invoceNo = "";
	private String manufactorurer = "";
	private String exporter = "";
	private String lcNo = "";
	
	public static TitleInfo getTitleInfo(String orderType){
		TitleInfo titleInfo = new TitleInfo();
		if (orderType.equals(IpmConfigConstant.CPQ_ORDER_TYPE_HK.toString())) {
			titleInfo.setCustomerName("SCOTCH & SODA B.V.");
			titleInfo.setAddress1("JACOBUS SPIJKERDREEF 20-24");
			titleInfo.setAddress2("2132 PZ HOOFDDORP");
			titleInfo.setAddress3("THE NETHERLANDS.");
		}else if (orderType.equals(IpmConfigConstant.CPQ_ORDER_TYPE_NETHERLANDS.toString())) {
			titleInfo.setCustomerName("SCOTCH & SODA B.V.");
			titleInfo.setAddress1("JACOBUS SPIJKERDREEF 20-24");
			titleInfo.setAddress2("2132 PZ HOOFDDORP");
			titleInfo.setAddress3("THE NETHERLANDS.");
		}
		return titleInfo;
	}
}
