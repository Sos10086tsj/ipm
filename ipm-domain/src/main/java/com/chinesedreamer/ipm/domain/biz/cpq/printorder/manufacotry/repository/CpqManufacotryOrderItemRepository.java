package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.repository;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;

/**
 * Description: 
 * @author Paris
 * @date Jun 4, 20159:10:38 AM
 * @version beta
 */
public interface CpqManufacotryOrderItemRepository extends IpmRepository<CpqManufacotryOrderItem, Long>{
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndDeletedFalse(String orderNo,String styleNo);
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNo(String orderNo,String styleNo,String color, String fromNo, String toNo);
}
