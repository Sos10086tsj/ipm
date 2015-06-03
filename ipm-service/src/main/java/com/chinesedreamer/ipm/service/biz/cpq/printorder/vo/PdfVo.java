package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date May 21, 20155:13:29 PM
 * @version beta
 */
public @Getter @Setter class PdfVo {
	
	private String order;
	private String style;
	private String colour;
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
	private Integer ttl;
	private Integer totalAmount;
}
