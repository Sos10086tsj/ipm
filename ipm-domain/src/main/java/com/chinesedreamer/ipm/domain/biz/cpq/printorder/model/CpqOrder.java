package com.chinesedreamer.ipm.domain.biz.cpq.printorder.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;

/**
 * 客户的订单信息
 *
 * @author Paris
 *
 */
@Entity
@Table(name = "ipm_biz_cpq_print_order")
public class CpqOrder extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048133666422905794L;
	
	/**
	 * 订单号码
	 */
	@Column(name = "order_no")
	private String orderNo;
	
	@Column(name = "order_no_type")
	private String orderNoType;//尾号
	
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
	
	@Column(name = "pdf_id")
	private Long pdfId;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "pdf_id", referencedColumnName = "id", insertable =false, updatable = false)
	private CpqFile pdf;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "cpqOrder")
	private List<CpqOrderItem> cpqOrderItems;

	@Override
	public String toString() {
		return "CpqOrder [orderNo=" + orderNo + ", styleNo=" + styleNo
				+ ", maker=" + maker + ", customer=" + customer
				+ ", price=" + price ;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getOrderNoType() {
		return orderNoType;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public String getMaker() {
		return maker;
	}

	public String getCustomer() {
		return customer;
	}

	public String getDescription() {
		return description;
	}

	public String getQuality() {
		return quality;
	}

	public String getFilling() {
		return filling;
	}

	public String getPrice() {
		return price;
	}

	public String getDelivery() {
		return delivery;
	}

	public String getWeight() {
		return weight;
	}

	public String getWashing() {
		return washing;
	}

	public String getPacking() {
		return packing;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getShippingMode() {
		return shippingMode;
	}

	public String getTerms_payment() {
		return terms_payment;
	}

	public Long getPdfId() {
		return pdfId;
	}

	public CpqFile getPdf() {
		return pdf;
	}

	public List<CpqOrderItem> getCpqOrderItems() {
		return cpqOrderItems;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setOrderNoType(String orderNoType) {
		this.orderNoType = orderNoType;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public void setFilling(String filling) {
		this.filling = filling;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setWashing(String washing) {
		this.washing = washing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setShippingMode(String shippingMode) {
		this.shippingMode = shippingMode;
	}

	public void setTerms_payment(String terms_payment) {
		this.terms_payment = terms_payment;
	}

	public void setPdfId(Long pdfId) {
		this.pdfId = pdfId;
	}

	public void setPdf(CpqFile pdf) {
		this.pdf = pdf;
	}

	public void setCpqOrderItems(List<CpqOrderItem> cpqOrderItems) {
		this.cpqOrderItems = cpqOrderItems;
	}
	
	
}
