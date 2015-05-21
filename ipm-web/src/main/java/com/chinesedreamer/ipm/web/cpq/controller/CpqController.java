package com.chinesedreamer.ipm.web.cpq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 
 * @author Paris
 * @date May 21, 20151:58:34 PM
 * @version beta
 */
@Controller
@RequestMapping(value = "cpq")
public class CpqController {
	@RequestMapping(value = "pdf", method = RequestMethod.GET)
	public String showPdf(Model model){
		return "cpq/pdf";
	}
}
