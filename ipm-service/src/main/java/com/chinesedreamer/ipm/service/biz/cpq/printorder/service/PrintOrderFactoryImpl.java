package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.PrintOrderType;

@Service
public class PrintOrderFactoryImpl implements PrintOrderFacotry{
	
	@Resource(name = "cpqPrintOrderService")
	private PrintOrderService cpqPrintOrderService;

	@Override
	public PrintOrderService getService(PrintOrderType type) {
		PrintOrderService service = null;
		switch (type) {
		case CPQ:
			service = cpqPrintOrderService;
			break;
		default:
			service = cpqPrintOrderService;
			break;
		}
		return service;
	}

}
