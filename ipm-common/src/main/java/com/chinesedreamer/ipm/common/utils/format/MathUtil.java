package com.chinesedreamer.ipm.common.utils.format;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

/**
 * Description: 
 * @author Paris
 * @date Jun 9, 201510:17:41 AM
 * @version beta
 */
public class MathUtil {
	/**
	 * 计算百分比
	 * @param x
	 * @param y
	 * @return
	 */
	public static String percent(Double x, Double y, String format){
		String percent = "";
		double p = y / x;
		DecimalFormat df;
		if (StringUtils.isEmpty(format)) {
			df = new DecimalFormat("##.00%");
		}else {
			df = new DecimalFormat(format);
		}
		percent = df.format(p);
		if (percent.equals(".00%")) {
			return "0.00%";
		}
		return percent;
	}
	
	public static String percent(Integer x, Integer y, String format){
		String percent = "";
		double p = (y * 1.0) / (x * 1.0);
		DecimalFormat df;
		if (StringUtils.isEmpty(format)) {
			df = new DecimalFormat("##.00%");
		}else {
			df = new DecimalFormat(format);
		}
		percent = df.format(p);
		if (percent.equals(".00%")) {
			return "0.00%";
		}
		return percent;
	}
}
