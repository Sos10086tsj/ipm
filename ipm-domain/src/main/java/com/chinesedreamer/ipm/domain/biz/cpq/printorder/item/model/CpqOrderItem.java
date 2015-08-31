package com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

@Entity
@Table(name = "ipm_biz_cpq_print_order_item")
public @Getter @Setter class CpqOrderItem extends IpmVersionEntity<Long>{

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
	
}
