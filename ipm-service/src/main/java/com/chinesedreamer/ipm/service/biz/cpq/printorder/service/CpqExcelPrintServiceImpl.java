package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.format.MathUtil;
import com.chinesedreamer.ipm.common.utils.office.ExcelUtil;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic.CpqDictionaryLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic.CpqOrderItemLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic.CpqOrderLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.ColorSizeVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.ColorSizeVoComparator;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.ManufactoryInfo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.TitleInfo;

/**
 * Description: 
 * @author Paris
 * @date Jun 10, 20151:46:46 PM
 * @version beta
 */
@Service
public class CpqExcelPrintServiceImpl implements CpqExcelPrintService{
	
	private Logger logger = LoggerFactory.getLogger(CpqExcelPrintServiceImpl.class);
	
	@Resource
	private CpqDictionaryLogic cpqDictionaryLogic;
	@Resource
	private CpqOrderItemLogic cpqOrderItemLogic;
	@Resource
	private CpqOrderLogic cpqOrderLogic;
	
	private HSSFCellStyle commonStyle;
	private void initCommonStyle(HSSFWorkbook workbook){
		commonStyle = workbook.createCellStyle();
		commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		commonStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short)10);
		commonStyle.setFont(font);
	}

	@Override
	public void printManufactory(HSSFWorkbook workbook,HSSFSheet sheet, ManufactoryInfo info) {
		//第一行：工厂名
		HSSFRow row1 = sheet.createRow(0); 
		row1.setHeightInPoints(20.25f);
		HSSFCell cell1_1 = row1.createCell(0);
		HSSFCellStyle cellStyle1_1 = workbook.createCellStyle();
		cellStyle1_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font1_1 = workbook.createFont();
		font1_1.setFontName("Times New Roman");
		font1_1.setFontHeightInPoints((short)16);
		font1_1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle1_1.setFont(font1_1);
		cell1_1.setCellStyle(cellStyle1_1);
		cell1_1.setCellType(Cell.CELL_TYPE_STRING);
		cell1_1.setCellValue(info.getName());
		CellRangeAddress region1_1 = new CellRangeAddress(0, 0, 0, 11);
		sheet.addMergedRegion(region1_1);
		
		//第二行：工厂地址一
		HSSFCellStyle cellStyle2_x = workbook.createCellStyle();
		cellStyle2_x.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font2_x = workbook.createFont();
		font2_x.setFontName("Times New Roman");
		font2_x.setFontHeightInPoints((short)9);
		cellStyle2_x.setFont(font2_x);
		
		HSSFRow row2 = sheet.createRow(1);
		row2.setHeightInPoints(15);
		HSSFCell cell2_1 = row2.createCell(0);
		cell2_1.setCellStyle(cellStyle2_x);
		cell2_1.setCellType(Cell.CELL_TYPE_STRING);
		cell2_1.setCellValue(info.getAddress1());
		CellRangeAddress region2_1 = new CellRangeAddress(1, 1, 0, 11);
		sheet.addMergedRegion(region2_1);
		
		//第三行：工厂地址二
		HSSFRow row3 = sheet.createRow(2);
		row3.setHeightInPoints(15);
		HSSFCell cell3_1 = row3.createCell(0);
		cell3_1.setCellStyle(cellStyle2_x);
		cell3_1.setCellType(Cell.CELL_TYPE_STRING);
		cell3_1.setCellValue(info.getAddress2());
		CellRangeAddress region3_1 = new CellRangeAddress(2, 2, 0, 11);
		sheet.addMergedRegion(region3_1);
	}

	
	@Override
	public void printTitle(HSSFWorkbook workbook, HSSFSheet sheet,
			TitleInfo info) {
		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short)10);
		
		HSSFCellStyle left_Top_Border = workbook.createCellStyle();
		left_Top_Border.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		left_Top_Border.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		left_Top_Border.setBorderTop(HSSFCellStyle.BORDER_THIN);
		left_Top_Border.setFont(font);
		
		HSSFCellStyle top_Border = workbook.createCellStyle();
		top_Border.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		top_Border.setBorderTop(HSSFCellStyle.BORDER_THIN);
		top_Border.setFont(font);
		
		HSSFCellStyle right_Top_Border = workbook.createCellStyle();
		right_Top_Border.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		right_Top_Border.setBorderRight(HSSFCellStyle.BORDER_THIN);
		right_Top_Border.setBorderTop(HSSFCellStyle.BORDER_THIN);
		right_Top_Border.setFont(font);
		
		HSSFCellStyle left_Border = workbook.createCellStyle();
		left_Border.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		left_Border.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		left_Border.setFont(font);
		
		HSSFCellStyle right_Border = workbook.createCellStyle();
		right_Border.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		right_Border.setBorderRight(HSSFCellStyle.BORDER_THIN);
		right_Border.setFont(font);
		
		//5.	csutomer name
		HSSFRow row5 = sheet.createRow(4);
		row5.setHeightInPoints(16.5f);;
		HSSFCell cell5_1 = row5.createCell(3);
		cell5_1.setCellStyle(left_Top_Border);
		cell5_1.setCellType(Cell.CELL_TYPE_STRING);
		cell5_1.setCellValue("Customer Name:");
		HSSFCell cell5_2 = row5.createCell(5);
		cell5_2.setCellStyle(top_Border);
		cell5_2.setCellType(Cell.CELL_TYPE_STRING);
		cell5_2.setCellValue(info.getCustomerName());
		for (int i = 0; i < 10; i++) {
			if (i == 4 || i > 5) {
				row5.createCell(i).setCellStyle(top_Border);
			}
		}
		row5.createCell(9).setCellStyle(right_Top_Border);
		
		//6~8	address
		this.printTitleNormalRow(5, "Address:", info.getAddress1(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(6, "", info.getAddress2(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(7, "", info.getAddress3(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(8, "Style No.:", info.getStyleNo(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(9, "Order No.:", info.getOrderNo(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(10, "Description:", info.getDescription(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(11, "Invoice ref.No:", info.getInvoceNo(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(12, "Manufacturer", info.getManufactorurer(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(13, "Exporter:", info.getExporter(), sheet, left_Border, right_Border);
		this.printTitleNormalRow(14, "L/C No:", info.getLcNo(), sheet, left_Border, right_Border);
	}

	/**
	 * 打印普通的title部分
	 * @param rowNum
	 * @param label
	 * @param value
	 * @param sheet
	 * @param cellStyle
	 */
	private void printTitleNormalRow(int rowNum, String label, String value, HSSFSheet sheet, HSSFCellStyle labelStyle, HSSFCellStyle endlStyle){
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeightInPoints(16.5f);
		HSSFCell cell_1 = row.createCell(3);
		cell_1.setCellStyle(labelStyle);
		cell_1.setCellType(Cell.CELL_TYPE_STRING);
		cell_1.setCellValue(label);
		
		HSSFCell cell_2 = row.createCell(5);
		cell_2.setCellType(Cell.CELL_TYPE_STRING);
		cell_2.setCellValue(value);
		
		row.createCell(9).setCellStyle(endlStyle);
		
	}


	
	@Override
	public void printTableTitle(HSSFWorkbook workbook, HSSFSheet sheet,Boolean hasCountry,List<String> sizes) {
		HSSFRow row16 = sheet.createRow(15);
		row16.setHeightInPoints(22);
		this.initCommonStyle(workbook);
		this.printNormalCell(row16, 0, "Box Number", commonStyle);
		row16.createCell(1).setCellStyle(commonStyle);
		sheet.addMergedRegion(new CellRangeAddress(15, 15, 0, 1));
		this.printNormalCell(row16, 2, "Style", commonStyle);
		int countryIndex = 0;
		if (hasCountry) {
			countryIndex = 1;
			row16.createCell(3).setCellStyle(commonStyle);
		}
		this.printNormalCell(row16, 3 + countryIndex, "Colour", commonStyle);
		this.printNormalCell(row16, 4 + countryIndex, "Sizes", commonStyle);
		for (int i = 5 + countryIndex; i < 4 + countryIndex + sizes.size(); i++) {
			row16.createCell(i).setCellStyle(commonStyle);
		}
		sheet.addMergedRegion(new CellRangeAddress(15, 15, 4 + countryIndex, 3 + countryIndex + sizes.size()));
		this.printNormalCell(row16, 4 + countryIndex + sizes.size(), "Artikel", commonStyle);
		this.printNormalCell(row16, 5 + countryIndex + sizes.size(), "Box", commonStyle);
		this.printNormalCell(row16, 6 + countryIndex + sizes.size(), "Tot Art.", commonStyle);
		this.printNormalCell(row16, 7 + countryIndex + sizes.size(), "Remark", commonStyle);
		
		HSSFRow row17 = sheet.createRow(16);
		row17.setHeightInPoints(22);
		this.printNormalCell(row17, 0, "From", commonStyle);
		this.printNormalCell(row17, 1, "To", commonStyle);
		this.printNormalCell(row17, 2, "Number", commonStyle);
		if (hasCountry) {
			this.printNormalCell(row17, 3 , "Country", commonStyle);
		}
		this.printNormalCell(row17, 3 + countryIndex, "name", commonStyle);
		for (int i = 0; i < sizes.size(); i++) {
			String sizeX = sizes.get(i);
			String sizeXValue = sizeX.substring("Size".length(), sizeX.length()).toUpperCase();
			this.printNormalCell(row17, 4 + countryIndex + i, sizeXValue, commonStyle);
		}
		this.printNormalCell(row17, 4 + countryIndex + sizes.size(), "per box", commonStyle);
		//17.7
		this.printNormalCell(row17, 5 + countryIndex + sizes.size(), "Qty", commonStyle);
		//17.8
		this.printNormalCell(row17, 6 + countryIndex + sizes.size(), "Qty", commonStyle);
		row17.createCell(7 + countryIndex + sizes.size()).setCellStyle(commonStyle);
		sheet.addMergedRegion(new CellRangeAddress(15, 16, 7 + countryIndex + sizes.size(), 7 + countryIndex + sizes.size()));

//		row16.getCell(7 + countryIndex + sizes.size()).setCellStyle(commonStyle);
//		row17.getCell(7 + countryIndex + sizes.size()).setCellStyle(commonStyle);
	}
	
	/**
	 * 打印普通cell
	 * @param row
	 * @param columnNum
	 * @param value
	 * @param cellStyle
	 */
	private void printNormalCell(HSSFRow row, int columnNum, Object value, HSSFCellStyle cellStyle){
		HSSFCell cell = row.createCell(columnNum);
		cell.setCellStyle(cellStyle);
		if (value instanceof Integer) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Integer)value);
		}else if (value instanceof String) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue((String)value);
		}
	}
	
	/**
	 * 生成加法公式
	 * @param items
	 * @return
	 */
	private String generatePlusFormula(List<String> items) {
		StringBuffer buffer = new StringBuffer();
		for (String item : items) {
			buffer.append(item)
			.append("+");
		}
		String rst = buffer.toString();
		if (rst.endsWith("+")) {
			rst = rst.substring(0, rst.length() -1 );
		}
		return rst ;
	}
	
	/**
	 * 生成求和公式
	 * @param items
	 * @return
	 */
	private String generateSumFormula(List<String> items) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SUM(");
		for (String item : items) {
			buffer.append(item)
			.append(":");
		}
		String rst = buffer.toString();
		if (rst.endsWith(":")) {
			rst = rst.substring(0, rst.length() -1 );
		}
		return rst + ")";
	}
	
	/**
	 * 打印公式
	 * @param row
	 * @param columnNum
	 * @param formula
	 * @param cellStyle
	 */
	private void printFormulaCell(HSSFRow row, int columnNum, String formula, HSSFCellStyle cellStyle){
		HSSFCell cell = row.getCell(columnNum);
		if (null == cell) {
			cell = row.createCell(columnNum, Cell.CELL_TYPE_FORMULA);
		}
		cell.setCellStyle(cellStyle);
		cell.setCellType(Cell.CELL_TYPE_FORMULA);
		cell.setCellFormula(formula);
	}

	@Override
	public void printDataRow(HSSFWorkbook workbook,
			HSSFSheet sheet, Boolean hasCountry, List<String> sizes,
			List<CpqManufacotryOrderItem> items) {
		Map<String, ColorSizeVo> colorSizeMap = new HashMap<String, ColorSizeVo>();
		this.initCommonStyle(workbook);
		
		int mergeRowStart = -1;
		int mergeRowEnd = -1;
		int countryIndex = 0;
		if (hasCountry) {
			countryIndex = 1;
		}

		List<String> totalBoxFormula = new ArrayList<String>();//总和公式

		List<String> totalQtyFormula = new ArrayList<String>();
		float totalGrossWeight = 0;
		float totalNetWeight = 0;
		float totalVolume = 0;
		Set<String> poReadedColors = new HashSet<String>();
		
		for (int i = 0; i < items.size(); i++) {
			if (-1 != mergeRowStart && -1 != mergeRowEnd) {//已经知道合并行数
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 0, 0));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 1, 1));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 4 + countryIndex + sizes.size(), 4  + countryIndex + sizes.size()));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 5 + countryIndex + sizes.size(), 5  + countryIndex + sizes.size()));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 6 + countryIndex + sizes.size(), 6  + countryIndex + sizes.size()));
				mergeRowStart = -1;
				mergeRowEnd = -1;
			}
			
			CpqManufacotryOrderItem item = items.get(i);
			
			HSSFRow itemRow = sheet.createRow(17 + i);
			itemRow.setHeightInPoints(16.5f);
			this.printNormalCell(itemRow, 0, item.getFromNo() , commonStyle);
			this.printNormalCell(itemRow, 1, item.getToNo() , commonStyle);
			this.printNormalCell(itemRow, 2, item.getStyleNo() , commonStyle);
			if (hasCountry) {
				List<CpqDictionary> countries = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.ORDER_COUNTRY, item.getOrderNoType());
				String countryCellValue = "";
				if (!countries.isEmpty()) {
					countryCellValue = countries.get(0).getValue();
				}
				this.printNormalCell(itemRow, 2 + countryIndex, countryCellValue , commonStyle);
			}
			this.printNormalCell(itemRow, 3 + countryIndex, item.getColor() , commonStyle);
			if (!colorSizeMap.keySet().contains(item.getColor())) {
				ColorSizeVo vo = new ColorSizeVo();
				vo.setColor(item.getColor());
				colorSizeMap.put(item.getColor(), vo);
			}
			//打印size 部分
			for (int j = 0; j < sizes.size(); j++) {
				String size = sizes.get(j);
				try {
					Method getMethod = CpqManufacotryOrderItem.class.getDeclaredMethod("get"+size);
					Integer value = (Integer)getMethod.invoke(item);
					if (null != value) {
//						ColorSizeVo vo = colorSizeMap.get(item.getColor());
//						Method mapGetMethod = ColorSizeVo.class.getDeclaredMethod("get"+size);
//						Method mapSetMethod = ColorSizeVo.class.getDeclaredMethod("set"+size, Integer.class);
//						Method mapShippedSetMethod = ColorSizeVo.class.getDeclaredMethod("setActual"+size, Integer.class);
//						Integer mapValue = value + (Integer)mapGetMethod.invoke(vo);
//						mapSetMethod.invoke(vo, mapValue);
//						mapShippedSetMethod.invoke(vo, mapValue);
//						colorSizeMap.put(item.getColor(), vo);
						this.printNormalCell(itemRow, 4 + countryIndex + j, value , commonStyle);
					}else{
						itemRow.createCell(4 + countryIndex + j).setCellStyle(commonStyle);
					}
					//颜色order qty、shipped qty都从po里读取
					if (!poReadedColors.contains( item.getColor() + "@" + size)) {
						CpqOrder poOrder = this.cpqOrderLogic.findByOrderNoAndStyleNo(item.getOrderNo(), item.getStyleNo());
						if(null != poOrder){
							CpqOrderItem poItem = this.cpqOrderItemLogic.findByOrderIdAndColor(poOrder.getId(), item.getColor());
							if (null != poItem) {
								Method poGtMethod = CpqOrderItem.class.getDeclaredMethod("get"+size);
								Integer poValue = (Integer)poGtMethod.invoke(poItem);
								if(null != poValue){
									ColorSizeVo vo = colorSizeMap.get(item.getColor());
									Method mapGetMethod = ColorSizeVo.class.getDeclaredMethod("get"+size);
									Method mapSetMethod = ColorSizeVo.class.getDeclaredMethod("set"+size, Integer.class);
									Method mapShippedSetMethod = ColorSizeVo.class.getDeclaredMethod("setActual"+size, Integer.class);
									Integer mapValue = poValue + (Integer)mapGetMethod.invoke(vo);
									mapSetMethod.invoke(vo, mapValue);
									mapShippedSetMethod.invoke(vo, mapValue);
									colorSizeMap.put(item.getColor(), vo);
								}
							}
						}
						poReadedColors.add(item.getColor() + "@" + size);
					}
				}catch (Exception e) {
					this.logger.error("{}",e);
				}
			}
			if(mergeRowStart == -1){//只有非合并的单元格才赋值，否则会影响sum总数
				this.printNormalCell(itemRow, 4 + countryIndex + sizes.size(), item.getPcsPerBox() , commonStyle);
				this.printNormalCell(itemRow, 5 + countryIndex + sizes.size(), item.getBoxQty() , commonStyle);
				this.printNormalCell(itemRow, 6 + countryIndex + sizes.size(), item.getPcsPerBox() * item.getBoxQty() , commonStyle);
			}else {
				itemRow.createCell(4 + countryIndex + sizes.size()).setCellStyle(commonStyle);
				itemRow.createCell(5 + countryIndex + sizes.size()).setCellStyle(commonStyle);
				itemRow.createCell(6 + countryIndex + sizes.size()).setCellStyle(commonStyle);
			}
			totalBoxFormula.add(ExcelUtil.getColumnCharacter(5 + countryIndex + sizes.size()) + (18 + i));
			totalQtyFormula.add(ExcelUtil.getColumnCharacter(6 + countryIndex + sizes.size()) + (18 + i));
			//remark 是order的结尾
			this.printNormalCell(itemRow, 7 + countryIndex + sizes.size(), "/" + item.getOrderNoType() , commonStyle);
			totalGrossWeight += item.getGrossWeightPerBox() * item.getBoxQty();
			totalNetWeight += item.getNetWeightPerBox() * item.getBoxQty();
			totalVolume += item.getVolumePerBox() * item.getBoxQty();
			
			if (i < items.size() - 1) {
				CpqManufacotryOrderItem nextItem = items.get(i+1);
				if (nextItem.getFromNo().equals(item.getFromNo())) {//from 相同
					if (mergeRowStart == - 1) {
						mergeRowStart = 17 + i;
					}
				}else {
					if (mergeRowStart != - 1) {
						mergeRowEnd = 17 + i;
					}
				}
			}else if (i == items.size() - 1 && mergeRowStart != -1) {
				mergeRowEnd = 17 + i;
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 0, 0));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 1, 1));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 4 + countryIndex + sizes.size(), 4  + countryIndex + sizes.size()));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 5 + countryIndex + sizes.size(), 5  + countryIndex + sizes.size()));
				sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 6 + countryIndex + sizes.size(), 6  + countryIndex + sizes.size()));
				mergeRowStart = -1;
				mergeRowEnd = -1;
			}
		}
		//打印5行空白格子，第3行补充 total
		for (int j = 17 + items.size(); j <= 21 + items.size(); j++) {
			HSSFRow blankRow = sheet.createRow(j);
			blankRow.setHeightInPoints(16.5f);
			for (int k = 0; k <= 7 + countryIndex + sizes.size(); k++) {
				blankRow.createCell(k).setCellStyle(commonStyle);
			}
		}
		sheet.getRow(19 + items.size()).getCell(4 + countryIndex + sizes.size()).setCellValue("TOTAL");
		//sheet.getRow(19 + items.size()).getCell(5 + countryIndex + sizes.size()).setCellValue(totalBox);
		//sheet.getRow(19 + items.size()).getCell(6 + countryIndex + sizes.size()).setCellValue(totalQty);
		this.printFormulaCell(sheet.getRow(19 + items.size()), 5 + countryIndex + sizes.size(), this.generateSumFormula(totalBoxFormula), commonStyle);
		this.printFormulaCell(sheet.getRow(19 + items.size()), 6 + countryIndex + sizes.size(), this.generateSumFormula(totalQtyFormula), commonStyle);
		
		//COLOUR  BREAKDOWN
		HSSFRow colorRow = sheet.createRow(22 + items.size());
		colorRow.setHeightInPoints(16.5f);
		for (int j = 0; j <= 1; j++) {
			colorRow.createCell(j).setCellStyle(commonStyle);
		}
		this.printNormalCell(colorRow, 2, "COLOUR  BREAKDOWN" , commonStyle);
		for (int j = 3; j <= 3 + countryIndex; j++) {
			colorRow.createCell(j).setCellStyle(commonStyle);
		}
		for (int j = 0; j < sizes.size(); j++) {
			String size = sizes.get(j);
			String sizeXValue = size.substring("Size".length(), size.length()).toUpperCase();
			this.printNormalCell(colorRow, 4 + countryIndex + j, sizeXValue, commonStyle);
		}
		for (int j = 4 + countryIndex + sizes.size(); j <= 7 + countryIndex + sizes.size(); j++) {
			colorRow.createCell(j).setCellStyle(commonStyle);
		}
		
		//打印color部分
		List<String> colorOrderedTotalFormual = new ArrayList<String>();
		List<String> colorShippedTotalFormual = new ArrayList<String>();
		List<String> colorDiscrepancyTotalFormual = new ArrayList<String>();
		List<ColorSizeVo> vos = this.getColorSizes(colorSizeMap);
		for (int i = 0; i < vos.size(); i++) {
			ColorSizeVo vo = vos.get(i);
			List<String> tmpOrderedTotalFormual = new ArrayList<String>();
			List<String> tmpShippedTotalFormual = new ArrayList<String>();
			
			HSSFRow blankRow = sheet.createRow(23 + i * 5 + items.size());
			blankRow.setHeightInPoints(16.5f);
			for (int k = 0; k <= 7 + countryIndex + sizes.size(); k++) {
				blankRow.createCell(k).setCellStyle(commonStyle);
			}
			
			HSSFRow colorOrderRow = sheet.createRow(24 + i * 5 + items.size());
			colorOrderRow.setHeightInPoints(16.5f);
			HSSFRow shippedOrderRow = sheet.createRow(25 + i * 5 + items.size());
			shippedOrderRow.setHeightInPoints(16.5f);
			HSSFRow discrepancyOrderRow = sheet.createRow(26 + i * 5 + items.size());
			discrepancyOrderRow.setHeightInPoints(16.5f);
			HSSFRow percentOrderRow = sheet.createRow(27 + i * 5 + items.size());
			percentOrderRow.setHeightInPoints(16.5f);
			colorOrderRow.createCell(0).setCellStyle(commonStyle);
			colorOrderRow.createCell(1).setCellStyle(commonStyle);
			shippedOrderRow.createCell(0).setCellStyle(commonStyle);
			shippedOrderRow.createCell(1).setCellStyle(commonStyle);
			discrepancyOrderRow.createCell(0).setCellStyle(commonStyle);
			discrepancyOrderRow.createCell(1).setCellStyle(commonStyle);
			discrepancyOrderRow.createCell(2).setCellStyle(commonStyle);
			percentOrderRow.createCell(0).setCellStyle(commonStyle);
			percentOrderRow.createCell(1).setCellStyle(commonStyle);
			percentOrderRow.createCell(2).setCellStyle(commonStyle);
			
			this.printNormalCell(colorOrderRow, 2 , "ORDER QTY" , commonStyle);
			this.printNormalCell(shippedOrderRow, 2 , "SHIPPED QTY" , commonStyle);
			this.printNormalCell(discrepancyOrderRow, 2 , "DISCREPANCY" , commonStyle);
			this.printNormalCell(percentOrderRow, 2 , "%" , commonStyle);
			if (hasCountry) {
				colorOrderRow.createCell(2 + countryIndex).setCellStyle(commonStyle);
				shippedOrderRow.createCell(2 + countryIndex).setCellStyle(commonStyle);
				discrepancyOrderRow.createCell(2 + countryIndex).setCellStyle(commonStyle);
				percentOrderRow.createCell(2 + countryIndex).setCellStyle(commonStyle);
			}
			this.printNormalCell(colorOrderRow, 3 + countryIndex , vo.getColor() , commonStyle);
			this.printNormalCell(shippedOrderRow, 3 + countryIndex , vo.getColor() , commonStyle);
			discrepancyOrderRow.createCell(3 + countryIndex).setCellStyle(commonStyle);
			percentOrderRow.createCell(3 + countryIndex).setCellStyle(commonStyle);
			
			for (int j = 0; j < sizes.size(); j++) {
				String size = sizes.get(j);
				try {
					Method orderedMethod = ColorSizeVo.class.getDeclaredMethod("get"+size);
					Integer orderedValue = (Integer)orderedMethod.invoke(vo);
					Method actualMethod = ColorSizeVo.class.getDeclaredMethod("getActual"+size);
					Integer actualValue = (Integer)actualMethod.invoke(vo);
					this.printNormalCell(colorOrderRow, 4 + countryIndex + j, orderedValue , commonStyle);
					this.printNormalCell(shippedOrderRow, 4 + countryIndex + j, actualValue , commonStyle);
					this.printNormalCell(discrepancyOrderRow, 4 + countryIndex + j, actualValue - orderedValue , commonStyle);
					this.printNormalCell(percentOrderRow, 4  + countryIndex + j, MathUtil.percent(orderedValue, actualValue - orderedValue, null) , commonStyle);
				}catch (Exception e) {
					this.logger.error("{}",e);
				}
				tmpOrderedTotalFormual.add(ExcelUtil.getColumnCharacter(4 + countryIndex + j) + (25 + i * 5 + items.size()));
				tmpShippedTotalFormual.add(ExcelUtil.getColumnCharacter(4 + countryIndex + j) + (26 + i * 5 + items.size()));
			}
			
			this.printFormulaCell(colorOrderRow, 4 + countryIndex + sizes.size(), this.generateSumFormula(tmpOrderedTotalFormual), commonStyle);
			this.printFormulaCell(shippedOrderRow, 4 + countryIndex + sizes.size(), this.generateSumFormula(tmpShippedTotalFormual), commonStyle);
			this.printFormulaCell(discrepancyOrderRow, 4 + countryIndex + sizes.size(), 
					 ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (26 + i * 5 + items.size()) + 
					"-" + ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (25 + i * 5 + items.size()), 
					commonStyle);
			this.printFormulaCell(percentOrderRow, 4 + countryIndex + sizes.size(), 
					 ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (27 + i * 5 + items.size()) + 
					"/" + ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (25 + i * 5 + items.size()), 
					commonStyle);

			for (int j = 5  + countryIndex + sizes.size(); j <=  7 + countryIndex + sizes.size(); j++) {
				colorRow.createCell(j).setCellStyle(commonStyle);
				colorOrderRow.createCell(j).setCellStyle(commonStyle);
				shippedOrderRow.createCell(j).setCellStyle(commonStyle);
				discrepancyOrderRow.createCell(j).setCellStyle(commonStyle);
				percentOrderRow.createCell(j).setCellStyle(commonStyle);
			}
			
			colorOrderedTotalFormual.add(ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (25 + i * 5 + items.size()));
			colorShippedTotalFormual.add(ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (26 + i * 5 + items.size()));
			colorDiscrepancyTotalFormual.add(ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (27 + i * 5 + items.size()));
		}
		//空4行
		for (int j = 23 + vos.size() * 5 + items.size(); j < 31 + vos.size() * 5 + items.size(); j++) {
			HSSFRow blankRow = sheet.createRow(j);
			for (int k = 0; k <= 7 + countryIndex + sizes.size(); k++) {
				blankRow.createCell(k).setCellStyle(commonStyle);
			}
		}
		HSSFRow totalOrderdRow = sheet.getRow(27 + vos.size() * 5 + items.size());
		totalOrderdRow.getCell(2).setCellValue("TOTAL ORDER QTY");
		this.printFormulaCell(totalOrderdRow, 4 + countryIndex + sizes.size(), this.generatePlusFormula(colorOrderedTotalFormual), commonStyle);
		HSSFRow totalShippedRow = sheet.getRow(28 + vos.size() * 5 + items.size());
		totalShippedRow.getCell(2).setCellValue("TOTAL SHIPPED QTY");
		this.printFormulaCell(totalShippedRow, 4 + countryIndex + sizes.size(), this.generatePlusFormula(colorShippedTotalFormual), commonStyle);
		HSSFRow totalDiscrepancyRow = sheet.getRow(29 + vos.size() * 5 + items.size());
		totalDiscrepancyRow.getCell(2).setCellValue("DISCREPANCY");
		this.printFormulaCell(totalDiscrepancyRow, 4 + countryIndex + sizes.size(), this.generatePlusFormula(colorDiscrepancyTotalFormual), commonStyle);
		HSSFRow totalPercentRow = sheet.getRow(30 + vos.size() * 5 + items.size());
		totalPercentRow.getCell(2).setCellValue("%");
		this.printFormulaCell(totalPercentRow, 4 + countryIndex + sizes.size(), 
				 ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (30 + vos.size() * 5 + items.size()) + 
				"/" + ExcelUtil.getColumnCharacter(4 + countryIndex + sizes.size()) + (28 + vos.size() * 5 + items.size()), 
				commonStyle);
		
		//空一行
		for (int i = 31 + vos.size() * 5 + items.size(); i <= 37 + vos.size() * 5 + items.size(); i++) {
			HSSFRow blankRow = sheet.createRow(i);
			for (int k = 0; k <= 7 + countryIndex + sizes.size(); k++) {
				blankRow.createCell(k).setCellStyle(commonStyle);
			}
		}
		//carton
		sheet.getRow(32 + vos.size() * 5 + items.size()).getCell(0).setCellValue("Carton:");
		//sheet.getRow(32 + vos.size() * 5 + items.size()).getCell(1).setCellValue(totalBox);
		this.printFormulaCell(sheet.getRow(32 + vos.size() * 5 + items.size()), 1,  ExcelUtil.getColumnCharacter(5 + countryIndex + sizes.size()) + (20 + items.size()), commonStyle);
		sheet.getRow(32 + vos.size() * 5 + items.size()).getCell(2).setCellValue("CTNS");
		//qty
		sheet.getRow(33 + vos.size() * 5 + items.size()).getCell(0).setCellValue("Qty:");
		//sheet.getRow(33 + vos.size() * 5 + items.size()).getCell(1).setCellValue(totalQty);
		this.printFormulaCell(sheet.getRow(33 + vos.size() * 5 + items.size()), 1,  ExcelUtil.getColumnCharacter(6 + countryIndex + sizes.size()) + (20 + items.size()), commonStyle);
		sheet.getRow(33 + vos.size() * 5 + items.size()).getCell(2).setCellValue("PCS");
		//grossweight
		sheet.getRow(34 + vos.size() * 5 + items.size()).getCell(0).setCellValue("G.W:");
		sheet.getRow(34 + vos.size() * 5 + items.size()).getCell(1).setCellValue(totalGrossWeight);
		sheet.getRow(34 + vos.size() * 5 + items.size()).getCell(2).setCellValue("KGS");
		//netweight
		sheet.getRow(35 + vos.size() * 5 + items.size()).getCell(0).setCellValue("N.W:");
		sheet.getRow(35 + vos.size() * 5 + items.size()).getCell(1).setCellValue(totalNetWeight);
		sheet.getRow(35 + vos.size() * 5 + items.size()).getCell(2).setCellValue("KGS");
		//Meas
		sheet.getRow(36 + vos.size() * 5 + items.size()).getCell(0).setCellValue("Meas:");
		sheet.getRow(36 + vos.size() * 5 + items.size()).getCell(1).setCellValue(totalVolume);
		sheet.getRow(36 + vos.size() * 5 + items.size()).getCell(2).setCellValue("CBM");
	}
	
	/**
	 * 颜色重排序
	 * @param colorSizeMap
	 * @return
	 */
	private List<ColorSizeVo> getColorSizes(Map<String, ColorSizeVo> colorSizeMap) {
		List<ColorSizeVo> vos = new ArrayList<ColorSizeVo>();
		for (String key : colorSizeMap.keySet()) {
			vos.add(colorSizeMap.get(key));
		}
		Collections.sort(vos, new ColorSizeVoComparator());
		return vos;
	}
}
