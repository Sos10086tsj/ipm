package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 8, 20158:40:46 AM
 * @version beta
 */
public @Getter @Setter class ExcelVo {
	private String order;
	private String style;
	private String colour;
	private Integer fromNo;
	private Integer toNo;
	private Integer sizeS;
	private Integer sizeM;
	private Integer sizeL;
	private Integer sizeXl;
	private Integer sizeXxl;
	private Integer sizeP;
	private Integer size1;
	private Integer size2;
	private Integer size3;
	private Integer size4;
	private Integer size6;
	private Integer size8;
	private Integer size10;
	private Integer size12;
	private Integer size14;
	private Integer size16;
	private Integer boxQty;
	private Integer pcs;
	private String remark;
	private Float grossWeight;
	private Float netWeight;
	private Float volume;
	private String country;
}
