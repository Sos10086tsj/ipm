package com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.model.CpqOrderItem;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.item.repository.CpqOrderItemRepository;

@Service
public class CpqOrderItemLogicImpl extends IpmLogicImpl<CpqOrderItem, Long> implements CpqOrderItemLogic{
	@Resource
	private CpqOrderItemRepository repository;
	@Override
	public CpqOrderItem findByOrderIdAndColor(Long orderId, String color) {
		return this.repository.findByOrderIdAndColor(orderId, color);
	}

}
