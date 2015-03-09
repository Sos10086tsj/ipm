package com.chinesedreamer.ipm.web.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 
 * @author Paris
 * @date Dec 29, 20143:58:12 PM
 * @version beta
 */
@Controller
@RequestMapping(value = "demo")
public class DemoController {
	public String kickStartDemo(Model model){
		return "demo/kickstart";
	}
}
