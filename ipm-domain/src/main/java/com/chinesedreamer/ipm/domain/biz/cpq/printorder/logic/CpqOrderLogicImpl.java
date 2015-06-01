package com.chinesedreamer.ipm.domain.biz.cpq.printorder.logic;

import java.util.List;

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
	@Override
	public List<CpqOrder> findByOrderNoLike(String orderNo) {
		return this.repository.findByOrderNoLikeOrderByOrderNoASCStyleNoASC(orderNo);
	}
	@Override
	public List<CpqOrder> findByStyleNoLike(String styleNo) {
		return this.repository.findByStyleNoLikeOrderByOrderNoASCStyleNoASC(styleNo);
	}
	@Override
	public List<CpqOrder> findByOrderNoLikeAndStyleNoLike(String orderNo,
			String styleNo) {
		return this.repository.findByOrderNoLikeAndStyleNoLikeOrderByOrderNoASCStyleNoASC(orderNo, styleNo);
	}
	@Override
	public List<CpqOrder> findAll() {
		return this.repository.findAllOrderByOrderNoASCStyleNoASC();
	}

}
