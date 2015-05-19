package com.chinesedreamer.ipm.service.biz.printorder.service;

import com.chinesedreamer.ipm.service.biz.printorder.constant.PrintOrderType;

public interface PrintOrderFacotry {
	public PrintOrderService getService(PrintOrderType type);
}
