package com.chinesedreamer.ipm.common.utils.office;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Description: 
 * @author Paris
 * @date Jun 5, 20153:19:52 PM
 * @version beta
 */
public class ExcelUtil {
	public static Integer getCellIntegerValue(Cell cell){
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double value = cell.getNumericCellValue();
			if (null != value && value > 0.0) {
				return value.intValue();
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			String value = cell.getStringCellValue();
			if (StringUtils.isNotEmpty(value)) {
				return Integer.parseInt(value);
			}
		}
		return null;
	}
	
	public static Float getCellFloatValue(Cell cell){
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double value = cell.getNumericCellValue();
			if (null != value && value > 0.0) {
				return value.floatValue();
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			String value = cell.getStringCellValue();
			if (StringUtils.isNotEmpty(value)) {
				return Float.parseFloat(value);
			}
		}
		return null;
	}
	
	public static String getCellStringValue(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double value = cell.getNumericCellValue();
			if (null != value && value > 0.0) {
				return value.toString();
			}
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			String value = cell.getStringCellValue();
			if (StringUtils.isNotEmpty(value)) {
				return value;
			}
		}
		return null;
	}
}
