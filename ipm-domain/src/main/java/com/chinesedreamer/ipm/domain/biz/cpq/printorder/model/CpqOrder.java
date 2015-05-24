package com.chinesedreamer.ipm.domain.biz.cpq.printorder.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;

/**
 * 客户的订单信息
 *
 * @author Paris
 *
 */
@Entity
@Table(name = "ipm_biz_cpq_print_order")
public @Getter @Setter class CpqOrder extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048133666422905794L;
	
	/**
	 * 订单号码
	 */
	@Column(name = "order_no")
	private String orderNo;
	
	/**
	 * 款号
	 */
	@Column(name = "style_no")
	private String styleNo;
	
	@Column
	private String maker;
	
	@Column
	private String customer;
	
	@Column
	private String description;
	
	@Column
	private String quality;
	
	@Column
	private String filling;
	
	@Column
	private String price;
	
	@Column
	private String delivery;
	
	@Column
	private String weight;
	
	@Column
	private String washing;
	
	@Column
	private String packing;
	
	@Column
	private String remarks;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "shipping_mode")
	private String shippingMode;
	
	@Column(name = "terms_payment")
	private String terms_payment;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "cpqOrder")
	private List<CpqOrderItem> cpqOrderItems;

	@Override
	public String toString() {
		return "CpqOrder [orderNo=" + orderNo + ", styleNo=" + styleNo
				+ ", maker=" + maker + ", customer=" + customer
				+ ", price=" + price ;
	}
	
	
}
