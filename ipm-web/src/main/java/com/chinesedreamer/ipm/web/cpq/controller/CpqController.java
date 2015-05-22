package com.chinesedreamer.ipm.web.cpq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		System.out.println("updatePdfRow");
		ResponseVo vo = new ResponseVo();
		return vo;
	}
}
