package com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.repository.CpqOrderRepository;

@Service
public class CpqOrderLogicImpl extends IpmLogicImpl<CpqOrder, Long> implements CpqOrderLogic{
	@Resource
	private CpqOrderRepository repository;
	@Override
	public CpqOrder findByOrderNoAndStyleNo(String orderNo, String styleNo) {
		return this.repository.findByOrderNoAndStyleNo(orderNo, styleNo);
	}

}
