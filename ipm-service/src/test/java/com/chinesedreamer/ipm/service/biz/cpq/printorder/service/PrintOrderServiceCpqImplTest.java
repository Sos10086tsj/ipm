package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.chinesedreamer.ipm.common.base.SpringTest;
import com.chinesedreamer.ipm.service.biz.cpq.printorder.constant.PrintOrderType;

public class PrintOrderServiceCpqImplTest extends SpringTest{
	@Resource
	private PrintOrderFacotry factory;
	@Test
	@Rollback(false)
	public void testReadPdf() {
		this.factory.getService(PrintOrderType.CPQ).readPdf("F:/downloads/15年上半年大季箱单/出货计划总表�NGB 1504 PO.pdf");
	}

}
