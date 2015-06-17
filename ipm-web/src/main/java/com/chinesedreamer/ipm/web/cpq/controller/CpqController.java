package com.chinesedreamer.ipm.web.cpq.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.ipm.common.component.DefaultDownloadComponent;
import com.chinesedreamer.ipm.common.component.DownloadComponent;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.CpqExcelType;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.PrintOrderType;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.service.PrintOrderFacotry;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.service.PrintOrderService;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.ExcelVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.FileSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.RptOrderSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.SelectVo;
import com.chinesedreamer.ipm.service.supp.attachment.service.AttachmentService;
import com.chinesedreamer.ipm.service.system.user.constant.UserConstant;
import com.chinesedreamer.ipm.web.vo.ResponseVo;

/**
 * Description: 
 * @author Paris
 * @date May 21, 20151:58:34 PM
 * @version beta
 */
@Controller
@RequestMapping(value = "cpq")
public class CpqController {
	@Resource
	private PrintOrderFacotry facotry;
	@Resource
	private AttachmentService attachmentService;
	
	/**
	 * 跳转pdf order信息展示�
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pdf", method = RequestMethod.GET)
	public String showPdf(Model model){	
		return "/cpq/pdf";
	}
	
	/**
	 * 获取pdf order 信息列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getPdfStore/{pdfId}", method = RequestMethod.GET)
	public List<PdfVo> getPdfStore(Model model,@PathVariable Long pdfId){
		return this.facotry.getService(PrintOrderType.CPQ).getPdfItems(pdfId);
	}
	
	/**
	 * 获取服装类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getClothingTypeStore", method = RequestMethod.GET)
	public List<SelectVo> getClothingTypeStore(){
		return this.facotry.getService(PrintOrderType.CPQ).getClothingTypes();
	}
	
	/**
	 * 获取已经上传过的pdf，按时间倒叙排序
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUploadedPdfStore", method = RequestMethod.GET)
	public List<FileSelectVo> getUploadedPdfStore(){
		return this.facotry.getService(PrintOrderType.CPQ).getUploadedFileStore(CpqFileType.PDF, null);
	}
	
	/**
	 * 逐行更改pdf数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updatePdfRow", method = RequestMethod.POST)
	public ResponseVo updatePdfRow(HttpServletRequest request){
		//TODO 
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		return vo;
	}
	
	/**
	 * 上传pdf文件
	 * @param model
	 * @param request
	 * @param pdf
	 * @return
	 */
	@RequestMapping(value = "uploadPdf",method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo uploadPdf(Model model,HttpServletRequest request,
			@RequestParam(value = "pdf", required = true)MultipartFile pdf){
		//1. 保存附件
		Attachment attachment = this.attachmentService.save(pdf, UserConstant.NO_USER_ID);
		PrintOrderService service = this.facotry.getService(PrintOrderType.CPQ);
		//2. 保存pdf业务对象
		String clothingType = request.getParameter("clothingType").trim();
		CpqFile cpqFile = service.saveFileBizValue(attachment,clothingType,"CUSTOMER");
		//3. 解析pdf
		service.readPdf(attachment.getFilePath(),cpqFile);
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		Map<String, String> rst = new HashMap<String, String>();
		rst.put("pdfId", cpqFile.getId().toString());
		rst.put("clothingType", cpqFile.getClothingType().toString());
		vo.setData(rst);
		return vo;
	}
	
	/******************** excel 部分 *******************/
	@RequestMapping(value = "excel", method = RequestMethod.GET)
	public String showExcel(Model model){	
		return "/cpq/excel";
	}
	
	
	/**
	 * 获取工厂列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "excel/getManufactoryStore", method = RequestMethod.GET)
	public List<SelectVo> getManufactoryStore(){
		return this.facotry.getService(PrintOrderType.CPQ).getManufactoryStore();
	}
	
	/**
	 * 获取已经上传过的Excel，按时间倒叙排序
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUploadedExcelStore", method = RequestMethod.GET)
	public List<FileSelectVo> getUploadedExcelStore(HttpServletRequest request){
		String manufactory = request.getParameter("manufactory");
		return this.facotry.getService(PrintOrderType.CPQ).getUploadedFileStore(CpqFileType.EXCEL, manufactory);
	}
	
	/**
	 * Excel批量上传
	 * @param model
	 * @param request
	 * @param excels
	 * @return
	 */
	@RequestMapping(value = "uploadExcel",method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo uploadExcel(Model model,HttpServletRequest request,
			@RequestParam(value = "excels", required = true)MultipartFile[] excels){
		//1. 保存附件
		String clothingType = request.getParameter("clothingType").trim();
		String manufactory = request.getParameter("manufactory").trim();
		StringBuffer fileIds = new StringBuffer();
		for (MultipartFile excel : excels) {
			Attachment attachment = this.attachmentService.save(excel, UserConstant.NO_USER_ID);
			PrintOrderService service = this.facotry.getService(PrintOrderType.CPQ);
			CpqFile cpqFile = service.saveFileBizValue(attachment,clothingType,manufactory);
			service.readExcel(attachment.getFilePath(), CpqExcelType.valueOf(manufactory), cpqFile);
			fileIds.append(cpqFile.getId())
			.append(",");
		}
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		Map<String, String> rst = new HashMap<String, String>();
		rst.put("excelIds", fileIds.toString().substring(0, fileIds.length() - 1));
		rst.put("clothingType", clothingType);
		vo.setData(rst);
		return vo;
	}
	
	/**
	 * 更新
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateExcelRow", method = RequestMethod.POST)
	public ResponseVo updateExcelRow(HttpServletRequest request){
		//TODO
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		return vo;
	}
	
	/**
	 * 获取excel order 信息列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getExcelItemStore/{excelId}", method = RequestMethod.GET)
	public List<ExcelVo> getExcelItemStore(Model model,@PathVariable Long excelId,HttpServletRequest request){
		String manufactory = request.getParameter("manufactory");
		return this.facotry.getService(PrintOrderType.CPQ).getExcelItems(excelId,manufactory);
	}
	
	/******** 打印报表 ************/
	/**
	 * 进入报表打印菜单
	 * @return
	 */
	@RequestMapping(value = "report", method = RequestMethod.GET)
	public String report(Model model){
		return "/cpq/report";
	}
	/**
	 * 获取order 列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getRptOrders",method = RequestMethod.GET)
	public List<RptOrderSelectVo> getRptOrders(HttpServletRequest request){
		String orderNoKey = request.getParameter("orderNo");
		String orderType = request.getParameter("orderType");
		return this.facotry.getService(PrintOrderType.CPQ).getOrders(orderNoKey,orderType);
	}
	
	/**
	 * 打印
	 * @throws IOException 
	 */
	@RequestMapping(value ="print", method = {RequestMethod.POST,RequestMethod.GET})
	public void print(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String orderNos = request.getParameter("orderNos");
		String manufactory = request.getParameter("manufactory");
		String orderType = request.getParameter("orderType");
		File report = this.facotry.getService(PrintOrderType.CPQ).printExcelReport(orderNos,manufactory,orderType);
		DownloadComponent downloadComponent = new DefaultDownloadComponent();
		downloadComponent.download(request, response, report.getPath());
		FileUtils.deleteQuietly(report);
	}
}
