package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmEntity;

/**
 * Description: 工厂excel信息item
 * @author Paris
 * @date Jun 4, 20159:05:45 AM
 * @version beta
 */
@Entity
@Table(name = "ipm_biz_cpq_manufactory_order_item")
public @Getter @Setter class CpqManufacotryOrderItem extends IpmEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -433094055233117938L;

	@Column(name = "order_no")
	private String orderNo;//订单号
	
	@Column(name = "style_no")
	private String styleNo;//款号
	
	@Column
	private String from;
	
	@Column
	private String to;
	
	@Column
	private String color;//颜色
	
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
	
	@Column(name = "box_qty")
	private Integer boxQty;//箱数
	
	@Column(name = "pcs_per_box")
	private Integer pcsPerBox;//每箱件数
	
	@Column
	private String remark;
	
	@Column
	private String country;
	
	@Column(name = "gross_weight_per_box")
	private Float grossWeightPerBox;
	
	@Column(name = "net_weight_per_box")
	private Float netWeightPerBox;
	
	@Column(name = "volume_per_box")
	private Float volumePerBox;
	
	@Column(name = "deleted",columnDefinition = "TINYINT(1)")
	private Boolean deleted = Boolean.FALSE;
}
