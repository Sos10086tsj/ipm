package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;

import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;

/**
 * Description: 
 * @author Paris
 * @date Jun 19, 20159:45:10 AM
 * @version beta
 */
public interface CpqExcelReadService {
	/**
	 * 读取佳楠01表数据
	 * @param cpqFile
	 * @param sheets
	 * @return
	 */
	public Set<CpqManufacotryOrderItem> readJianan01ExcelSheet(CpqFile cpqFile, Sheet... sheets) ;
}
