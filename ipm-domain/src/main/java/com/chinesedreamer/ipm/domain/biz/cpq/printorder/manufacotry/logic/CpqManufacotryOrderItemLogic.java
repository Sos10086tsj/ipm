package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.logic;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;

/**
 * Description: 
 * @author Paris
 * @date Jun 4, 20159:11:07 AM
 * @version beta
 */
public interface CpqManufacotryOrderItemLogic extends IpmLogic<CpqManufacotryOrderItem, Long>{
	public CpqManufacotryOrderItem findByOrderNoAndStyleNo(String orderNo,String styleNo);
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNo(String orderNo,String styleNo,String color, String fromNo, String toNo);
}
