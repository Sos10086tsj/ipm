package com.chinesedreamer.ipm.web.cpq.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date May 22, 20153:00:31 PM
 * @version beta
 */
public @Getter @Setter class ExcelVo {
	private String order;
	private String style;
	private String from;
	private String to;
	private String colour;
	private Integer sizeS;
	private Integer sizeM;
	private Integer sizeL;
	private Integer sizeXL;
	private Integer sizeXXL;
	private Integer box;
	private Float qty;
	private Float grossWeight;
	private Float netWeight;
	
	public static List<ExcelVo> localInstance() {
		List<ExcelVo> vos = new ArrayList<ExcelVo>();
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			ExcelVo vo = new ExcelVo();
			vo.setOrder("1504-07.4001" + i);
			vo.setStyle("127154/1504-07.4001" + i);
			vo.setColour( (40 + i) + ""); 
			vo.setSizeS(random.nextInt(100) * i);
			vo.setSizeM(random.nextInt(100) * i);
			vo.setSizeL(random.nextInt(100) * i);
			vo.setSizeXL(random.nextInt(100) * i);
			vo.setSizeXXL(random.nextInt(100) * i);
			vos.add(vo);
		}
		return vos;
	}
}
