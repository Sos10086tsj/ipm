package com.chinesedreamer.ipm.domain.biz.cpq.printorder.repository;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

public interface CpqOrderRepository extends IpmRepository<CpqOrder, Long>{
	public CpqOrder findByOrderNoAndStyleNo(String orderNo, String styleNo);
	
	public List<CpqOrder> findByOrderNoLikeOrderByOrderNoASCStyleNoASC(String orderNo);
	public List<CpqOrder> findByStyleNoLikeOrderByOrderNoASCStyleNoASC(String styleNo);
	public List<CpqOrder> findByOrderNoLikeAndStyleNoLikeOrderByOrderNoASCStyleNoASC(String orderNo, String styleNo);
	public List<CpqOrder> findAllOrderByOrderNoASCStyleNoASC();
}
