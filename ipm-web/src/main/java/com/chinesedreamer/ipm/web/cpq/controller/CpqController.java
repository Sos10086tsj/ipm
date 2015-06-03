package com.chinesedreamer.ipm.web.cpq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.PrintOrderType;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.service.PrintOrderFacotry;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.service.PrintOrderService;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfSelectVo;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.vo.PdfVo;
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
	public List<PdfSelectVo> getUploadedPdfStore(){
		return this.facotry.getService(PrintOrderType.CPQ).getUploadedPdfStore();
	}
	
	/**
	 * 逐行更改pdf数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updatePdfRow", method = RequestMethod.POST)
	public ResponseVo updatePdfRow(HttpServletRequest request){
		String order = request.getParameter("order").trim();
		String style = request.getParameter("style").trim();
		String colour = request.getParameter("colour").trim();
		String sizeS = request.getParameter("sizeS").trim();
		String sizeM = request.getParameter("sizeM").trim();
		String sizeL = request.getParameter("sizeL").trim();
		String sizeXL = request.getParameter("sizeXL").trim();
		String sizeXXL = request.getParameter("sizeXXL").trim();
		System.out.println("params:");
		System.out.println("order:" + order);
		System.out.println("style:" + style);
		System.out.println("colour:" + colour);
		System.out.println("sizeS:" + sizeS);
		System.out.println("sizeM:" + sizeM);
		System.out.println("sizeL:" + sizeL);
		System.out.println("sizeXL:" + sizeXL);
		System.out.println("sizeXXL:" + sizeXXL);
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		return vo;
	}
	
	/**
	 * 获取excel order 信息
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getExcelStore", method = RequestMethod.GET)
	public List<ExcelVo> getExcelStore(Model model){
		return ExcelVo.localInstance();
	}
	
	/**
	 * 更新
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateExcelRow", method = RequestMethod.POST)
	public ResponseVo updateExcelRow(HttpServletRequest request){
		String order = request.getParameter("order").trim();
		String style = request.getParameter("style").trim();
		String from = request.getParameter("from").trim();
		String to = request.getParameter("to").trim();
		String colour = request.getParameter("colour").trim();
		String sizeS = request.getParameter("sizeS").trim();
		String sizeM = request.getParameter("sizeM").trim();
		String sizeL = request.getParameter("sizeL").trim();
		String sizeXL = request.getParameter("sizeXL").trim();
		String sizeXXL = request.getParameter("sizeXXL").trim();
		String box = request.getParameter("box").trim();
		String qty = request.getParameter("qty").trim();
		String grossWeight = request.getParameter("grossWeight").trim();
		String netWeight = request.getParameter("netWeight").trim();
		System.out.println("params:");
		System.out.println("order:" + order);
		System.out.println("from:" + from);
		System.out.println("to:" + to);
		System.out.println("style:" + style);
		System.out.println("colour:" + colour);
		System.out.println("sizeS:" + sizeS);
		System.out.println("sizeM:" + sizeM);
		System.out.println("sizeL:" + sizeL);
		System.out.println("sizeXL:" + sizeXL);
		System.out.println("sizeXXL:" + sizeXXL);
		System.out.println("box:" + box);
		System.out.println("qty:" + qty);
		System.out.println("grossWeight:" + grossWeight);
		System.out.println("netWeight:" + netWeight);
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		return vo;
	}

	/*****************************/
	
	@RequestMapping(value = "uploadPdf",method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo uploadPdf(Model model,HttpServletRequest request,
			@RequestParam(value = "pdf", required = true)MultipartFile pdf){
		//1. 保存附件
		Attachment attachment = this.attachmentService.save(pdf, UserConstant.NO_USER_ID);
		PrintOrderService service = this.facotry.getService(PrintOrderType.CPQ);
		//2. 保存pdf业务对象
		String clothingType = request.getParameter("clothingType").trim();
		CpqFile cpqFile = service.saveFileBizValue(attachment,clothingType);
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
}
