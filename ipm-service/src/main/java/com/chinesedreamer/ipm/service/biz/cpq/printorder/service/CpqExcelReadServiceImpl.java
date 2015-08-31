package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.office.ExcelUtil;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic.CpqDictionaryLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.logic.CpqManufacotryOrderItemLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;

/**
 * Description: 
 * @author Paris
 * @date Jun 19, 20159:50:18 AM
 * @version beta
 */
@Service
public class CpqExcelReadServiceImpl implements CpqExcelReadService{
	private Logger logger = LoggerFactory.getLogger(CpqExcelReadServiceImpl.class);
	
	@Resource
	private CpqDictionaryLogic cpqDictionaryLogic;
	@Resource
	private CpqManufacotryOrderItemLogic cpqManufacotryOrderItemLogic;

	@Override
	public Set<CpqManufacotryOrderItem> readJianan01ExcelSheet(CpqFile cpqFile,
			Sheet... sheets) {	
		Set<CpqManufacotryOrderItem> items = new HashSet<>();
		for (Sheet sheet : sheets) {
			if (null != sheet) {
				int rows = sheet.getLastRowNum();
				if (rows < 1) {
					this.logger.info("sheet:{} is empty.",sheet.getSheetName());
				}
				this.logger.info("sheet name:{}", sheet.getSheetName());
				int startRow = 0;//开始的行
				int endRow = 0;//结束行
				
				//tmp 变量都是解决合并单元格问题的
				Integer tmpFrom = -1;
				Integer tmpTo = -1;
				String tmpColor = "";
				Integer tmpBoxQty = 0;
				Float tmpGrossWeight = 0f;
				Float tmpNetWeight = 0f;
				Float tmpVolume = 0f;
				
				List<Integer> availableRows = this.getStartAndEndRow(sheet);
				for (int i = 0; i < availableRows.size() -1; i = i +2) {
					startRow = availableRows.get(i);
					endRow = availableRows.get(i+1);
					int currentRow = startRow;//第一行：合约号
					//第一行：订单号、款号
					Row orderRow = sheet.getRow(currentRow);
					String orderNo = ExcelUtil.getCellStringValue(orderRow.getCell(2));//订单号
					
					
					//获取不同类型的size列表
					List<CpqDictionary> sizeDicts = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.CLOTHING_CATEGORY_SIZE, cpqFile.getClothingType().toString());
					int sizes = sizeDicts.size();
					
					String styleNo = ExcelUtil.getCellStringValue(orderRow.getCell(2 + sizes + 5));//款号
					
					currentRow = this.getBoxTitleRow(sheet, currentRow);
					if (-1 == currentRow) {//说明没有找到箱号的单元格
						break;
					}
					
					currentRow += 2;//第一个from-to的行
					for (int j = currentRow; j < endRow; j++) {
						Row itemRow = sheet.getRow(j);
						if (null == itemRow || itemRow.getZeroHeight()) {//有部分隐藏的行
							continue;
						}
						//读取from和to
						String from = ExcelUtil.getCellStringValue(itemRow.getCell(0));
						if (StringUtils.isEmpty(from)) {
							continue;//from 为空
						}
						if (StringUtils.isNotEmpty(from)) {
							int index = from.indexOf("-");
							if (index == -1) {
								tmpFrom = Integer.parseInt(from);
								tmpTo = tmpFrom;
							}else {
								tmpFrom = Integer.parseInt(from.substring(0, index));
								if (index == from.length() - 1) {
									tmpTo = tmpFrom;
								}else {
									tmpTo = Integer.parseInt(from.substring(index + 1, from.length()));
								}
							}
						}
						//读取color
						String colorCellValue = ExcelUtil.getCellStringValue(itemRow.getCell(2));
						if (StringUtils.isNotEmpty(colorCellValue)) {
							tmpColor = this.formatColor(colorCellValue);
						}
						//拼装item
						CpqManufacotryOrderItem item = this.cpqManufacotryOrderItemLogic.findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(orderNo, styleNo, tmpColor, tmpFrom, tmpTo, cpqFile.getOwner());
						if (null == item) {
							item = new CpqManufacotryOrderItem();
						}
						item.setOrderNo(orderNo);
						item.setOrderNoType(this.getOrderType(orderNo));
						item.setStyleNo(styleNo);
						item.setFromNo(tmpFrom);
						item.setToNo(tmpTo);
						item.setColor(tmpColor);
						item.setOwner(cpqFile.getOwner());
						
						//箱数
						Integer qualityCellValue = ExcelUtil.getCellIntegerValue(itemRow.getCell(1));
						if (null != qualityCellValue) {
							tmpBoxQty = qualityCellValue;
						}
						item.setBoxQty(tmpBoxQty);
						
						//size
						for (int m = 0 ; m < sizes ; m++) {
							CpqDictionary sizeDict = sizeDicts.get(m);
							String sizeParam = this.formatSizeKey(sizeDict.getValue());
							Integer sizeCellValue = ExcelUtil.getCellIntegerValue(itemRow.getCell(3 + m));
							if (null != sizeCellValue) {
								Integer sizeValue = sizeCellValue;
								try {
									Method setMethod = CpqManufacotryOrderItem.class.getDeclaredMethod("set"+sizeParam, Integer.class);
									setMethod.invoke(item, sizeValue);
								} catch (Exception e) {
									this.logger.error("{}",e);
								} 
							}
						}
						//每箱件数
						Float pcs = ExcelUtil.getCellFloatValue(itemRow.getCell(3 + sizes));
						if (null != pcs) {
							item.setPcsPerBox(pcs.intValue());
						}
						//毛重
						Float grossWeightCellValue = ExcelUtil.getCellFloatValue(itemRow.getCell(5 + sizes));
						if (null != grossWeightCellValue) {
							tmpGrossWeight =  grossWeightCellValue;
						}
						item.setGrossWeightPerBox(tmpGrossWeight);
						//净重
						Float netWeightCellValue = ExcelUtil.getCellFloatValue(itemRow.getCell(6 + sizes));
						if (null != netWeightCellValue) {
							tmpNetWeight = netWeightCellValue;
						}
						item.setNetWeightPerBox(tmpNetWeight);
						//体积
						String volumeCellValue = ExcelUtil.getCellStringValue(itemRow.getCell(7 + sizes));
						if (StringUtils.isNotEmpty(volumeCellValue)) {
							tmpVolume = this.formatVolume(volumeCellValue);
						}
						item.setVolumePerBox(tmpVolume);
						
						item.setExcelId(cpqFile.getId());
						items.add(item);
					}
				}
			}
		}
		return items;
	}

	/**
	 * 获取开始和结束的行
	 * @param sheet
	 * @return
	 */
	private List<Integer> getStartAndEndRow(Sheet sheet){
		List<Integer> availableRows = new ArrayList<Integer>();
		String beginSymble = "合约号";
		String endSymble = "合计";
		int rows = sheet.getLastRowNum();
		for (int i = 0; i < rows; i++) {
			Row row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			Cell cell = row.getCell(0);
			if (null == cell) {
				continue;
			}
			String cellValue = ExcelUtil.getCellStringValue(cell);
			if (StringUtils.isEmpty(cellValue)) {
				continue;
			}
			if (cellValue.startsWith(beginSymble) || cellValue.startsWith(endSymble)) {
				availableRows.add(i);
			}
		}
		return availableRows;
	}
	
	/**
	 * 获取第一个单元格名字为：箱号 的行。因为01表中有很多空行
	 * @param sheet
	 * @param startRow
	 * @return
	 */
	private int getBoxTitleRow(Sheet sheet , int startRow){
		int rows = sheet.getLastRowNum();
		int rowNum = -1;
		String beginSymble = "箱号";
		for (int i = startRow; i < rows; i++) {
			Row row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			Cell cell = row.getCell(0);
			if (null == cell) {
				continue;
			}
			String cellValue = ExcelUtil.getCellStringValue(cell);
			if (StringUtils.isEmpty(cellValue)) {
				continue;
			}
			if (cellValue.startsWith(beginSymble)){
				rowNum = i;
				break;
			}
		}
		return rowNum;
	}
	
	/**
	 * 颜色值保留"-"符号之前的文字
	 * @param color
	 * @return
	 */
	private String formatColor(String color){
		if (StringUtils.isEmpty(color)) {
			return color;
		}
		int index = color.indexOf("-");
		if (index == -1) {
			return color;
		}
		return color.substring(0, index);
	}
	
	/**
	 * 获取order 尾号
	 * @param orderNo
	 * @return
	 */
	private String getOrderType(String orderNo) {
		int index = orderNo.indexOf("/");
		if (index == -1) {
			return orderNo;
		}
		return orderNo.substring(index + 1, orderNo.length());
	}
	
	private String formatSizeKey(String sizeParam) {
		sizeParam = sizeParam.replace(" ", "").replace("-", "");//去空格
		//Size XL, Size XXL 单独处理
		if ("SizeXL".equals(sizeParam)) {
			sizeParam = "SizeXl";
		}
		if ("SizeXXL".equals(sizeParam)) {
			sizeParam = "SizeXxl";
		}
		return sizeParam.replace(" ", "");
	}
	
	private Float formatVolume(String volumeCellValue) {
		if (volumeCellValue.endsWith("M3")) {
			volumeCellValue = volumeCellValue.substring(0, volumeCellValue.length()-2);
		}
		int index = volumeCellValue.indexOf("=");
		volumeCellValue = volumeCellValue.substring(index + 1, volumeCellValue.length());
		return Float.parseFloat(volumeCellValue);
	}
}
