package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.logic;

import java.util.List;

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
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(String orderNo,String styleNo,String color, Integer fromNo, Integer toNo, String owner);
	public List<CpqManufacotryOrderItem> getExcelItems(Long excelId,String owner);
	public List<CpqManufacotryOrderItem> findByOrderNoAndOwner(String orderNo, String owner);
}
