package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.ManufactoryInfo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.TitleInfo;

/**
 * Description: 打印excel
 * @author Paris
 * @date Jun 10, 20151:46:32 PM
 * @version beta
 */
public interface CpqExcelPrintService {

	/**
	 * 打印厂商标题信息	1~3行
	 * @param workbook
	 * @param sheet
	 * @param info
	 */
	public void printManufactory(HSSFWorkbook workbook, HSSFSheet sheet, ManufactoryInfo info);
	
	/**
	 * 打印表头部分	4~15行
	 * @param workbook
	 * @param sheet
	 * @param info
	 */
	public void printTitle(HSSFWorkbook workbook, HSSFSheet sheet, TitleInfo info);
	
	/**
	 * 打印数据表头部分
	 * @param workbook
	 * @param sheet
	 * @param hasCountry
	 * @param sizes
	 */
	public void printTableTitle(HSSFWorkbook workbook, HSSFSheet sheet, Boolean hasCountry,List<String> sizes);
	
	/**
	 * 逐行打印数据
	 * @param workbook
	 * @param sheet
	 * @param hasCountry
	 * @param sizes
	 * @param items
	 * @return
	 */
	public void printDataRow(HSSFWorkbook workbook, HSSFSheet sheet, Boolean hasCountry,List<String> sizes,List<CpqManufacotryOrderItem> items);
}
