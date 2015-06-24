package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.io.File;
import java.util.List;

import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.CpqExcelType;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.ExcelVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.FileSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.RptOrderSelectVo;
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
	 * 获取指定order的item信息
	 * @param orderNo
	 * @return
	 */
	public List<PdfVo> getPdfItems(String orderNo);
	
	/**
	 * 读取服装类型
	 * @return
	 */
	public List<SelectVo> getClothingTypes();
	
	/**
	 * 获取已经上传过的pdf，按时间倒叙排序
	 * @return
	 */
	public List<FileSelectVo> getUploadedFileStore(CpqFileType type, String manufactory);
	
	
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
	public List<ExcelVo> getExcelItems(Long excelId, String excelType);
	
	/*********** 报表打印 **************/
	public List<RptOrderSelectVo> getOrders(String key,String orderType);
	
	public File printExcelReport(String orderNos,String manufactory,String orderType);
	
	/**
	 * 读取指定的color列表
	 * @param colorType
	 * @return
	 */
	public List<String> getOrderTypes(String colorType);
	
	public List<FileSelectVo> getPdfOrders(String orderNo);
	
	public void updatePdfRow(String order,String style,String colour,String sizeS,String sizeM,String sizeL,
			String sizeXl,String sizeXxl,String sizeP,String size1,String size2,String size3,String size4,
			String size6,String size8,String size10,String size12,String size14,String size16);
}
