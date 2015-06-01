package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.util.List;

import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;

/**
 * 打单service，目前只有曹佩琴使用
 * @author Paris
 *
 */
public interface PrintOrderService {
	/**
	 * 读取pdf客户文件
	 * @param filePath
	 */
	public void readPdf(String filePath);
	
	/**
	 * 查询pdf解析列表
	 * @param orderNo
	 * @param styleNo
	 * @return
	 */
	public List<PdfVo> getPdf(String orderNo, String styleNo);
	
	/**
	 * 读取excel工厂文件
	 * @param filePaths
	 */
	public void readExcels(List<String> filePaths);
	
	/**
	 * 打印订单
	 * @param template
	 */
	public void printOrder(String template);
}
