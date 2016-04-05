package com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

@Entity
@Table(name = "ipm_biz_cpq_print_order_item")
public class CpqOrderItem extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7824485433669707857L;

	@Column(name = "order_id")
	private Long orderId;
	
	@Column
	private String color;
	
	@Column(name = "size_s")
	private Integer sizeS;
	
	@Column(name = "size_m")
	private Integer sizeM;
	
	@Column(name = "size_l")
	private Integer sizeL;
	
	@Column(name = "size_xl")
	private Integer sizeXl;
	
	@Column(name = "size_xxl")
	private Integer sizeXxl;
	
	@Column(name = "size_p")
	private Integer sizeP;
	
	@Column(name = "size_1")
	private Integer size1;
	
	@Column(name = "size_2")
	private Integer size2;

	@Column(name = "size_3")
	private Integer size3;
	
	@Column(name = "size_4")
	private Integer size4;
	
	@Column(name = "size_6")
	private Integer size6;
	
	@Column(name = "size_8")
	private Integer size8;
	
	@Column(name = "size_10")
	private Integer size10;
	
	@Column(name = "size_12")
	private Integer size12;
	
	@Column(name = "size_14")
	private Integer size14;
	
	@Column(name = "size_16")
	private Integer size16;
	
	@Column(name = "size_uni_1")
	private Integer sizeUNI1;
	
	@Column(name = "size_uni_2")
	private Integer sizeUNI2;
	
	@Column(name = "size_uni_3")
	private Integer sizeUNI3;
	
	@Column(name = "size_uni_4")
	private Integer sizeUNI4;
	
	@Column(name = "size_uni_5")
	private Integer sizeUNI5;
	
	@Column(name = "size_uni_6")
	private Integer sizeUNI6;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CpqOrder cpqOrder;

	public Long getOrderId() {
		return orderId;
	}

	public String getColor() {
		return color;
	}

	public Integer getSizeS() {
		return sizeS;
	}

	public Integer getSizeM() {
		return sizeM;
	}

	public Integer getSizeL() {
		return sizeL;
	}

	public Integer getSizeXl() {
		return sizeXl;
	}

	public Integer getSizeXxl() {
		return sizeXxl;
	}

	public Integer getSizeP() {
		return sizeP;
	}

	public Integer getSize1() {
		return size1;
	}

	public Integer getSize2() {
		return size2;
	}

	public Integer getSize3() {
		return size3;
	}

	public Integer getSize4() {
		return size4;
	}

	public Integer getSize6() {
		return size6;
	}

	public Integer getSize8() {
		return size8;
	}

	public Integer getSize10() {
		return size10;
	}

	public Integer getSize12() {
		return size12;
	}

	public Integer getSize14() {
		return size14;
	}

	public Integer getSize16() {
		return size16;
	}

	public Integer getSizeUNI1() {
		return sizeUNI1;
	}

	public Integer getSizeUNI2() {
		return sizeUNI2;
	}

	public Integer getSizeUNI3() {
		return sizeUNI3;
	}

	public Integer getSizeUNI4() {
		return sizeUNI4;
	}

	public Integer getSizeUNI5() {
		return sizeUNI5;
	}

	public Integer getSizeUNI6() {
		return sizeUNI6;
	}

	public CpqOrder getCpqOrder() {
		return cpqOrder;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setSizeS(Integer sizeS) {
		this.sizeS = sizeS;
	}

	public void setSizeM(Integer sizeM) {
		this.sizeM = sizeM;
	}

	public void setSizeL(Integer sizeL) {
		this.sizeL = sizeL;
	}

	public void setSizeXl(Integer sizeXl) {
		this.sizeXl = sizeXl;
	}

	public void setSizeXxl(Integer sizeXxl) {
		this.sizeXxl = sizeXxl;
	}

	public void setSizeP(Integer sizeP) {
		this.sizeP = sizeP;
	}

	public void setSize1(Integer size1) {
		this.size1 = size1;
	}

	public void setSize2(Integer size2) {
		this.size2 = size2;
	}

	public void setSize3(Integer size3) {
		this.size3 = size3;
	}

	public void setSize4(Integer size4) {
		this.size4 = size4;
	}

	public void setSize6(Integer size6) {
		this.size6 = size6;
	}

	public void setSize8(Integer size8) {
		this.size8 = size8;
	}

	public void setSize10(Integer size10) {
		this.size10 = size10;
	}

	public void setSize12(Integer size12) {
		this.size12 = size12;
	}

	public void setSize14(Integer size14) {
		this.size14 = size14;
	}

	public void setSize16(Integer size16) {
		this.size16 = size16;
	}

	public void setSizeUNI1(Integer sizeUNI1) {
		this.sizeUNI1 = sizeUNI1;
	}

	public void setSizeUNI2(Integer sizeUNI2) {
		this.sizeUNI2 = sizeUNI2;
	}

	public void setSizeUNI3(Integer sizeUNI3) {
		this.sizeUNI3 = sizeUNI3;
	}

	public void setSizeUNI4(Integer sizeUNI4) {
		this.sizeUNI4 = sizeUNI4;
	}

	public void setSizeUNI5(Integer sizeUNI5) {
		this.sizeUNI5 = sizeUNI5;
	}

	public void setSizeUNI6(Integer sizeUNI6) {
		this.sizeUNI6 = sizeUNI6;
	}

	public void setCpqOrder(CpqOrder cpqOrder) {
		this.cpqOrder = cpqOrder;
	}
	
	
}
