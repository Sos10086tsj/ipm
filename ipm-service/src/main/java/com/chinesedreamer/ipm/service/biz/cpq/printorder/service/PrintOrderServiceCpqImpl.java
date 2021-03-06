package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.constant.ConfigPropertiesConstant;
import com.chinesedreamer.ipm.common.utils.format.StringUtil;
import com.chinesedreamer.ipm.common.utils.io.PropertiesUtils;
import com.chinesedreamer.ipm.common.utils.office.ExcelUtil;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileClothingType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.logic.CpqFileLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic.CpqDictionaryLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic.CpqOrderItemLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic.CpqOrderLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.logic.CpqManufacotryOrderItemLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.domain.system.config.constant.IpmConfigConstant;
import com.chinesedreamer.ipm.domain.system.config.logic.IpmConfigLogic;
import com.chinesedreamer.ipm.domain.system.config.model.IpmConfig;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.CpqExcelType;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.ExcelVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.FileSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.RptOrderSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.SelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.excel.PutianmuExcelOrders;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.ManufactoryInfo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.report.TitleInfo;
import com.chinesedreamer.ipm.tools.pdf.reader.constant.PdfReaderType;
import com.chinesedreamer.ipm.tools.pdf.reader.model.IpmPdf;
import com.chinesedreamer.ipm.tools.pdf.reader.service.PdfFactory;
import com.chinesedreamer.ipm.tools.pdf.reader.service.PdfReader;

/**
 * 曹佩琴订单打印
 * @author Paris
 *
 */
@Service("cpqPrintOrderService")
public class PrintOrderServiceCpqImpl implements PrintOrderService{
	
	private Logger logger = LoggerFactory.getLogger(PrintOrderServiceCpqImpl.class);
	
	@Resource
	private PdfFactory pdfFactory;
	@Resource
	private CpqOrderLogic cpqOrderLogic;
	@Resource
	private CpqOrderItemLogic cpqOrderItemLogic;
	@Resource
	private CpqDictionaryLogic cpqDictionaryLogic;
	@Resource
	private IpmConfigLogic configLogic;
	@Resource
	private CpqFileLogic cpqFileLogic;
	@Resource
	private CpqManufacotryOrderItemLogic cpqManufacotryOrderItemLogic;
	@Resource
	private CpqExcelPrintService cpqExcelPrintService;
	@Resource
	private CpqExcelReadService cpqExcelReadService;
	
	private Map<String, String> errorMap = new HashMap<String, String>();
	
	private Set<String> existsColours;

	@Override
	public void readPdf(String filePath,CpqFile cpqFile) {
		this.logger.info("********** readPdf starting ********");
		PdfReader pdfReader = this.pdfFactory.getPdfReader(PdfReaderType.BOXPDF);
		IpmPdf pdf = pdfReader.read(filePath);
		String content = pdf.getContent();
		
//		IpmPdf pdf = new IpmPdf();
//
//		PDDocument document = null;
//		OutputStreamWriter out = null;
//		try {
//			document = PDDocument.load(filePath);
//			out = new OutputStreamWriter(new FileOutputStream("I:\\test\\test.txt"),"UTF-8");
//			
//			List allPages = document.getDocumentCatalog().getAllPages();
//			for (int i = 0; i < allPages.size(); i++) {
//				PDPage page = (PDPage)allPages.get(i);
//				PDRectangle pdr = page.findCropBox();
//				Rectangle rec = new Rectangle();
//				rec.setBounds(Math.round(pdr.getLowerLeftX()), 
//						Math.round(pdr.getLowerLeftY()), 
//						Math.round(pdr.getWidth()), 
//						Math.round(pdr.getHeight()));
//				System.out.println("Crobox:" + rec);
//				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//				stripper.addRegion("cropbox", rec);
//				stripper.setSortByPosition(true);
//				stripper.extractRegions(page);
//				List regions = stripper.getRegions();
//				for (int j = 0; j < regions.size(); j++) {
//					String region = (String)regions.get(j);
//					System.out.println("region:" + region);
//					String text = stripper.getTextForRegion(region);
//					System.out.println("text:" + text);
//				}
//			}
//			
//			return pdf;
//		} catch (Exception e) {
//			logger.error("pdf parse error", e);
//			return pdf;
//		} finally {
//			if (null != out)
//				try {
//					out.close();
//				} catch (IOException e) {
//					logger.error("IOExeption:", e);
//				}
//			if (null != document)
//				try {
//					document.close();
//				} catch (IOException e) {
//					logger.error("IOExeption:", e);
//				}
//		}
		
		
		if (StringUtils.isNotEmpty(content)) {
			//初始化已经存在的colour列表
			this.initSuppportedColurs();
			
			boolean continueRead = false;//连读模式，多行
			boolean itemBegin = false;//开始读item信息
			
			Map<String, String> datasource = new HashMap<String, String>();//order 数组
			List<Map<String, String>> items = new ArrayList<Map<String,String>>();//item 数组
			
			StringReader sr = new StringReader(content);
			BufferedReader br = new BufferedReader(sr);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			
			//获取不同类型的size列表
			List<CpqDictionary> sizeDicts = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.CLOTHING_CATEGORY_SIZE, cpqFile.getClothingType().toString());
			try {
				int startLien = 0;
				while ((str = br.readLine()) != null) {
					if (startLien == 3) {
						datasource.put("orderNo", StringUtil.subString(str, ": ", true));
					}
					if (startLien == 1) {
						datasource.put("styleNo", StringUtil.subString(str, ": ", true));
					}
					if (startLien > 0) {
						startLien -- ;
					}
					if (str.startsWith("ORDERNR")) {//表示一个新订单开始
						startLien = 5;
						//datasource.put("orderNo", StringUtil.subString(str, "ORDERNR: ", true));
					}
//					else if (str.startsWith("STYLE REFERENCE")) {
//						datasource.put("styleNo", StringUtil.subString(str, "STYLE REFERENCE: ", true));
//					}
//					else if (str.startsWith("NEW STYLE")) {//NEW STYLE  128601
//						datasource.put("styleNo", StringUtil.subString(str, "NEW STYLE", true));
//					}
					else if (str.startsWith("MAKER")) {
						continueRead = true;
					}else if (str.startsWith("CUSTOMER")) {
						datasource.put("maker", buffer.toString());
						buffer.setLength(0);
						buffer.append(str)
						.append(" ");
					}else if (str.startsWith("DESCRIPTION")) {
						datasource.put("customer", buffer.toString());
						buffer.setLength(0);
						continueRead = false;
					}else if (str.startsWith("USD")) {
						datasource.put("price", StringUtil.subString(str, "USD", true));
					}else if (str.startsWith("EUR")) {
						datasource.put("price", StringUtil.subString(str, "EUR", true));
					}else if (continueRead) {
						buffer.append(str)
						.append(" ");
					}
					//order 部分读取结束，开始读取item
					else if(str.startsWith("PRODUCTION ORDER")){
						itemBegin = true;
					}else if(str.startsWith("Signed by")){
						itemBegin = false;
						this.saveOrder(datasource, items, cpqFile, sizeDicts);//保存信息
						datasource.clear();
						items.clear();
					}else if(itemBegin){
						if(str.endsWith("TOTAL PIECES")){
							String[] colors = str.split(" ");
							for (String color : colors) {
								if (StringUtils.isNotEmpty(color)) {
									int index = color.indexOf("-");
									if (index != -1) {
										Map<String, String> item = new HashMap<String, String>();
										item.put("color", color.trim().toUpperCase().substring(0, index));
										items.add(item);
									}
								}
							}
						}else{
							String sizeParam = this.getSizeParam(sizeDicts, str);
							if(StringUtils.isNotEmpty(sizeParam)){
								
								//处理	Size 1/2 	Size 3/4 问题
								if(str.startsWith("Size 1/2")){
									String subStr = str.substring("Size 1/2".length());
									String[] sizeS = subStr.trim().split(" ");
									for (int i = 0; i < this.getMinSize(items, sizeS); i++){
										Map<String, String> item = items.get(i);
										item.put("Size 1", sizeS[0]);
										item.put("Size 2", sizeS[0]);
									}
								}else if (str.startsWith("Size 3/4")) {		
									String subStr = str.substring("Size 3/4".length());
									String[] sizeS = subStr.trim().split(" ");
									for (int i = 0; i < this.getMinSize(items, sizeS); i++){
										Map<String, String> item = items.get(i);
										item.put("Size 3", sizeS[0]);
										item.put("Size 4", sizeS[0]);
									}
								}else {
									String[] sizeS = StringUtil.subString(str, sizeParam, true).split(" ");
									for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
										String size = sizeS[i];
										if (StringUtils.isNotEmpty(size)) {
											Map<String, String> item = items.get(i);
											item.put(this.formatSizeKey(sizeParam), size);
										}
									}
								}
							}
						}
					}
				}
			} catch (IOException e) {
				this.logger.info("Error happened. Order:{}, style:{}, color:{}." ,datasource.get("orderNo"), datasource.get("styleNo"), datasource.get("color"));
				this.logger.error("pdf parse exception.", e);
			}
		}
		this.logger.info("********** readPdf end ********");
	}
	
	/**
	 * 判断解析到的字段是某种size类型
	 * @param sizeDicts
	 * @param str
	 * @return
	 */
	private String getSizeParam(List<CpqDictionary> sizeDicts, String str) {
		for (CpqDictionary dict : sizeDicts) {
			if (str.startsWith(dict.getValue())) {
				return dict.getValue();
			}
		}
		return "";
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
	
	/**
	 * 保存order以及item信息，重复时更新
	 * @param datasource
	 */
	private void saveOrder(Map<String, String> datasource, List<Map<String, String>> items,CpqFile cpqFile,List<CpqDictionary> sizeDicts){
		CpqOrder order = this.cpqOrderLogic.findByOrderNoAndStyleNo(datasource.get("orderNo"), datasource.get("styleNo"));
		if (null == order) {
			order = new CpqOrder();
		}
		order.setPdfId(cpqFile.getId());
		order.setOrderNo(datasource.get("orderNo"));
		order.setOrderNoType(this.getOrderType(datasource.get("orderNo")));
		order.setStyleNo(datasource.get("styleNo"));
		order.setMaker(datasource.get("maker"));
		order.setCustomer(datasource.get("customer"));
		order.setPrice(datasource.get("price").replace(",", ""));
		order = this.cpqOrderLogic.save(order);
		
		for (Map<String, String> item : items) {
			String color = item.get("color");
			CpqOrderItem orderItem = this.cpqOrderItemLogic.findByOrderIdAndColor(order.getId(), color);
			if (null == orderItem) {
				orderItem = new CpqOrderItem();
			}
			if (!this.existsColours.contains(color)) {
				errorMap.put("Missing configuration of color.", "color:" + color + "缺少配置");
				return ;
			}
			orderItem.setColor(color);
			for (CpqDictionary dict : sizeDicts) {
				String sizeParam = this.formatSizeKey(dict.getValue());
				if (StringUtils.isNotEmpty(item.get(sizeParam))) {
					try {
						Method method = CpqOrderItem.class.getDeclaredMethod("set" + sizeParam, Integer.class);
						method.invoke(orderItem, Integer.parseInt(item.get(sizeParam)));
					} catch (Exception e) {
						this.logger.info("Error happened. Order:{}, style:{}, color:{}." ,datasource.get("orderNo"), datasource.get("styleNo"), datasource.get("color"));
						this.logger.error("{}",e);
					}
				}
			}
			orderItem.setOrderId(order.getId());
			this.cpqOrderItemLogic.save(orderItem);
		}
	}
	
	/**
	 * 计算size数组与color数组最小值，处理空size
	 * @param items
	 * @param size
	 * @return
	 */
	private int getMinSize(List<Map<String, String>> items, String[] size) {
		if (null == items || null == size) {
			return 0;
		}
		return (items.size() > size.length ? size.length : items.size()) ;
	}
	
	private void initSuppportedColurs(){
		if (null == this.existsColours) {
			existsColours = new HashSet<String>();
		}
		List<CpqDictionary> exsits = this.cpqDictionaryLogic.findByType(CpqDictionaryType.COLOR);
		for (CpqDictionary exsit : exsits) {
			existsColours.add(exsit.getProperty());
		}
	}

	@Override
	public void readExcel(String filePath, CpqExcelType template, CpqFile cpqFile) {
		this.logger.info("********** readExcel start ********");
		FileInputStream is = null;
		Workbook workbook = null;
		try {
			is = new FileInputStream(filePath);
			if (filePath.endsWith(".xls")) {
				workbook = new HSSFWorkbook(is);
			}else if (filePath.endsWith(".xlsx")) {
				//TODO 07
			}else {
				logger.info("not support file:{}", filePath);
				return;
			}
			switch (template) {
			case JIANAN:
				this.readJiananExcel(workbook, cpqFile);
				break;
			case PUTIANMU:
				this.readPutianmuExcel(workbook, cpqFile);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.error("{}",e);
		}finally{
			if (null != workbook) {
				try {
					workbook.close();
				} catch (IOException e) {
					this.logger.error("{}",e);
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					this.logger.error("{}",e);
				}
			}
		}
		this.logger.info("********** readExcel end ********");
	}
	
	/**
	 * 佳楠长excel解析
	 * @param workbook
	 */
	private void readJiananExcel(Workbook workbook, CpqFile cpqFile) {
		Set<CpqManufacotryOrderItem> items = new HashSet<>();
		IpmConfig jiananConfig = this.configLogic.findByProperty(IpmConfigConstant.CPQ_EXCEL_JIANAN);
		if (null == jiananConfig) {
			logger.info("Missing 佳楠 normal configuration.");
		}else {
			String[] jiananConfigs = jiananConfig.getPropertyValue().split(",");
			Sheet[] jiananSheets = new Sheet[jiananConfigs.length];
			for (int i = 0; i < jiananConfigs.length; i++) {
				jiananSheets[i] = workbook.getSheet(jiananConfigs[i]);
			}
			items.addAll(this.readJiananExcelSheet(cpqFile,jiananSheets));
		}
		
		IpmConfig jiananSpecialConfig = this.configLogic.findByProperty(IpmConfigConstant.CPQ_EXCEL_JIANAN_SPECIAL);
		if (null == jiananSpecialConfig) {
			logger.info("Missing 佳楠 special configuration.");
		}else {
			String[] jiananConfigs = jiananSpecialConfig.getPropertyValue().split(",");
			Sheet[] jiananSheets = new Sheet[jiananConfigs.length];
			for (int i = 0; i < jiananConfigs.length; i++) {
				jiananSheets[i] = workbook.getSheet(jiananConfigs[i]);
			}
			items.addAll(this.cpqExcelReadService.readJianan01ExcelSheet(cpqFile, jiananSheets));
		}
		for (CpqManufacotryOrderItem item : items) {
			this.cpqManufacotryOrderItemLogic.save(item);
		}
	}
	
	/**
	 * 解析普天姆excel
	 * @param workbook
	 * @param cpqFile
	 */
	private void readPutianmuExcel(Workbook workbook, CpqFile cpqFile){
		Set<CpqManufacotryOrderItem> items = new HashSet<>();
		IpmConfig putianmuConfig = this.configLogic.findByProperty(IpmConfigConstant.CPQ_EXCEL_PUTIANMU);
		if (null == putianmuConfig) {
			logger.info("Missing 普天姆 configuration.");
		}else {
			String[] putianmuConfigs = putianmuConfig.getPropertyValue().split(",");
			Sheet[] putianmuSheets = new Sheet[putianmuConfigs.length];
			for (int i = 0; i < putianmuConfigs.length; i++) {
				putianmuSheets[i] = workbook.getSheet(putianmuConfigs[i]);
			}
			items.addAll(this.readPutianmuExcelSheet(cpqFile, putianmuSheets));
		}
		for (CpqManufacotryOrderItem item : items) {
			this.cpqManufacotryOrderItemLogic.save(item);
		}
	}
	
	/**
	 * 解析普天姆sheet
	 * @param cpqFile
	 * @param sheets
	 * @return
	 */
	private Set<CpqManufacotryOrderItem> readPutianmuExcelSheet(CpqFile cpqFile, Sheet... sheets){
		Set<CpqManufacotryOrderItem> items = new HashSet<>();
		Map<String, CpqManufacotryOrderItem> tempMap = new HashMap<String, CpqManufacotryOrderItem>();
		//1. 读取香港、荷兰表中的所有 order no。 和 style no。
		Set<PutianmuExcelOrders> peos = this.getPutiamuSheetOrders(sheets);
		//2. 解对应的style no。 sheet
		if (peos.isEmpty()) {
			logger.info("missing Putianmu config sheet, exit");
			return items;
		}
		Workbook wb = null;
		for (Sheet sheet : sheets) {
			if (null != sheet) {
				wb = sheet.getWorkbook();
				break;
			}
		}
		for (PutianmuExcelOrders peo : peos) {
			String orderNo = peo.getOrderNo();
			String styleNo = peo.getStyleNo();
			String sheetName = this.getPutianmuSheetNameByStyleNo(styleNo);
			Sheet sheet = wb.getSheet(sheetName);
			if (null == sheet) {
				//1605-12.51309/  读取.和/之间的数字
				int index_1 = orderNo.indexOf(".");
				
//				if (-1 != index_1 && -1 != index_2) {
//					sheet = wb.getSheet(orderNo.substring(index_1 + 1, index_2));
//				}
				String sheetOrderName = "";
				if (-1 != index_1){
					sheetOrderName = orderNo.substring(index_1 + 1);
				}
				int index_2 = sheetOrderName.indexOf("/");
				if (-1 != index_2) {
					sheetOrderName = sheetOrderName.substring(0, index_2);
				}
				if (StringUtils.isEmpty(sheetOrderName)) {
					continue;
				}
				sheet = wb.getSheet(sheetOrderName);
				if (null == sheet) {
					continue;
				}
			}
			//2.1	获取对应style no，箱数、每箱件数、体积、毛重、净重
			int rows = sheet.getLastRowNum();
			int startRow = 0;
			int endRow = rows;
			for (int i = 0; i < endRow; i++) {
				Row row = sheet.getRow(i);
				if (null == row) {
					startRow = i + 1;
					break;
				}
				Cell orderNoCell = row.getCell(0);
				if (null == orderNoCell) {
					startRow = i + 1;
					break;
				}
				String orderNoCellValue = ExcelUtil.getCellStringValue(orderNoCell);
				if (StringUtils.isEmpty(orderNoCellValue)) {
					startRow = i + 1;
					break;
				}
				//当空row或者空cell出现后，停止
				if (orderNoCellValue.startsWith(orderNo)) {
					CpqManufacotryOrderItem item = new CpqManufacotryOrderItem();
					item.setOrderNo(orderNoCellValue);
					item.setOrderNoType(this.getOrderType(orderNoCellValue));
					item.setStyleNo(styleNo);
					item.setExcelId(cpqFile.getId());
					item.setOwner(cpqFile.getOwner());
					//item.setBoxQty(ExcelUtil.getCellIntegerValue(row.getCell(5)));
					//item.setPcsPerBox(ExcelUtil.getCellIntegerValue(row.getCell(6)));
					item.setVolumePerBox(
							ExcelUtil.getCellFloatValue(row.getCell(7))
							* ExcelUtil.getCellFloatValue(row.getCell(8))
							* ExcelUtil.getCellFloatValue(row.getCell(9))
							 / 1000000
							);
					item.setGrossWeightPerBox(ExcelUtil.getCellFloatValue(row.getCell(12)));
					item.setNetWeightPerBox(ExcelUtil.getCellFloatValue(row.getCell(11)));
					tempMap.put(orderNoCellValue, item);
				}
			}
			//2.2	根据style no获取不同color的值
			//2.2.1	获取不同order的开始row
			List<Integer> rowNums = this.getPutianmuSheetOrderNoRows(sheet, orderNo, startRow, endRow);
			for (int i = 0; i < rowNums.size(); i++) {
				int orderStartRow = rowNums.get(i);
				int orderEndRow = 0;
				if(i < rowNums.size() - 1){
					orderEndRow = rowNums.get(i+1);
				}else {
					orderEndRow = endRow;
				}
				Row row = sheet.getRow(orderStartRow);
				if (null == row) {
					continue;
				}
				Cell orderNoCell = row.getCell(0);
				if (null == orderNoCell) {
					continue;
				}
				String orderNoCellValue = ExcelUtil.getCellStringValue(orderNoCell);
				//国家cell
				//Cell countryCell = sheet.getRow(orderStartRow - 1).getCell(0);
				String countryCellValue = "";
						//国家有配置生成ExcelUtil.getCellStringValue(countryCell);
				List<Integer> colorRowNums = this.getPutianmuSheetOrderNoColorRows(sheet, orderStartRow, orderEndRow);	
				for (int j = 0; j < colorRowNums.size() - 1; ) {
					int colorStartRow = colorRowNums.get(j);
					int colorEndRow = colorRowNums.get(j+1);

					//tmp 值保存上个value，处理合并单元格问题
					Integer tmpFromNo = 0;
					Integer tmpToNo = 0;
					Integer tmpBoxQty = 0;
					Integer tmPcs = 0;
					String tmpColor = "";
					
					for (int k = colorStartRow; k < colorEndRow; k++) {
						Row colorItemRow = sheet.getRow(k);
						
						Cell colorCell = colorItemRow.getCell(0);
						if (null != colorCell) {
							String colorCellValue = ExcelUtil.getCellStringValue(colorCell);
							//修复color有中文汉字时，造成的错误
							if (StringUtils.isNotEmpty(colorCellValue) ) {
								colorCellValue = this.formatColorString(colorCellValue);
								if(!this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.COLOR, colorCellValue).isEmpty()){
									tmpColor = colorCellValue;
								}
							}
						}

						Float fromNo = ExcelUtil.getCellFloatValue(colorItemRow.getCell(1));
						if (null != fromNo) {
							tmpFromNo = fromNo.intValue();
						}
						Float toNo = ExcelUtil.getCellFloatValue(colorItemRow.getCell(2));
						if (null != toNo) {
							tmpToNo = toNo.intValue();
						}
						logger.info(">>>>>>>>>>>>>>orderNoCellValue:" + orderNoCellValue
								+ "styleNo:" +styleNo
								+ "tmpColor:" +tmpColor
								+ "tmpFromNo:" +tmpFromNo
								+ "tmpToNo:" +tmpToNo
								+ "cpqFile.getOwner():" +cpqFile.getOwner()
								);
						
						CpqManufacotryOrderItem saveItem = 
								this.cpqManufacotryOrderItemLogic
								.findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(orderNoCellValue, styleNo, tmpColor, tmpFromNo, tmpToNo, cpqFile.getOwner());
						if (null == saveItem) {
							CpqManufacotryOrderItem tempItem = tempMap.get(orderNoCellValue);
							saveItem = new CpqManufacotryOrderItem();
							saveItem.setOrderNo(tempItem.getOrderNo());
							saveItem.setOrderNoType(this.getOrderType(tempItem.getOrderNo()));
							saveItem.setStyleNo(tempItem.getStyleNo());
							saveItem.setExcelId(tempItem.getExcelId());
							saveItem.setOwner(tempItem.getOwner());
							saveItem.setVolumePerBox(tempItem.getVolumePerBox());
							saveItem.setGrossWeightPerBox(tempItem.getGrossWeightPerBox());
							saveItem.setNetWeightPerBox(tempItem.getNetWeightPerBox());
						}
						saveItem.setCountry(countryCellValue);
						saveItem.setFromNo(tmpFromNo);
						saveItem.setToNo(tmpToNo);
						saveItem.setColor(tmpColor);
						Float boxQty = ExcelUtil.getCellFloatValue(colorItemRow.getCell(3));
						if (null != boxQty) {
							tmpBoxQty = boxQty.intValue();
						}
						saveItem.setBoxQty(tmpBoxQty);
						List<String> sizes = this.getClothingTypeSizes(cpqFile.getClothingType().toString());
						for (int l = 0; l < sizes.size(); l++) {//4 到  3+ sizes.size
							Cell sizeCell = colorItemRow.getCell(4 + l);
							Integer sizeCellValue = ExcelUtil.getCellIntegerValue(sizeCell);
							if (null != sizeCellValue) {
								try {
									Method setMethod = CpqManufacotryOrderItem.class.getDeclaredMethod("set" + sizes.get(l), Integer.class);
									setMethod.invoke(saveItem, sizeCellValue);
								} catch (Exception e) {
									this.logger.error("{}",e);
								}
							}
						}
						Float pcs = ExcelUtil.getCellFloatValue(colorItemRow.getCell(4 + sizes.size()));
						if (null != pcs) {
							tmPcs = pcs.intValue();
						}
						saveItem.setPcsPerBox(tmPcs);
						items.add(saveItem);
					}
					j+=2;
				}
			}
		}
		
		return items;
	}
	
	/**
	 * 获得普天姆一个order no中，每个color所在的行数。两个一对
	 * @param sheet
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	private List<Integer> getPutianmuSheetOrderNoColorRows(Sheet sheet,int startRow, int endRow){
		List<Integer> colorRowNums = new ArrayList<Integer>();
		Integer model = 0; //0:等待color，1：等待ttl
		for (int i = startRow; i < endRow; i++) {
			Row row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			Cell colorCell = row.getCell(0);
			if (null != colorCell && model == 0) {
				String colorCellValue = ExcelUtil.getCellStringValue(colorCell);
				if (StringUtils.isNotEmpty(colorCellValue)) {
					colorCellValue = this.formatColorString(colorCellValue);
					List<CpqDictionary> color = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.COLOR, colorCellValue);
					if (null != color && color.size() > 0) {//是配置的color值
						colorRowNums.add(i);
						model = 1;
					}
				}
			}
			Cell ttlCell = row.getCell(1);
			if (null != ttlCell && model == 1) {
				String ttlCellValue = ExcelUtil.getCellStringValue(ttlCell);
				if (StringUtils.isNotEmpty(ttlCellValue) && ttlCellValue.equals("TTL")) {//一种color结束
					colorRowNums.add(i);
					model = 0;
//					return colorRowNums;
				}
			}
		}
		return colorRowNums;
	}
	
	/**
	 * 如果 color是类似 970.0 这种形式，返回 970
	 * @param color
	 * @return
	 */
	private String formatColorString(String color) {
		if (StringUtils.isEmpty(color)) {
			return color;
		}
		int index = color.indexOf(".");
		if (index == -1) {
			return color;
		}
		return color.substring(0,index);
	}
	
	/**
	 * 获取普天姆style表中，每行order的起始行
	 * @param sheet
	 * @param orderNo
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	private List<Integer> getPutianmuSheetOrderNoRows(Sheet sheet, String orderNo, int startRow, int endRow){
		List<Integer> rowNums = new ArrayList<Integer>();
		for (int i = startRow; i < endRow; i++) {
			Row row = sheet.getRow(i);
			if (null == row) {
				continue;
			}
			Cell orderNoCell = row.getCell(0);
			if (null == orderNoCell) {
				continue;
			}
			String orderNoCellValue = ExcelUtil.getCellStringValue(orderNoCell);
			if (StringUtils.isNotEmpty(orderNoCellValue) && orderNoCellValue.startsWith(orderNo)) {
				rowNums.add(i);
			}
		}
		return rowNums;
	}
	
	/**
	 * 根据style no。获取excel sheet表名
	 * @param styleNo
	 * @return
	 */
	private String getPutianmuSheetNameByStyleNo(String styleNo) {
		int index = styleNo.indexOf(".");
		if (-1 == index) {
			return styleNo ;
		}
		return styleNo.substring(index + 1, styleNo.length());
	}
	
	/**
	 * 读取普天姆sheet中的order no和style no列表
	 * @param sheets
	 * @return
	 */
	private Set<PutianmuExcelOrders> getPutiamuSheetOrders(Sheet... sheets) {
		Set<PutianmuExcelOrders> peos = new HashSet<PutianmuExcelOrders>();
		Set<String> exsits = new HashSet<String>();
		for (Sheet sheet : sheets) {
			if (null != sheet) {
				int rows = sheet.getLastRowNum();
				int startRow = 0;
				int endRow = rows;
				for (int i = 0; i < rows; i++) {
					Row row = sheet.getRow(i);
					if (null == row) {
						continue;
					}
					Cell cell = row.getCell(1);
					if (null == cell) {
						continue;
					}
					String cellValue = ExcelUtil.getCellStringValue(cell);
					if (StringUtils.isNotEmpty(cellValue) && cellValue.startsWith("ORDER NR")) {
						startRow = i + 1;
						break;
					}
				}
				//开始解读order no。和style no。
				for (int i = startRow; i < endRow; i++) {
					Row row = sheet.getRow(i);
					if (null == row) {
						continue;
					}
//					Cell orderCell = row.getCell(0);
//					Cell styleCell = row.getCell(1);
					Cell orderCell = row.getCell(1);
					Cell styleCell = row.getCell(0);
					if (null == orderCell || null == styleCell) {
						continue;
					}
					String orderNo = ExcelUtil.getCellStringValue(orderCell);
					String styleNo = ExcelUtil.getCellStringValue(styleCell);
					
					if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(styleNo) || orderNo.contains("要求")) {
						continue;
					}
					if (styleNo.endsWith(".0")) {
						styleNo = styleNo.substring(0, styleNo.length() - 2);
					}
					if (!exsits.contains(orderNo + "@@@@@@@@@@" + styleNo)) {
						peos.add(new PutianmuExcelOrders(orderNo, styleNo));
						exsits.add(orderNo + "@@@@@@@@@@" + styleNo);
					}
				}
			}
		}
		return peos;
	}
	
	/**
	 * 解析佳楠sheet
	 * @param cpqFile
	 * @param sheets
	 * @return
	 */
	private Set<CpqManufacotryOrderItem> readJiananExcelSheet(CpqFile cpqFile, Sheet... sheets) {
		Set<CpqManufacotryOrderItem> items = new HashSet<>();
		String beginSymble = "合约号";
		String endSymble = "合计";
		for (Sheet sheet : sheets) {
			if (null != sheet) {
				int rows = sheet.getLastRowNum();
				if (rows < 1) {
					this.logger.info("sheet:{} is empty.",sheet.getSheetName());
				}
				this.logger.info("sheet name:{}", sheet.getSheetName());
				int startRow = 0;
				int endRow = 0;
				Integer tmpFrom = -1;
				Integer tmpTo = -1;
				String tmpColor = "";
				Integer tmpBoxQty = 0;
				Float tmpGrossWeight = 0f;
				Float tmpNetWeight = 0f;
				Float tmpVolume = 0f;
				
				for (int i = 0; i < rows;) {
					Row row = sheet.getRow(i);	
					if (null == row) {//excel已经到底
						break;
					}
					Cell cell = row.getCell(0);
					if (null == cell) {
						break;
					}
					String cellValue = ExcelUtil.getCellStringValue(cell);
					if (StringUtils.isNotEmpty(cellValue) && cellValue.startsWith(beginSymble)) {
						startRow = i;
					}else {
						i++;
						continue;
					}
					//第一行：订单号、款号
					Row row1 = sheet.getRow(startRow);
					String orderNo = ExcelUtil.getCellStringValue(row1.getCell(2));//订单号
					
					//获取不同类型的size列表
					List<CpqDictionary> sizeDicts = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.CLOTHING_CATEGORY_SIZE, cpqFile.getClothingType().toString());
					int sizes = sizeDicts.size();
					
					String styleNo = ExcelUtil.getCellStringValue(row1.getCell(2 + sizes + 5));//款号
					if (styleNo.endsWith(".0")) {
						styleNo = styleNo.substring(0, styleNo.length() - 2);
					}
					
					//第二行：国家、备注号
					startRow++;
					Row row2 = sheet.getRow(startRow);
					String country = "";
							//国家有配置生成 ExcelUtil.getCellStringValue(row2.getCell(5));//国家
					String remark = ExcelUtil.getCellStringValue(row2.getCell(12));//备注
					startRow += 3;
					
					for (int j = startRow; j < rows; j++) {
						Row end = sheet.getRow(j);
						if (endSymble.equals(ExcelUtil.getCellStringValue(end.getCell(0)))) {
							endRow = j;
							break;
						}
					}
					
					for (int j = startRow; j < endRow; j++) {
						//第四行：item开始
						Row itemRow = sheet.getRow(j);
						String from = ExcelUtil.getCellStringValue(itemRow.getCell(0));
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
						//String to = row4.getCell(1).getStringCellValue();//to TODO 目前没有
						
						String colorCellValue = ExcelUtil.getCellStringValue(itemRow.getCell(2));
						if (StringUtils.isNotEmpty(colorCellValue)) {
							tmpColor = this.formatColor(colorCellValue);
						}
						
						CpqManufacotryOrderItem item = this.cpqManufacotryOrderItemLogic.findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(orderNo, styleNo, tmpColor, tmpFrom, tmpTo, cpqFile.getOwner());
						if (null == item) {
							item = new CpqManufacotryOrderItem();
						}	
						item.setOrderNo(orderNo);
						item.setOrderNoType(this.getOrderType(orderNo));
						item.setStyleNo(styleNo);
						item.setCountry(country);
						item.setRemark(remark);
						item.setFromNo(tmpFrom);
						item.setToNo(tmpTo);
						item.setColor(tmpColor);
						item.setOwner(cpqFile.getOwner());
						
						Integer qualityCellValue = ExcelUtil.getCellIntegerValue(itemRow.getCell(1));
						if (null != qualityCellValue) {
							tmpBoxQty = qualityCellValue;
						}
						item.setBoxQty(tmpBoxQty);
						
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
									this.logger.info("Error happened. Order:{}, style:{}, color:{}." ,orderNo, styleNo, tmpColor);
									this.logger.error("{}",e);
								} 
							}
						}
						Float pcs = ExcelUtil.getCellFloatValue(itemRow.getCell(3 + sizes));
						if (null != pcs) {
							item.setPcsPerBox(pcs.intValue());
						}
						Float grossWeightCellValue = ExcelUtil.getCellFloatValue(itemRow.getCell(5 + sizes));
						if (null != grossWeightCellValue) {
							tmpGrossWeight =  grossWeightCellValue;
						}
						item.setGrossWeightPerBox(tmpGrossWeight);
						Float netWeightCellValue = ExcelUtil.getCellFloatValue(itemRow.getCell(6 + sizes));
						if (null != netWeightCellValue) {
							tmpNetWeight = netWeightCellValue;
						}
						item.setNetWeightPerBox(tmpNetWeight);
						String volumeCellValue = ExcelUtil.getCellStringValue(itemRow.getCell(7 + sizes));
						if (StringUtils.isNotEmpty(volumeCellValue)) {
							tmpVolume = this.formatVolume(volumeCellValue);
						}
						item.setVolumePerBox(tmpVolume);
						item.setExcelId(cpqFile.getId());
						items.add(item);
					}
					i = endRow + 5;
				}
			}
		}
		return items;
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
	
	private Float formatVolume(String volumeCellValue) {
		if (volumeCellValue.endsWith("M3")) {
			volumeCellValue = volumeCellValue.substring(0, volumeCellValue.length()-2);
		}
		int index = volumeCellValue.indexOf("=");
		volumeCellValue = volumeCellValue.substring(index + 1, volumeCellValue.length());
		return Float.parseFloat(volumeCellValue);
	}

	
	@Override
	public CpqFile saveFileBizValue(Attachment attachment,String clothingType, String owner) {
		CpqFile file = new CpqFile();
		file.setFileName(attachment.getFileName());
		file.setType(CpqFileType.getType(attachment.getFileName()));
		file.setAttachRefId(attachment.getId());
		file.setDeleted(Boolean.FALSE);
		file.setUploadDate(attachment.getUploadDate());
		file.setClothingType(CpqFileClothingType.valueOf(clothingType));
		file.setOwner(owner);
		return this.cpqFileLogic.save(file);
	}

	@Override
	public List<PdfVo> getPdfItems(Long pdfId) {
		List<PdfVo> vos = new ArrayList<PdfVo>();
		CpqFile cpqFile = this.cpqFileLogic.findOne(pdfId);
		List<String> sizes = this.getClothingTypeSizes(cpqFile.getClothingType().toString());
		
		List<CpqOrder> orders = this.cpqOrderLogic.findByPdfId(pdfId);
		for (CpqOrder order : orders) {
			List<CpqOrderItem> orderItems = this.cpqOrderItemLogic.findByOrderId(order.getId());
			for (CpqOrderItem orderItem : orderItems) {
				vos.add(this.convert2PdfVo(order, orderItem,sizes));
			}
		}
		return vos;
	}
	
	@Override
	public List<PdfVo> getPdfItems(String orderNo){
		List<PdfVo> vos = new ArrayList<PdfVo>();
		
		List<CpqOrder> orders = this.cpqOrderLogic.findByOrderNo(orderNo);
		List<String> sizes = this.getClothingTypeSizes(this.cpqFileLogic.findOne(orders.get(0).getPdfId()).getClothingType().toString());
		
		for (CpqOrder order : orders) {
			List<CpqOrderItem> orderItems = this.cpqOrderItemLogic.findByOrderId(order.getId());
			for (CpqOrderItem orderItem : orderItems) {
				vos.add(this.convert2PdfVo(order, orderItem,sizes));
			}
		}
		return vos;
	}

	private PdfVo convert2PdfVo(CpqOrder order,CpqOrderItem orderItem,List<String> sizes) {
		PdfVo vo = new PdfVo();
		vo.setOrder(order.getOrderNo());
		vo.setStyle(order.getStyleNo());
		vo.setColour(orderItem.getColor());
		Integer total = 0;
		for (String size : sizes) {
			try {
				Method getMethod = CpqOrderItem.class.getDeclaredMethod("get"+size);
				Integer value = (Integer)getMethod.invoke(orderItem);
				if (null != value) {
					total += value;
					Method setMethod  = PdfVo.class.getDeclaredMethod("set"+size, Integer.class);
					setMethod.invoke(vo, value);
				} 
			}catch (Exception e) {
				this.logger.error("{}",e);
			} 
		}
		vo.setTtl(total);
		if (StringUtils.isNotEmpty(order.getPrice())) {
			vo.setTotalAmount(total * Integer.parseInt(order.getPrice()));
		}
		return vo;
	}

	@Override
	public List<SelectVo> getClothingTypes() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<CpqDictionary> dictionaries = this.cpqDictionaryLogic.findByType(CpqDictionaryType.CLOTHING_CATEGORY);
		for (CpqDictionary dict : dictionaries) {
			vos.add(new SelectVo(dict.getProperty(), dict.getValue()));
		}
		return vos;
	}

	@Override
	public List<FileSelectVo> getUploadedFileStore(CpqFileType type, String manufactory) {
		List<CpqFile> files = this.cpqFileLogic.findByTypeOrderByUploadDate(type);
		List<FileSelectVo> vos = new ArrayList<FileSelectVo>();
		for (CpqFile file : files) {
			if (StringUtils.isEmpty(manufactory) && !this.cpqOrderLogic.findByPdfId(file.getId()).isEmpty()) {
				vos.add(new FileSelectVo(file.getId().toString(), file.getFileName() + "(" + DateFormatUtils.format(file.getUploadDate(), "MM/dd HH:mm") + ")", file.getClothingType().toString()));
			}
			if (StringUtils.isNotEmpty(manufactory) && !this.cpqManufacotryOrderItemLogic.getExcelItems(file.getId(),manufactory).isEmpty()) {
				vos.add(new FileSelectVo(file.getId().toString(), file.getFileName() + "(" + DateFormatUtils.format(file.getUploadDate(), "MM/dd HH:mm") + ")", file.getClothingType().toString()));
			}
		}
		return vos;
	}

	@Override
	public List<SelectVo> getManufactoryStore() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		for (CpqExcelType type : CpqExcelType.values()) {
			vos.add(new SelectVo(type.toString(), type.getName()));
		}
		return vos;
	}

	@Override
	public List<ExcelVo> getExcelItems(Long excelId, String excelType) {
		List<ExcelVo> vos = new ArrayList<ExcelVo>();
		CpqFile cpqFile = this.cpqFileLogic.findOne(excelId);
		List<String> sizes = this.getClothingTypeSizes(cpqFile.getClothingType().toString());
		List<CpqManufacotryOrderItem> items = this.cpqManufacotryOrderItemLogic.getExcelItems(excelId, excelType);
		for (CpqManufacotryOrderItem item : items) {
			vos.add(this.convert2ExcelVo(item, sizes));
		}
		return vos;
	}
	
	private ExcelVo convert2ExcelVo(CpqManufacotryOrderItem item, List<String> sizes) {
		ExcelVo vo = new ExcelVo();
		vo.setOrder(item.getOrderNo());
		vo.setStyle(item.getStyleNo());
		vo.setFromNo(item.getFromNo());
		vo.setToNo(item.getToNo());
		vo.setColour(item.getColor());
		for (String size : sizes) {
			try {
				Method getMethod = CpqManufacotryOrderItem.class.getDeclaredMethod("get"+size);
				Integer value = (Integer)getMethod.invoke(item);
				if (null != value) {
					Method setMethod  = ExcelVo.class.getDeclaredMethod("set"+size, Integer.class);
					setMethod.invoke(vo, value);
				} 
			}catch (Exception e) {
				this.logger.error("{}",e);
			}
		}
		vo.setBoxQty(item.getBoxQty());
		vo.setPcs(item.getPcsPerBox());
		vo.setCountry(item.getCountry());
		vo.setGrossWeight(item.getGrossWeightPerBox());
		vo.setNetWeight(item.getNetWeightPerBox());
		vo.setVolume(item.getVolumePerBox());
		vo.setRemark(item.getRemark());
		return vo;
	}
	
	/**
	 * 根据类型获取相关size属性
	 * @param clothingType
	 * @return
	 */
	private List<String> getClothingTypeSizes(String clothingType) {
		List<CpqDictionary> sizeDicts = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.CLOTHING_CATEGORY_SIZE, clothingType);
		List<String> sizes = new ArrayList<String>();
		for (CpqDictionary dict : sizeDicts) {
			sizes.add(this.formatSizeKey(dict.getValue()));//比如SizeS
		}
		return sizes;
	}

	/************* 打印 ********************/
	@Override
	public List<RptOrderSelectVo> getOrders(String key,String orderType) {
		List<String> orders = null;
		if (StringUtils.isEmpty(key)) {
			orders = this.cpqOrderLogic.findOrders(this.getOrderTypes(orderType));
		}else {
			orders = this.cpqOrderLogic.findOrdersByOrderNoLike("%" + key.trim() + "%",this.getOrderTypes(orderType));
		}
		List<RptOrderSelectVo> vos = new ArrayList<RptOrderSelectVo>();
		for (String order : orders) {
			vos.add(new RptOrderSelectVo(order));
		}
		return vos;
	}
	
	public List<String> getOrderTypes(String orderType) {
		List<String> orderTypes = new ArrayList<String>();
		IpmConfig config = configLogic.findByProperty(orderType);
		for (String orderTypeNo : config.getPropertyValue().split(",")) {
			orderTypes.add(orderTypeNo);
		}
		return orderTypes;
	}

	@Override
	public File printExcelReport(String orderNos, String manufactory,String orderType) {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
		String[] orders = orderNos.split(",");
		PropertiesUtils propertiesUtils = new PropertiesUtils("config.properties");
		String outputPath = propertiesUtils.getProperty(ConfigPropertiesConstant.fILE_OUTPUT_PATH);
		File folder = new File(outputPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File outputFile = new File(folder + "/" +  manufactory + System.currentTimeMillis() + ".xls");
		for (String order : orders) {
			//1. 获取item
			List<CpqManufacotryOrderItem> items = this.cpqManufacotryOrderItemLogic.findByOrderNoAndOwner(order, manufactory);
			if (items.isEmpty()) {
				continue;
			}
			List<String> sizes = this.getClothingTypeSizes(this.cpqFileLogic.findOne(items.get(0).getExcelId()).getClothingType().toString());
			
			HSSFSheet sheet = workbook.createSheet(order.replace("/", "-"));//excel表明不能有“/”符号
			
			boolean hasCountry = false;
			if (orderType.equals(IpmConfigConstant.CPQ_ORDER_TYPE_HK.toString())) {
				hasCountry = true;
			}
			
			//2. 打印抬头
			this.cpqExcelPrintService.printManufactory(workbook, sheet, ManufactoryInfo.getManufactoryInfo(manufactory));
			//3. 打印头部信息
			TitleInfo titleInfo = TitleInfo.getTitleInfo(orderType);
			titleInfo.setStyleNo(items.get(0).getStyleNo());
			titleInfo.setOrderNo(order);
			titleInfo.setManufactorurer("NINGBO Z & H FOREIGN TRADE CO., LTD.");
			titleInfo.setExporter("NINGBO Z & H FOREIGN TRADE CO., LTD.");
			titleInfo.setLcNo(hasCountry ? "T/T" : "D/P");
			this.cpqExcelPrintService.printTitle(workbook, sheet, titleInfo);
			//4. 打印表头
			
			this.cpqExcelPrintService.printTableTitle(workbook, sheet, hasCountry, sizes);
			
			//18.	 逐行打印数据
			this.cpqExcelPrintService.printDataRow(workbook, sheet, hasCountry, sizes, items);
			
//			for (int i = 0; i < 14; i++) {
//				sheet.autoSizeColumn(i, true);
//			}
			sheet.autoSizeColumn(2, true);
			workbook.setForceFormulaRecalculation(true);
		}
		//2. 打印
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			this.logger.error("{}",e);
		}finally{
			try {
				workbook.close();
			} catch (IOException e) {
				this.logger.error("{}",e);
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					this.logger.error("{}",e);
				}
			}
		}
		return outputFile;
	}

	@Override
	public List<FileSelectVo> getPdfOrders(String orderNo) {
		List<FileSelectVo> vos = new ArrayList<FileSelectVo>();
		Set<String> orderNos = new HashSet<String>();
		List<CpqOrder> orders = this.cpqOrderLogic.findByOrderNoLike(orderNo);
		for (CpqOrder order : orders) {
			if (!orderNos.contains(order.getOrderNo())) {
				vos.add(new FileSelectVo(order.getOrderNo(), order.getOrderNo(), this.cpqFileLogic.findOne(order.getPdfId()).getClothingType().toString()));
			}
		}
		return vos;
	}

	@Override
	public void updatePdfRow(String order, String style, String colour,
			String sizeS, String sizeM, String sizeL, String sizeXl,
			String sizeXxl, String sizeP, String size1, String size2,
			String size3, String size4, String size6, String size8,
			String size10, String size12, String size14, String size16,
			String sizeUNI1,String sizeUNI2,String sizeUNI3,String sizeUNI4,String sizeUNI5,String sizeUNI6) {
		CpqOrder co = this.cpqOrderLogic.findByOrderNoAndStyleNo(order, style);
		CpqOrderItem item = this.cpqOrderItemLogic.findByOrderIdAndColor(co.getId(), colour);
		item.setSizeS(StringUtils.isEmpty(sizeS) ? null : Integer.parseInt(sizeS));
		item.setSizeM(StringUtils.isEmpty(sizeM) ? null : Integer.parseInt(sizeM));
		item.setSizeL(StringUtils.isEmpty(sizeL) ? null : Integer.parseInt(sizeL));
		item.setSizeXl(StringUtils.isEmpty(sizeXl) ? null : Integer.parseInt(sizeXl));
		item.setSizeXxl(StringUtils.isEmpty(sizeXxl) ? null : Integer.parseInt(sizeXxl));
		item.setSizeP(StringUtils.isEmpty(sizeP) ? null : Integer.parseInt(sizeP));
		item.setSize1(StringUtils.isEmpty(size1) ? null : Integer.parseInt(size1));
		item.setSize2(StringUtils.isEmpty(size2) ? null : Integer.parseInt(size2));
		item.setSize3(StringUtils.isEmpty(size3) ? null : Integer.parseInt(size3));
		item.setSize4(StringUtils.isEmpty(size4) ? null : Integer.parseInt(size4));
		item.setSize6(StringUtils.isEmpty(size6) ? null : Integer.parseInt(size6));
		item.setSize8(StringUtils.isEmpty(size8) ? null : Integer.parseInt(size8));
		item.setSize10(StringUtils.isEmpty(size10) ? null : Integer.parseInt(size10));
		item.setSize12(StringUtils.isEmpty(size12) ? null : Integer.parseInt(size12));
		item.setSize14(StringUtils.isEmpty(size14) ? null : Integer.parseInt(size14));
		item.setSize16(StringUtils.isEmpty(size16) ? null : Integer.parseInt(size16));
		item.setSizeUNI1(StringUtils.isEmpty(sizeUNI1) ? null : Integer.parseInt(sizeUNI1));
		item.setSizeUNI2(StringUtils.isEmpty(sizeUNI2) ? null : Integer.parseInt(sizeUNI2));
		item.setSizeUNI3(StringUtils.isEmpty(sizeUNI3) ? null : Integer.parseInt(sizeUNI3));
		item.setSizeUNI4(StringUtils.isEmpty(sizeUNI4) ? null : Integer.parseInt(sizeUNI4));
		item.setSizeUNI5(StringUtils.isEmpty(sizeUNI5) ? null : Integer.parseInt(sizeUNI5));
		item.setSizeUNI6(StringUtils.isEmpty(sizeUNI6) ? null : Integer.parseInt(sizeUNI6));
		this.cpqOrderItemLogic.save(item);
	}
}