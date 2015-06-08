package com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

public interface CpqOrderLogic extends IpmLogic<CpqOrder, Long>{
	public CpqOrder findByOrderNoAndStyleNo(String orderNo, String styleNo);
	public List<CpqOrder> findByPdfId(Long pdfId);
	public List<String> findOrders();
	public List<String> findOrdersByOrderNoLike(String orderNo);
}
