package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;

/**
 * Description: 工厂excel信息item
 * @author Paris
 * @date Jun 4, 20159:05:45 AM
 * @version beta
 */
@Entity
@Table(name = "ipm_biz_cpq_manufactory_order_item")
public @Getter @Setter class CpqManufacotryOrderItem extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -433094055233117938L;

	@Column(name = "order_no")
	private String orderNo;//订单号
	
	@Column(name = "order_no_type")
	private String orderNoType;//order no 尾号
	
	@Column(name = "style_no")
	private String styleNo;//款号
	
	@Column(name = "from_no")
	private Integer fromNo;
	
	@Column(name = "to_no")
	private Integer toNo;
	
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
	
	@Column(name = "excel_id")
	private Long excelId;
	
	@Column
	private String owner;

	@Override
	public String toString() {
		return "CpqManufacotryOrderItem [orderNo=" + orderNo + ", styleNo="
				+ styleNo + ", fromNo=" + fromNo + ", toNo=" + toNo
				+ ", color=" + color + ", sizeS=" + sizeS + ", sizeM=" + sizeM
				+ ", sizeL=" + sizeL + ", sizeXl=" + sizeXl + ", sizeXxl="
				+ sizeXxl + ", sizeP=" + sizeP + ", size1=" + size1
				+ ", size2=" + size2 + ", size3=" + size3 + ", size4=" + size4
				+ ", size6=" + size6 + ", size8=" + size8 + ", size10="
				+ size10 + ", size12=" + size12 + ", size14=" + size14
				+ ", size16=" + size16 + ", boxQty=" + boxQty + ", pcsPerBox="
				+ pcsPerBox + ", remark=" + remark + ", country=" + country
				+ ", grossWeightPerBox=" + grossWeightPerBox
				+ ", netWeightPerBox=" + netWeightPerBox + ", volumePerBox="
				+ volumePerBox + ", deleted=" + deleted + "]";
	}
	
	
}
