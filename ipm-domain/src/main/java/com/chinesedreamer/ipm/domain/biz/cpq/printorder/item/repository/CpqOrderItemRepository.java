package com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.repository;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;

public interface CpqOrderItemRepository extends IpmRepository<CpqOrderItem, Long>{
	public CpqOrderItem findByOrderIdAndColor(Long orderId,String color);
}
