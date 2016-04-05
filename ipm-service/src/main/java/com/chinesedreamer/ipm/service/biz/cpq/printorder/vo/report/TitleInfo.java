package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report;

import com.chinesedreamer.ipm.domain.system.config.constant.IpmConfigConstant;

/**
 * Description: 
 * @author Paris
 * @date Jun 10, 20151:59:49 PM
 * @version beta
 */
public class TitleInfo {
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
			titleInfo.setCustomerName("KENTEX TRADING (HONGKONG) LTD");
			titleInfo.setAddress1("ROOM 1009, 10/F., TOWER 3, ENTERPRISE SQUARE 1,");
			titleInfo.setAddress2("9 SHEUNG YUET ROAD, KOWLOON BAY");
			titleInfo.setAddress3("HONG KONG");
		}else if (orderType.equals(IpmConfigConstant.CPQ_ORDER_TYPE_NETHERLANDS.toString())) {
			titleInfo.setCustomerName("SCOTCH & SODA B.V.");
			titleInfo.setAddress1("JACOBUS SPIJKERDREEF 20-24");
			titleInfo.setAddress2("2132 PZ HOOFDDORP");
			titleInfo.setAddress3("THE NETHERLANDS.");
		}
		return titleInfo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getAddress3() {
		return address3;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getDescription() {
		return description;
	}

	public String getInvoceNo() {
		return invoceNo;
	}

	public String getManufactorurer() {
		return manufactorurer;
	}

	public String getExporter() {
		return exporter;
	}

	public String getLcNo() {
		return lcNo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInvoceNo(String invoceNo) {
		this.invoceNo = invoceNo;
	}

	public void setManufactorurer(String manufactorurer) {
		this.manufactorurer = manufactorurer;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	
	
}
