package com.chinesedreamer.ipm.domain.biz.cpq.printorder.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

public interface CpqOrderRepository extends IpmRepository<CpqOrder, Long>{
	public CpqOrder findByOrderNoAndStyleNo(String orderNo, String styleNo);
	
	public List<CpqOrder> findByPdfId(Long pdfId, Sort sort);
	
	@Query("SELECT distinct co.orderNo FROM CpqOrder co WHERE 1 = 1 AND co.orderNoType in :orderTypes ORDER BY co.orderNo ASC ")
	public List<String> findByOrderNoTypeInOrderByOrderNoAsc(@Param("orderTypes")List<String> orderTypes);
	
	@Query("SELECT distinct co.orderNo FROM CpqOrder co WHERE co.orderNo like :orderNo AND co.orderNoType in :orderTypes ORDER BY co.orderNo ASC ")
	public List<String> findByOrderNoLikeAndOrderNoTypeInOrderByOrderNoAsc(@Param("orderNo")String orderNo,@Param("orderTypes")List<String> orderTypes);
}
