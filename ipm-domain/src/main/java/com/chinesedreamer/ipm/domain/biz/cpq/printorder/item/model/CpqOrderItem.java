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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CpqOrder cpqOrder;
}
