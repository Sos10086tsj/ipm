package com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

public interface CpqOrderLogic extends IpmLogic<CpqOrder, Long>{
	public CpqOrder findByOrderNoAndStyleNo(String orderNo, String styleNo);
	public List<CpqOrder> findByPdfId(Long pdfId);
	public List<CpqOrder> findByOrderNo(String orderNo);
	public List<String> findOrders(List<String> orderTypes);
	public List<String> findOrdersByOrderNoLike(String orderNo,List<String> orderTypes);
	public List<CpqOrder> findByOrderNoLike(String orderNo);
}
