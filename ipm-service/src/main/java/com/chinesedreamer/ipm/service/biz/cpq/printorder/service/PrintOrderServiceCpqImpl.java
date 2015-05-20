package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.common.utils.format.StringUtil;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic.CpqOrderItemLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic.CpqOrderLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
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

	@Override
	public void readPdf(String filePath) {
		PdfReader pdfReader = this.pdfFactory.getPdfReader(PdfReaderType.BOXPDF);
		IpmPdf pdf = pdfReader.read(filePath);
		String content = pdf.getContent();
		if (StringUtils.isNotEmpty(content)) {
			
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
						buffer.append(str)
						.append(" ");
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
								if (!color.equals("TOTAL")
										&& !color.equals("PIECES")
										&& StringUtils.isNotEmpty(color)) {
									Map<String, String> item = new HashMap<String, String>();
									item.put("color", color.trim().toLowerCase());
									items.add(item);
								}
							}
						}else if (str.startsWith("Size S")) {
							String[] sizeS = StringUtil.subString(str, "Size S", true).split(" ");
							for (int i = 0; i < sizeS.length; i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeS", size);
								}
							}
						}else if (str.startsWith("Size M")) {
							String[] sizeS = StringUtil.subString(str, "Size M", true).split(" ");
							for (int i = 0; i < sizeS.length; i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeM", size);
								}
							}
						}else if (str.startsWith("Size L")) {
							String[] sizeS = StringUtil.subString(str, "Size L", true).split(" ");
							for (int i = 0; i < sizeS.length; i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeL", size);
								}
							}
						}else if (str.startsWith("Size XL")) {
							String[] sizeS = StringUtil.subString(str, "Size XL", true).split(" ");
							for (int i = 0; i < sizeS.length; i++) {
								String size = sizeS[i];
								if (StringUtils.isNotEmpty(size)) {
									Map<String, String> item = items.get(i);
									item.put("sizeXL", size);
								}
							}
						}else if (str.startsWith("Size XXL")) {
							String[] sizeS = StringUtil.subString(str, "Size XXL", true).split(" ");
							for (int i = 0; i < sizeS.length; i++) {
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
		order.setPrice(datasource.get("price"));
		order = this.cpqOrderLogic.save(order);
		
		for (Map<String, String> item : items) {
			String color = item.get("color");
			CpqOrderItem orderItem = this.cpqOrderItemLogic.findByOrderIdAndColor(order.getId(), color);
			if (null == orderItem) {
				orderItem = new CpqOrderItem();
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
			this.cpqOrderItemLogic.save(orderItem);
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

}
