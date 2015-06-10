package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.repository;

import java.util.List;

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
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(String orderNo,String styleNo,String color, Integer fromNo, Integer toNo, String owner);

	public List<CpqManufacotryOrderItem> findByExcelIdAndOwner(Long excelId, String owner);
	
	public List<CpqManufacotryOrderItem> findByOrderNoAndOwnerOrderByFromNoAsc(String orderNo,String owner);
}
