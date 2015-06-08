package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.util.List;

import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.CpqExcelType;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.ExcelVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.FileSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.SelectVo;

/**
 * 打单service，目前只有曹佩琴使用
 * @author Paris
 *
 */
public interface PrintOrderService {
	
	
	/**
	 * 保存文件信息
	 * @param attachment
	 * @param clothingType 衣服类型
	 * @return
	 */
	public CpqFile saveFileBizValue(Attachment attachment,String clothingType, String owner);
	
	/**
	 * 读取pdf客户文件
	 * @param filePath
	 */
	public void readPdf(String filePath,CpqFile cpqFile);
	
	/**
	 * 读取指定pdf文件的item信息
	 * @param pdfReferenceId
	 * @return
	 */
	public List<PdfVo> getPdfItems(Long pdfId);
	
	/**
	 * 读取服装类型
	 * @return
	 */
	public List<SelectVo> getClothingTypes();
	
	/**
	 * 获取已经上传过的pdf，按时间倒叙排序
	 * @return
	 */
	public List<FileSelectVo> getUploadedFileStore(CpqFileType type);
	
	
	/***********excel **************/
	/**
	 * 获取工厂列表
	 * @return
	 */
	public List<SelectVo> getManufactoryStore();
	
	/**
	 * 读取excel工厂文件
	 * @param filePaths
	 */
	public void readExcel(String filePath, CpqExcelType template, CpqFile cpqFile);
	
	/**
	 * 读取指定Excel文件的item信息
	 * @param excelId
	 * @return
	 */
	public List<ExcelVo> getExcelItems(Long excelId);
}
