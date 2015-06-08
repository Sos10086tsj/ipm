package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNo(String orderNo,String styleNo,String color, Integer fromNo, Integer toNo);
	
	@Query(" FROM CpqManufacotryOrderItem item WHERE item.excelId = :excelId AND item.deleted = false ORDER BY item.orderNo ,item.fromNo, item.toNo ASC ")
	public List<CpqManufacotryOrderItem> getExcelItems(@Param("excelId")Long excelId);
	
	public List<CpqManufacotryOrderItem> findByExcelIdAndDeletedFalse(Long excelId);
}
