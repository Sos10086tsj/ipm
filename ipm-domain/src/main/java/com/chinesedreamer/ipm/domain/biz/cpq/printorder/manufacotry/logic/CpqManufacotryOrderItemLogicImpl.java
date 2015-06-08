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
	public CpqManufacotryOrderItem findByOrderNoAndStyleNoAndColorAndFromNoAndToNo(String orderNo,String styleNo,String color, Integer fromNo, Integer toNo) {
		return this.repository.findByOrderNoAndStyleNoAndColorAndFromNoAndToNo(orderNo, styleNo, color, fromNo, toNo);
	}
	@Override
	public List<CpqManufacotryOrderItem> getExcelItems(Long excelId,String excelType) {
		return this.repository.getExcelItems(excelId,excelType);
	}

}
