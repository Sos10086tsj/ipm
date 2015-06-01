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
	private Integer sizeXL;
	private Integer sizeXXL;
	private Integer tTL;
	private Integer totalAmount;
}
