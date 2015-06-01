package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.format.StringUtil;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic.CpqDictionaryLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic.CpqOrderItemLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic.CpqOrderLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
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
	
	private Map<String, String> errorMap = new HashMap<String, String>();
	
	private Set<String> existsColours;

	@Override
	public void readPdf(String filePath) {
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
						this.saveOrder(datasource, items);//保存信息
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
						}else if (str.startsWith("Size S")) {
							String[] sizeS = StringUtil.subString(str, "Size S", true).split(" ");
							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeS", size);
								}
							}
						}else if (str.startsWith("Size M")) {
							String[] sizeS = StringUtil.subString(str, "Size M", true).split(" ");
							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeM", size);
								}
							}
						}else if (str.startsWith("Size L")) {
							String[] sizeS = StringUtil.subString(str, "Size L", true).split(" ");
							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeL", size);
								}
							}
						}else if (str.startsWith("Size XL")) {
							String[] sizeS = StringUtil.subString(str, "Size XL", true).split(" ");
							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeXL", size);
								}
							}
						}else if (str.startsWith("Size XXL")) {
							String[] sizeS = StringUtil.subString(str, "Size XXL", true).split(" ");
							for (int i = 0; i < this.getMinSize(items, sizeS); i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeXXL", size);
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
	 * 保存order以及item信息，重复时更新
	 * @param datasource
	 */
	private void saveOrder(Map<String, String> datasource, List<Map<String, String>> items){
		CpqOrder order = this.cpqOrderLogic.findByOrderNoAndStyleNo(datasource.get("orderNo"), datasource.get("styleNo"));
		if (null == order) {
			order = new CpqOrder();
		}
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
			if (StringUtils.isNotEmpty(item.get("sizeS"))) {
				orderItem.setSizeS(Integer.parseInt(item.get("sizeS")));
			}
			if (StringUtils.isNotEmpty(item.get("sizeM"))) {
				orderItem.setSizeM(Integer.parseInt(item.get("sizeM")));
			}
			if (StringUtils.isNotEmpty(item.get("sizeL"))) {
				orderItem.setSizeL(Integer.parseInt(item.get("sizeL")));
			}
			if (StringUtils.isNotEmpty(item.get("sizeXL"))) {
				orderItem.setSizeXl(Integer.parseInt(item.get("sizeXL")));
			}
			if (StringUtils.isNotEmpty(item.get("sizeXXL"))) {
				orderItem.setSizeXxl(Integer.parseInt(item.get("sizeXXL")));
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
	public void readExcels(List<String> filePaths) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printOrder(String template) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PdfVo> getPdf(String orderNo, String styleNo) {
		List<CpqOrder> orders = null;
		if (StringUtils.isNotEmpty(orderNo) && StringUtils.isNotEmpty(styleNo)) {
			orders = this.cpqOrderLogic.findByOrderNoLikeAndStyleNoLike(orderNo, styleNo);
		}else if (StringUtils.isEmpty(orderNo) && StringUtils.isNotEmpty(styleNo)) {
			orders = this.cpqOrderLogic.findByStyleNoLike(styleNo);
		}else if (StringUtils.isNotEmpty(orderNo) && StringUtils.isEmpty(styleNo)) {
			orders = this.cpqOrderLogic.findByOrderNoLike(orderNo);
		}else {
			orders = this.cpqOrderLogic.findAll();
		}
		for (CpqOrder cpqOrder : orders) {
			
		}
		return null;
	}

}
