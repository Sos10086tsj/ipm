package com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
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
	@Override
	public List<CpqOrder> findByPdfId(Long pdfId) {
		Sort sort =  new Sort(Sort.Direction.ASC, "orderNo","styleNo");
		return this.repository.findByPdfId(pdfId, sort);
	}
	@Override
	public List<String> findOrders(List<String> orderTypes) {
		return this.repository.findByOrderNoTypeInOrderByOrderNoAsc(orderTypes);
	}
	@Override
	public List<String> findOrdersByOrderNoLike(String orderNo,List<String> orderTypes) {
		return this.repository.findByOrderNoLikeAndOrderNoTypeInOrderByOrderNoAsc(orderNo, orderTypes);
	}
	@Override
	public List<CpqOrder> findByOrderNo(String orderNo) {
		return this.repository.findByOrderNo(orderNo);
	}
	@Override
	public List<CpqOrder> findByOrderNoLike(String orderNo) {
		return this.repository.findByOrderNoLike("%" + orderNo + "%");
	}


}
