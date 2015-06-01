package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.PrintOrderType;

public interface PrintOrderFacotry {
	public PrintOrderService getService(PrintOrderType type);
}
