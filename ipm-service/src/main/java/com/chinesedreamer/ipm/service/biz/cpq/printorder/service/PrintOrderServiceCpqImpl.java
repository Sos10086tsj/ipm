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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.ColorSizeVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.ExcelVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.FileSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.RptOrderSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.SelectVo;
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
	
	private Map<String, String> errorMap = new HashMap<String, String>();
	
	private Set<String> existsColours;

	@Override
	public void readPdf(String filePath,CpqFile cpqFile) {
		this.logger.info("********** readPdf starting ********");
		PdfReader pdfReader = this.pdfFactory.getPdfReader(PdfReaderType.BOXPDF);
		IpmPdf pdf = pdfReader.read(filePath);
		String content = pdf.getContent();
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
				while ((str = br.readLine()) != null) {
					if (str.startsWith("ORDERNR")) {//表示一个新订单开始
						datasource.put("orderNo", StringUtil.subString(str, "ORDERNR", true));
					}else if (str.startsWith("STYLENR")) {
						datasource.put("styleNo", StringUtil.subString(str, "STYLENR", true));
					}else if (str.startsWith("MAKER")) {
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
										item.put("color", color.trim().toLowerCase().substring(0, index));
										items.add(item);
									}
								}
							}
						}else{
							String sizeParam = this.getSizeParam(sizeDicts, str);
							if(StringUtils.isNotEmpty(sizeParam)){
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
			} catch (IOException e) {
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
		sizeParam = sizeParam.replace(" ", "");//去空格
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
		order.setStyleNo(datasource.get("styleNo"));
		order.setMaker(datasource.get("maker"));
		order.setCustomer(datasource.get("customer"));
		order.setPrice(datasource.get("price").replace(",", ""));
		this.logger.debug("********* order:", order.toString());
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
						this.logger.error("{}",e);
					}
				}
			}
			this.logger.debug("********* orderItem:", orderItem.toString());
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
			items.addAll(this.readJiananExcelSheet(cpqFile,jiananSheets));
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
		
		for (CpqManufacotryOrderItem item : items) {
			this.cpqManufacotryOrderItemLogic.save(item);
		}
	}
	
	/**
	 * 解析佳楠sheet
	 * @param cpqFile
	 * @param sheets
	 * @return
	 */
	private Set<CpqManufacotryOrderItem> readJiananExcelSheet(CpqFile cpqFile, Sheet... sheets) {
		Set<CpqManufacotryOrderItem> items = new HashSet<>();
		String beginSymble = "宁波承天一楠制衣有限公司";
		String endSymble = "合计";
		for (Sheet sheet : sheets) {
			if (null != sheet) {
				int rows = sheet.getPhysicalNumberOfRows();
				if (rows < 1) {
					this.logger.info("sheet:{} is empty.",sheet.getSheetName());
				}
				
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
						startRow = i + 1;
					}else {
						i++;
						continue;
					}
					
					//第一行：订单号、款号
					Row row1 = sheet.getRow(startRow);
					String orderNo = ExcelUtil.getCellStringValue(row1.getCell(2));//订单号
					String styleNo = ExcelUtil.getCellStringValue(row1.getCell(12));//款号
					//获取不同类型的size列表
					List<CpqDictionary> sizeDicts = this.cpqDictionaryLogic.findByTypeAndProperty(CpqDictionaryType.CLOTHING_CATEGORY_SIZE, cpqFile.getClothingType().toString());
					int sizes = sizeDicts.size();
					//第二行：国家、备注号
					startRow++;
					Row row2 = sheet.getRow(startRow);
					String country = ExcelUtil.getCellStringValue(row2.getCell(5));//国家
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
									this.logger.error("{}",e);
								} 
							}
						}
						String pcs = ExcelUtil.getCellStringValue(itemRow.getCell(3 + sizes));
								//ExcelUtil.getCellIntegerValue(itemRow.getCell(3 + sizes));
						if (StringUtils.isNotEmpty(pcs)) {
							item.setPcsPerBox(Integer.parseInt(pcs));
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
	public List<RptOrderSelectVo> getOrders(String key) {
		List<String> orders = null;
		if (StringUtils.isEmpty(key)) {
			orders = this.cpqOrderLogic.findOrders();
		}else {
			orders = this.cpqOrderLogic.findOrdersByOrderNoLike("%" + key.trim() + "%");
		}
		List<RptOrderSelectVo> vos = new ArrayList<RptOrderSelectVo>();
		for (String order : orders) {
			vos.add(new RptOrderSelectVo(order));
		}
		return vos;
	}

	@Override
	public File printExcelReport(String orderNos, String manufactory) {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
		String[] orders = orderNos.split(",");
		PropertiesUtils propertiesUtils = new PropertiesUtils("config.properties");
		String outputPath = propertiesUtils.getProperty("i:/test/files/output/ipm/");
		File folder = new File(outputPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File outputFile = new File(folder +  manufactory + ".xls");
		for (String order : orders) {
			//1. 获取item
			List<CpqManufacotryOrderItem> items = this.cpqManufacotryOrderItemLogic.findByOrderNoAndOwner(order, manufactory);
			if (items.isEmpty()) {
				continue;
			}
			
			HSSFSheet sheet = workbook.createSheet(order);
			
			//第一行：工厂名
			HSSFRow row1 = sheet.createRow(0); 
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
			cell1_1.setCellValue("NINGBO Z & H FOREIGN TRADE COMPANY LIMITED");
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
			HSSFCell cell2_1 = row2.createCell(0);
			cell2_1.setCellStyle(cellStyle2_x);
			cell2_1.setCellType(Cell.CELL_TYPE_STRING);
			cell2_1.setCellValue("12F,BUILDING 3 OF SHANGDONG NATIONS, N0.1926 CANGHAI ROAD，");
			CellRangeAddress region2_1 = new CellRangeAddress(1, 1, 0, 11);
			sheet.addMergedRegion(region2_1);
			
			//第三行：工厂地址二
			HSSFRow row3 = sheet.createRow(2);
			HSSFCell cell3_1 = row3.createCell(0);
			cell3_1.setCellStyle(cellStyle2_x);
			cell3_1.setCellType(Cell.CELL_TYPE_STRING);
			cell3_1.setCellValue("NINGBO, ZHEJIANG,CHINA");
			CellRangeAddress region3_1 = new CellRangeAddress(2, 2, 0, 11);
			sheet.addMergedRegion(region3_1);
			
			//通用cell样式
			HSSFCellStyle cellStyle_x = workbook.createCellStyle();
			cellStyle_x.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle_x.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
			cellStyle_x.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
			cellStyle_x.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
			cellStyle_x.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			HSSFFont font_x = workbook.createFont();
			font_x.setFontName("Arial");
			font_x.setFontHeightInPoints((short)10);
			cellStyle_x.setFont(font_x);
			
			//第五行：客户名
			this.printTitleRow(4, "Customer Name: ", "SCOTCH & SODA B.V.", sheet, cellStyle_x);
			
			//第6，7 ， 8行，客户地址
			this.printTitleRow(5, "Address: ", "JACOBUS SPIJKERDREEF 20-24", sheet, cellStyle_x);
			this.printTitleRow(6, "", "2132 PZ HOOFDDORP", sheet, cellStyle_x);
			this.printTitleRow(7, "", "THE NETHERLANDS.", sheet, cellStyle_x);
			
			//9.	style no.
			this.printTitleRow(8, "Style no.: ", items.get(0).getStyleNo(), sheet, cellStyle_x);
			//10.	订单号
			this.printTitleRow(9, "Order no.: ", order, sheet, cellStyle_x);
			//11. TODO
			this.printTitleRow(10, "Description:", "", sheet, cellStyle_x);
			//12. TODO
			this.printTitleRow(11, "Invoice ref.No:", "", sheet, cellStyle_x);
			//13.	TODO
			this.printTitleRow(11, "Manufacturer", "", sheet, cellStyle_x);
			//14. TODO
			this.printTitleRow(11, "Exporter:", "", sheet, cellStyle_x);
			//15. TODO
			this.printTitleRow(11, "L/C No:", "", sheet, cellStyle_x);
			
			//打印表头
			//16.
			HSSFRow row16 = sheet.createRow(15);
			//16.1 box number
			this.printNormalCell(row16, 0, "Box Number", cellStyle_x);
			sheet.addMergedRegion(new CellRangeAddress(15, 15, 0, 1));
			//16.2 style
			this.printNormalCell(row16, 2, "Style", cellStyle_x);
			//16.3 Colour
			this.printNormalCell(row16, 3, "Colour", cellStyle_x);
			//16.4 sizes
			List<String> sizes = this.getClothingTypeSizes(this.cpqFileLogic.findOne(items.get(0).getExcelId()).getClothingType().toString());
			this.printNormalCell(row16, 4, "Sizes", cellStyle_x);
			sheet.addMergedRegion(new CellRangeAddress(15, 15, 4, 3 + sizes.size()));
			//16.5
			this.printNormalCell(row16, 4 + sizes.size(), "Artikel", cellStyle_x);
			//16.6 
			this.printNormalCell(row16, 5 + sizes.size(), "Box", cellStyle_x);
			//16.7
			this.printNormalCell(row16, 6 + sizes.size(), "Tot Art.", cellStyle_x);
			//16.8
			this.printNormalCell(row16, 7 + sizes.size(), "Remark", cellStyle_x);
			
			//17.
			HSSFRow row17 = sheet.createRow(16);
			sheet.addMergedRegion(new CellRangeAddress(15, 16, 7 + sizes.size(), 7 + sizes.size()));
			//17.1
			this.printNormalCell(row17, 0, "From", cellStyle_x);
			//17.2
			this.printNormalCell(row17, 1, "To", cellStyle_x);
			//17.3
			this.printNormalCell(row17, 2, "Number", cellStyle_x);
			//17.4
			this.printNormalCell(row17, 3, "name", cellStyle_x);
			//17.5
			for (int i = 0; i < sizes.size(); i++) {
				String sizeX = sizes.get(i);
				String sizeXValue = sizeX.substring("Size".length() + 1, sizeX.length()).toUpperCase();
				this.printNormalCell(row17, 4 + i, sizeXValue, cellStyle_x);
			}
			//17.6
			this.printNormalCell(row17, 3+sizes.size(), "per box", cellStyle_x);
			//17.7
			this.printNormalCell(row17, 4+sizes.size(), "Qty", cellStyle_x);
			//17.8
			this.printNormalCell(row17, 5+sizes.size(), "Qty", cellStyle_x);
			//18.	 逐行打印数据
			Integer totalBox = 0;
			Integer totalQty = 0;
			Map<String, ColorSizeVo> colorSizeMap = new HashMap<String, ColorSizeVo>();
			
			for (int i = 0; i < items.size(); i++) {
				int mergeRowStart = -1;
				int mergeRowEnd = -1;
				
				CpqManufacotryOrderItem item = items.get(i);
				
				HSSFRow itemRow = sheet.createRow(17 + i);
				this.printNormalCell(itemRow, 0, item.getFromNo() , cellStyle_x);
				this.printNormalCell(itemRow, 1, item.getToNo() , cellStyle_x);
				this.printNormalCell(itemRow, 2, item.getStyleNo() , cellStyle_x);
				this.printNormalCell(itemRow, 3, item.getColor() , cellStyle_x);
				for (int j = 0; j < sizes.size(); j++) {
					String size = sizes.get(i);
					try {
						Method getMethod = CpqManufacotryOrderItem.class.getDeclaredMethod("get"+size);
						Integer value = (Integer)getMethod.invoke(item);
						if (null != value) {
							ColorSizeVo vo = colorSizeMap.get(item.getColor());
							if (null == vo) {
								vo = new ColorSizeVo();
								vo.setColor(item.getColor());
								
							}
							Method mapGetMethod = ColorSizeVo.class.getDeclaredMethod("get"+size);
							Method mapSetMethod = ColorSizeVo.class.getDeclaredMethod("set"+size, Integer.class);
							Method mapShippedSetMethod = ColorSizeVo.class.getDeclaredMethod("setActual"+size, Integer.class);
							Integer mapValue = value + (Integer)mapGetMethod.invoke(vo);
							mapSetMethod.invoke(mapValue, vo);
							mapShippedSetMethod.invoke(mapValue, vo);
							colorSizeMap.put(item.getColor(), vo);
							this.printNormalCell(itemRow, 3 + j, value , cellStyle_x);
						} 
					}catch (Exception e) {
						this.logger.error("{}",e);
					}
				}
				this.printNormalCell(itemRow, 4+sizes.size(), item.getPcsPerBox() , cellStyle_x);
				this.printNormalCell(itemRow, 5+sizes.size(), item.getBoxQty() , cellStyle_x);
				totalBox += item.getBoxQty();
				this.printNormalCell(itemRow, 6+sizes.size(), item.getPcsPerBox() * item.getPcsPerBox() , cellStyle_x);
				totalQty += item.getPcsPerBox() * item.getPcsPerBox();
				this.printNormalCell(itemRow, 5+sizes.size(), item.getRemark() , cellStyle_x);
				
				//后面还存在可能需要合并的数据
				if (i < items.size() - 1) {
					CpqManufacotryOrderItem nextItem = items.get(i+1);
					if (nextItem.getFromNo().equals(item.getFromNo())) {//from 相同
						if (mergeRowStart == - 1) {//前面不存在merge行，此行为第一行
							mergeRowStart = 17 + i;
						}else {//前面已经有待合并行，此时结束行 + 1
							mergeRowEnd = 17 + i;
						}
					}else {//merge行处理
						if (-1 != mergeRowStart && -1 != mergeRowEnd) {
							sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 0, 0));
							sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 1, 1));
							sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 4+sizes.size(), 4+sizes.size()));
							sheet.addMergedRegion(new CellRangeAddress(mergeRowStart, mergeRowEnd, 5+sizes.size(), 5+sizes.size()));
						}
					}
				}
			}
			//20.	空两行
			HSSFRow row20 = sheet.createRow(19 + items.size());
			this.printNormalCell(row20, 4 + sizes.size(), "TOTAL", cellStyle_x);
			this.printNormalCell(row20, 5 + sizes.size(), totalBox, cellStyle_x);
			this.printNormalCell(row20, 6 + sizes.size(), totalQty, cellStyle_x);
			//21.	空两行
			HSSFRow row21 = sheet.createRow(21 + items.size());
			this.printNormalCell(row21, 2, "COLOUR  BREAKDOWN", cellStyle_x);
			for (int i = 0; i < sizes.size(); i++) {
				String sizeX = sizes.get(i);
				String sizeXValue = sizeX.substring("Size".length() + 1, sizeX.length()).toUpperCase();
				this.printNormalCell(row21, 4 + i, sizeXValue, cellStyle_x);
			}
			//22. color vo 
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
	
	/**
	 * 打印表头部分
	 */
	private void printTitleRow(int rowNum, String label, String value, HSSFSheet sheet, HSSFCellStyle cellStyle){
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell_1 = row.createCell(3);
		cell_1.setCellStyle(cellStyle);
		cell_1.setCellType(Cell.CELL_TYPE_STRING);
		cell_1.setCellValue(label);
		HSSFCell cell_2 = row.createCell(5);
		cell_2.setCellStyle(cellStyle);
		cell_2.setCellType(Cell.CELL_TYPE_STRING);
		cell_2.setCellValue(value);
	}
	
	/**
	 * 打印普通数据
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
	
	private List<ColorSizeVo> getColorSizes() {
		
	}
}
