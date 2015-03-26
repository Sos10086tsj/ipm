package com.chinesedreamer.ipm.domain.system.company.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.service.impl.IpmServiceImpl;
import com.chinesedreamer.ipm.domain.system.company.info.model.CompanyInfo;
import com.chinesedreamer.ipm.domain.system.company.info.model.vo.CompanyInfoVo;
import com.chinesedreamer.ipm.domain.system.company.info.service.CompanyInfoService;
import com.chinesedreamer.ipm.domain.system.company.model.Company;
import com.chinesedreamer.ipm.domain.system.company.repository.CompanyRepository;

@Service
public class CompanyInfoServiceImpl extends IpmServiceImpl<CompanyInfo, Long> implements CompanyInfoService{
	@Resource
	private CompanyRepository repository;
	
	@Override
	public CompanyInfo createInfo(CompanyInfoVo vo) {
		//1	保存客户
		//1.1	检查code唯一性
		if (null != this.repository.findByCode(vo.getCode())) {
			return null;
		}
		//1.2 	保存客户
		Company saved = this.save(this.generateFromVo(vo));
		//1.3	保存客户信息
		return null;
	}
	
	/**
	 * 根据vo生成客户信息
	 * @param vo
	 * @return
	 */
	private CompanyInfo generateFromVo(CompanyInfoVo vo) {
		CompanyInfoVo info = new CompanyInfoVo();
		info.set
		info.setScale(scale);
		company.setVip(vo.getVip());
		return company;
	}
}
