package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;

/**
 * Description: 工厂excel信息item
 * @author Paris
 * @date Jun 4, 20159:05:45 AM
 * @version beta
 */
@Entity
@Table(name = "ipm_biz_cpq_manufactory_order_item")
public class CpqManufacotryOrderItem extends IpmVersionEntity<Long>{

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
		return "CpqManufacotryOrderItem [orderNo=" + orderNo + ", orderNoType=" + orderNoType + ", styleNo=" + styleNo
				+ ", fromNo=" + fromNo + ", toNo=" + toNo + ", color=" + color + ", sizeS=" + sizeS + ", sizeM=" + sizeM
				+ ", sizeL=" + sizeL + ", sizeXl=" + sizeXl + ", sizeXxl=" + sizeXxl + ", sizeP=" + sizeP + ", size1="
				+ size1 + ", size2=" + size2 + ", size3=" + size3 + ", size4=" + size4 + ", size6=" + size6 + ", size8="
				+ size8 + ", size10=" + size10 + ", size12=" + size12 + ", size14=" + size14 + ", size16=" + size16
				+ ", sizeUNI1=" + sizeUNI1 + ", sizeUNI2=" + sizeUNI2 + ", sizeUNI3=" + sizeUNI3 + ", sizeUNI4="
				+ sizeUNI4 + ", sizeUNI5=" + sizeUNI5 + ", sizeUNI6=" + sizeUNI6 + ", boxQty=" + boxQty + ", pcsPerBox="
				+ pcsPerBox + ", remark=" + remark + ", country=" + country + ", grossWeightPerBox=" + grossWeightPerBox
				+ ", netWeightPerBox=" + netWeightPerBox + ", volumePerBox=" + volumePerBox + ", deleted=" + deleted
				+ ", excelId=" + excelId + ", owner=" + owner + "]";
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

	public Integer getFromNo() {
		return fromNo;
	}

	public Integer getToNo() {
		return toNo;
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

	public Integer getBoxQty() {
		return boxQty;
	}

	public Integer getPcsPerBox() {
		return pcsPerBox;
	}

	public String getRemark() {
		return remark;
	}

	public String getCountry() {
		return country;
	}

	public Float getGrossWeightPerBox() {
		return grossWeightPerBox;
	}

	public Float getNetWeightPerBox() {
		return netWeightPerBox;
	}

	public Float getVolumePerBox() {
		return volumePerBox;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Long getExcelId() {
		return excelId;
	}

	public String getOwner() {
		return owner;
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

	public void setFromNo(Integer fromNo) {
		this.fromNo = fromNo;
	}

	public void setToNo(Integer toNo) {
		this.toNo = toNo;
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

	public void setBoxQty(Integer boxQty) {
		this.boxQty = boxQty;
	}

	public void setPcsPerBox(Integer pcsPerBox) {
		this.pcsPerBox = pcsPerBox;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setGrossWeightPerBox(Float grossWeightPerBox) {
		this.grossWeightPerBox = grossWeightPerBox;
	}

	public void setNetWeightPerBox(Float netWeightPerBox) {
		this.netWeightPerBox = netWeightPerBox;
	}

	public void setVolumePerBox(Float volumePerBox) {
		this.volumePerBox = volumePerBox;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setExcelId(Long excelId) {
		this.excelId = excelId;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
