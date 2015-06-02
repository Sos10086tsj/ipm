package com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;

public interface CpqOrderItemLogic extends IpmLogic<CpqOrderItem, Long>{
	public CpqOrderItem findByOrderIdAndColor(Long orderId,String color);
	
	/**
	 * 查找order对应的items
	 * @param orderId
	 * @return
	 */
	public List<CpqOrderItem> findByOrderId(Long orderId);
}
