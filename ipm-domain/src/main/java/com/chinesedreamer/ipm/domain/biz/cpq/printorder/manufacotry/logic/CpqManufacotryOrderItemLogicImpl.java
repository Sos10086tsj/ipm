package com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.model.CpqManufacotryOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.manufacotry.repository.CpqManufacotryOrderItemRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 4, 20159:11:22 AM
 * @version beta
 */
@Service
public class CpqManufacotryOrderItemLogicImpl extends IpmLogicImpl<CpqManufacotryOrderItem, Long> implements CpqManufacotryOrderItemLogic{
	@Resource
	private CpqManufacotryOrderItemRepository repository;
	@Override
	public CpqManufacotryOrderItem findByOrderNoAndStyleNo(String orderNo,String styleNo) {
		return this.repository.findByOrderNoAndStyleNoAndDeletedFalse(orderNo, styleNo);
	}
	@Override
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(String orderNo,String styleNo,String color, Integer fromNo, Integer toNo, String owner) {
		return this.repository.findByOrderNoAndStyleNoAndColorAndFromNoAndToNoAndOwner(orderNo, styleNo, color, fromNo, toNo, owner);
	}
	@Override
	public List<CpqManufacotryOrderItem> getExcelItems(Long excelId,String owner) {
		return this.repository.findByExcelIdAndOwner(excelId, owner);
	}
	@Override
	public List<CpqManufacotryOrderItem> findByOrderNoAndOwner(String orderNo,String owner) {
		return this.repository.findByOrderNoAndOwnerOrderByFromNoAsc(orderNo, owner);
	}

}
