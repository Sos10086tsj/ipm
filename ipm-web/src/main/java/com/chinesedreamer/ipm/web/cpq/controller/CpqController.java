package com.chinesedreamer.ipm.web.cpq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	/**
	 * 跳转pdf order信息展示页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pdf", method = RequestMethod.GET)
	public String showPdf(Model model){
		return "cpq/pdf";
	}
	
	/**
	 * 获取pdf order 信息列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getPdfStore", method = RequestMethod.GET)
	public List<PdfVo> getPdfStore(Model model){
		return PdfVo.localInstance();
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
	
	@RequestMapping(value = "uploadPdf",method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo uploadPdf(Model model,HttpServletRequest request,
			@RequestParam(value = "pdf", required = true)MultipartFile pdf){
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		return vo;
	}
}
