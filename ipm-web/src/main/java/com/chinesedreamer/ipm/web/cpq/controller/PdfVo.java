package com.chinesedreamer.ipm.web.cpq.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	public static List<PdfVo> localInstance() {
		List<PdfVo> vos = new ArrayList<PdfVo>();
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			PdfVo vo = new PdfVo();
			vo.setOrder("1504-07.4001" + i);
			vo.setStyle("127154/1504-07.4001" + i);
			vo.setColour( (40 + i) + ""); 
			vo.setSizeS(random.nextInt(100) * i);
			vo.setSizeM(random.nextInt(100) * i);
			vo.setSizeL(random.nextInt(100) * i);
			vo.setSizeXL(random.nextInt(100) * i);
			vo.setSizeXXL(random.nextInt(100) * i);
			vo.setTTL(vo.getSizeS() + vo.getSizeL() + vo.getSizeM() + vo.getSizeXL() + vo.getSizeXXL());
			vo.setTotalAmount(random.nextInt(1000) * i);
			vos.add(vo);
		}
		return vos;
	}
}
