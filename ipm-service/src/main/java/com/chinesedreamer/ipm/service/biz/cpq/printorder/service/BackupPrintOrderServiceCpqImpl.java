//package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.chinesedreamer.ipm.common.utils.format.StringUtil;
//import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
//import com.chinesedreamer.ipm.domain.biz.cpq.file.logic.CpqFileLogic;
//import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic.CpqDictionaryLogic;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic.CpqOrderItemLogic;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic.CpqOrderLogic;
//import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
//import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
//import com.chinesedreamer.ipm.domain.system.config.constant.IpmConfigConstant;
//import com.chinesedreamer.ipm.domain.system.config.logic.IpmConfigLogic;
//import com.chinesedreamer.ipm.domain.system.config.model.IpmConfig;
//import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.CpqExcelType;
//import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
//import com.chinesedreamer.ipm.tools.pdf.reader.constant.PdfReaderType;
//import com.chinesedreamer.ipm.tools.pdf.reader.model.IpmPdf;
//import com.chinesedreamer.ipm.tools.pdf.reader.service.PdfFactory;
//import com.chinesedreamer.ipm.tools.pdf.reader.service.PdfReader;
//
///**
// * 曹佩琴订单打印
// * @author Paris
// *
// */
//@Service("cpqPrintOrderService")
//public class BackupPrintOrderServiceCpqImpl implements PrintOrderService{
//	
//	private Logger logger = LoggerFactory.getLogger(BackupPrintOrderServiceCpqImpl.class);
//	
//	@Resource
//	private PdfFactory pdfFactory;
//	@Resource
//	private CpqOrderLogic cpqOrderLogic;
//	@Resource
//	private CpqOrderItemLogic cpqOrderItemLogic;
//	@Resource
//	private CpqDictionaryLogic cpqDictionaryLogic;
//	@Resource
//	private IpmConfigLogic configLogic;
//	
//	private Map<String, String> errorMap = new HashMap<String, String>();
//	
//	private Set<String> existsColours;
//
//	@Override
//	public void readPdf(String filePath,CpqFile cpqFile) {
//		this.logger.info("********** readPdf starting ********");
//		PdfReader pdfReader = this.pdfFactory.getPdfReader(PdfReaderType.BOXPDF);
//		IpmPdf pdf = pdfReader.read(filePath);
//		String content = pdf.getContent();
//		if (StringUtils.isNotEmpty(content)) {
//			//初始化已经存在的colour列表
//			this.initSuppportedColurs();
//			
//			boolean continueRead = false;//连读模式，多行
//			boolean itemBegin = false;//开始读item信息
//			
//			Map<String, String> datasource = new HashMap<String, String>();//order 数组
//			List<Map<String, String>> items = new ArrayList<Map<String,String>>();//item 数组
//			
//			StringReader sr = new StringReader(content);
//			BufferedReader br = new BufferedReader(sr);
//			String str = null;
//			StringBuffer buffer = new StringBuffer();
//			try {
//				while ((str = br.readLine()) != null) {
//					if (str.startsWith("ORDERNR")) {//表示一个新订单开始
//						datasource.put("orderNo", StringUtil.subString(str, "ORDERNR", true));
//					}else if (str.startsWith("STYLENR")) {
//						datasource.put("styleNo", StringUtil.subString(str, "STYLENR", true));
//					}else if (str.startsWith("MAKER")) {
//						continueRead = true;
//					}else if (str.startsWith("CUSTOMER")) {
//						datasource.put("maker", buffer.toString());
//						buffer.setLength(0);
//						buffer.append(str)
//						.append(" ");
//					}else if (str.startsWith("DESCRIPTION")) {
//						datasource.put("customer", buffer.toString());
//						buffer.setLength(0);
//						continueRead = false;
//					}else if (str.startsWith("USD")) {
//						datasource.put("price", StringUtil.subString(str, "USD", true));
//					}else if (continueRead) {
//						buffer.append(str)
//						.append(" ");
//					}
//					//order 部分读取结束，开始读取item
//					else if(str.startsWith("PRODUCTION ORDER")){
//						itemBegin = true;
//					}else if(str.startsWith("Signed by")){
//						itemBegin = false;
//						this.saveOrder(datasource, items, cpqFile);//保存信息
//						datasource.clear();
//						items.clear();
//					}else if(itemBegin){
//						if(str.endsWith("TOTAL PIECES")){
//							String[] colors = str.split(" ");
//							for (String color : colors) {
//								if (StringUtils.isNotEmpty(color)) {
//									int index = color.indexOf("-");
//									if (index != -1) {
//										Map<String, String> item = new HashMap<String, String>();
//										item.put("color", color.trim().toLowerCase().substring(0, index));
//										items.add(item);
//									}
//								}
//							}
//						}else if (str.startsWith("Size S")) {
//							String[] sizeS = StringUtil.subString(str, "Size S", true).split(" ");
//							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
//								String size = sizeS[i];
//								if (StringUtils.isNotEmpty(size)) {
//									Map<String, String> item = items.get(i);
//									item.put("sizeS", size);
//								}
//							}
//						}else if (str.startsWith("Size M")) {
//							String[] sizeS = StringUtil.subString(str, "Size M", true).split(" ");
//							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
//								String size = sizeS[i];
//								if (StringUtils.isNotEmpty(size)) {
//									Map<String, String> item = items.get(i);
//									item.put("sizeM", size);
//								}
//							}
//						}else if (str.startsWith("Size L")) {
//							String[] sizeS = StringUtil.subString(str, "Size L", true).split(" ");
//							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
//								String size = sizeS[i];
//								if (StringUtils.isNotEmpty(size)) {
//									Map<String, String> item = items.get(i);
//									item.put("sizeL", size);
//								}
//							}
//						}else if (str.startsWith("Size XL")) {
//							String[] sizeS = StringUtil.subString(str, "Size XL", true).split(" ");
//							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
//								String size = sizeS[i];
//								if (StringUtils.isNotEmpty(size)) {
//									Map<String, String> item = items.get(i);
//									item.put("sizeXL", size);
//								}
//							}
//						}else if (str.startsWith("Size XXL")) {
//							String[] sizeS = StringUtil.subString(str, "Size XXL", true).split(" ");
//							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
//								String size = sizeS[i];
//								if (StringUtils.isNotEmpty(size)) {
//									Map<String, String> item = items.get(i);
//									item.put("sizeXXL", size);
//								}
//							}
//						}
//					}
//				}
//			} catch (IOException e) {
//				this.logger.error("pdf parse exception.", e);
//			}
//		}
//		this.logger.info("********** readPdf end ********");
//	}
//	
//	/**
//	 * 保存order以及item信息，重复时更新
//	 * @param datasource
//	 */
//	private void saveOrder(Map<String, String> datasource, List<Map<String, String>> items,CpqFile cpqFile){
//		CpqOrder order = this.cpqOrderLogic.findByOrderNoAndStyleNo(datasource.get("orderNo"), datasource.get("styleNo"));
//		if (null == order) {
//			order = new CpqOrder();
//		}
//		order.setPdfId(cpqFile.getId());
//		order.setOrderNo(datasource.get("orderNo"));
//		order.setStyleNo(datasource.get("styleNo"));
//		order.setMaker(datasource.get("maker"));
//		order.setCustomer(datasource.get("customer"));
//		order.setPrice(datasource.get("price").replace(",", ""));
//		this.logger.debug("********* order:", order.toString());
//		order = this.cpqOrderLogic.save(order);
//		
//		for (Map<String, String> item : items) {
//			String color = item.get("color");
//			CpqOrderItem orderItem = this.cpqOrderItemLogic.findByOrderIdAndColor(order.getId(), color);
//			if (null == orderItem) {
//				orderItem = new CpqOrderItem();
//			}
//			if (!this.existsColours.contains(color)) {
//				errorMap.put("Missing configuration of color.", "color:" + color + "缺少配置");
//				return ;
//			}
//			orderItem.setColor(color);
//			if (StringUtils.isNotEmpty(item.get("sizeS"))) {
//				orderItem.setSizeS(Integer.parseInt(item.get("sizeS")));
//			}
//			if (StringUtils.isNotEmpty(item.get("sizeM"))) {
//				orderItem.setSizeM(Integer.parseInt(item.get("sizeM")));
//			}
//			if (StringUtils.isNotEmpty(item.get("sizeL"))) {
//				orderItem.setSizeL(Integer.parseInt(item.get("sizeL")));
//			}
//			if (StringUtils.isNotEmpty(item.get("sizeXL"))) {
//				orderItem.setSizeXl(Integer.parseInt(item.get("sizeXL")));
//			}
//			if (StringUtils.isNotEmpty(item.get("sizeXXL"))) {
//				orderItem.setSizeXxl(Integer.parseInt(item.get("sizeXXL")));
//			}
//			this.logger.debug("********* orderItem:", orderItem.toString());
//			orderItem.setOrderId(order.getId());
//			this.cpqOrderItemLogic.save(orderItem);
//		}
//	}
//	
//	/**
//	 * 计算size数组与color数组最小值，处理空size
//	 * @param items
//	 * @param size
//	 * @return
//	 */
//	private int getMinSize(List<Map<String, String>> items, String[] size) {
//		if (null == items || null == size) {
//			return 0;
//		}
//		return (items.size() > size.length ? size.length : items.size()) ;
//	}
//	
//	private void initSuppportedColurs(){
//		if (null == this.existsColours) {
//			existsColours = new HashSet<String>();
//		}
//		List<CpqDictionary> exsits = this.cpqDictionaryLogic.findByType(CpqDictionaryType.COLOR);
//		for (CpqDictionary exsit : exsits) {
//			existsColours.add(exsit.getProperty());
//		}
//	}
//
//	@Override
//	public void readExcels(List<String> filePaths, CpqExcelType template) {
//		for (String filePath : filePaths) {
//			FileInputStream is = null;
//			Workbook workbook = null;
//			try {
//				is = new FileInputStream(filePath);
//				if (filePath.endsWith(".xls")) {
//					workbook = new HSSFWorkbook(is);
//				}else if (filePath.endsWith(".xlsx")) {
//					//TODO 07
//				}else {
//					logger.info("not support file:{}", filePath);
//					return;
//				}
//				switch (template) {
//				case JIANAN:
//					this.readJiananExcel(workbook);
//					break;
//				case PUTIANMU:
//					break;
//				default:
//					break;
//				}
//			} catch (Exception e) {
//				this.logger.error("{}",e);
//			}finally{
//				if (null != workbook) {
//					try {
//						workbook.close();
//					} catch (IOException e) {
//						this.logger.error("{}",e);
//					}
//				}
//				if (null != is) {
//					try {
//						is.close();
//					} catch (IOException e) {
//						this.logger.error("{}",e);
//					}
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 佳楠长excel解析
//	 * @param workbook
//	 */
//	private void readJiananExcel(Workbook workbook) {
//		IpmConfig jiananConfig = this.configLogic.findByProperty(IpmConfigConstant.CPQ_EXCEL_JIANAN);
//		if (null == jiananConfig) {
//			logger.info("Missing 佳楠 normal configuration.");
//		}
//		Sheet helanSheet = workbook.getSheet("出荷兰");
//		Sheet hkSheet = workbook.getSheet("香港");
//		Sheet sheet01 = workbook.getSheet("01");
//		if (null == helanSheet) {
//			logger.info("sheet:{} not exist.","出荷兰");
//		}
//		if (null == hkSheet) {
//			logger.info("sheet:{} not exist.","香港");
//		}
//		if (null == sheet01) {
//			logger.info("sheet:{} not exist.","01");
//		}
//	}
//	private void readJiananExcelSheet(Sheet... sheets) {
//		for (Sheet sheet : sheets) {
//			if (null != sheet) {
//				int rows = sheet.getPhysicalNumberOfRows();
//				
//			}
//		}
//	}
//
//	@Override
//	public void printOrder(String template) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Resource
//	private CpqFileLogic cpqFileLogic;
//	@Override
//	public CpqFile saveFileBizValue(Attachment attachment,String clothingType) {
//		CpqFile file = new CpqFile();
//		file.setFileName(attachment.getFileName());
//		file.setType(CpqFileType.getType(attachment.getFileName()));
//		file.setAttachRefId(attachment.getId());
//		file.setDeleted(Boolean.FALSE);
//		file.setUploadDate(attachment.getUploadDate());
//		return this.cpqFileLogic.save(file);
//	}
//
//	@Override
//	public List<PdfVo> getPdfItems(Long pdfId) {
//		List<PdfVo> vos = new ArrayList<PdfVo>();
//		List<CpqOrder> orders = this.cpqOrderLogic.findByPdfId(pdfId);
//		for (CpqOrder order : orders) {
//			List<CpqOrderItem> orderItems = this.cpqOrderItemLogic.findByOrderId(order.getId());
//			for (CpqOrderItem orderItem : orderItems) {
//				vos.add(this.convert2Vo(order, orderItem));
//			}
//		}
//		return vos;
//	}
//
//	private PdfVo convert2Vo(CpqOrder order,CpqOrderItem orderItem) {
//		PdfVo vo = new PdfVo();
//		vo.setOrder(order.getOrderNo());
//		vo.setStyle(order.getStyleNo());
//		vo.setColour(orderItem.getColor());
//		vo.setSizeL(orderItem.getSizeL());
//		vo.setSizeM(orderItem.getSizeM());
//		vo.setSizeS(orderItem.getSizeS());
//		vo.setSizeXL(orderItem.getSizeXl());
//		vo.setSizeXXL(orderItem.getSizeXxl());
//		Integer total = this.calculateTotal(orderItem.getSizeL(),orderItem.getSizeM(),orderItem.getSizeS(),orderItem.getSizeXl(),orderItem.getSizeXxl());
//		vo.setTTL(total);
//		if (StringUtils.isNotEmpty(order.getPrice())) {
//			vo.setTotalAmount(total * Integer.parseInt(order.getPrice()));
//		}
//		return vo;
//	}
//	
//	private Integer calculateTotal(Integer... sizes){
//		Integer total = 0;
//		for (Integer size : sizes) {
//			if (null != size) {
//				total += size;
//			}
//		}
//		return total;
//	}
//}
