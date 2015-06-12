package com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report;

import java.util.Comparator;

/**
 * Description: 
 * @author Paris
 * @date Jun 9, 20159:58:43 AM
 * @version beta
 */
public class ColorSizeVoComparator implements Comparator<ColorSizeVo>{

	@Override
	public int compare(ColorSizeVo o1, ColorSizeVo o2) {
		return o1.getColor().compareTo(o2.getColor());
	}

}
