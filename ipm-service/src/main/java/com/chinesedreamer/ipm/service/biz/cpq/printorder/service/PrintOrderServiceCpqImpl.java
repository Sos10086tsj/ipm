package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.format.StringUtil;
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
						
						CpqManufacotryOrderItem item = this.cpqManufacotryOrderItemLogic.findByOrderNoAndStyleNoAndColorAndFromNoAndToNo(orderNo, styleNo, tmpColor, tmpFrom, tmpTo);
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
		return null;
	}
}
