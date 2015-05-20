package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.util.List;

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
