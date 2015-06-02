package com.chinesedreamer.ipm.domain.biz.cpq.printorder.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

public interface CpqOrderRepository extends IpmRepository<CpqOrder, Long>{
	public CpqOrder findByOrderNoAndStyleNo(String orderNo, String styleNo);
	
	public List<CpqOrder> findByPdfId(Long pdfId, Sort sort);
}
