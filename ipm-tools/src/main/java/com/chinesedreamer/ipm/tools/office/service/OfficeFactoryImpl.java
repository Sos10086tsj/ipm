package com.chinesedreamer.ipm.tools.office.service;

import javax.annotation.Resource;

import com.chinesedreamer.ipm.tools.office.constant.OfficeType;

/**
 * Description: 
 * @author Paris
 * @date May 22, 20159:19:56 AM
 * @version beta
 */
public class OfficeFactoryImpl implements OfficeFactory{
	@Resource(name = "cpqOfficeService")
	private OfficeService cpqOfficeService;
	@Override
	public OfficeService getService(OfficeType type) {
		OfficeService os = null;
		switch (type) {
		case CPQ:
			os = this.cpqOfficeService;
			break;
		default:
			os = this.cpqOfficeService;
			break;
		}
		return os;
	}

}
