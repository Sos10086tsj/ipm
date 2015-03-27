package com.chinesedreamer.ipm.service.system.company.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.service.impl.IpmServiceImpl;
import com.chinesedreamer.ipm.domain.system.company.info.logic.CompanyInfoLogic;
import com.chinesedreamer.ipm.domain.system.company.info.model.CompanyInfo;
import com.chinesedreamer.ipm.domain.system.company.logic.CompanyLogic;
import com.chinesedreamer.ipm.domain.system.company.model.Company;
import com.chinesedreamer.ipm.domain.system.company.model.CompanyStatus;
import com.chinesedreamer.ipm.service.system.company.info.model.CompanyInfoVo;
import com.chinesedreamer.ipm.service.system.company.model.CompanyVo;
import com.chinesedreamer.ipm.service.system.company.service.CompanyService;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:28:20 AM
 * @version beta
 */
@Service
public class CopmanyServiceImpl extends IpmServiceImpl<Company, Long> implements CompanyService{
	@Resource
	private CompanyLogic logic;
	@Resource
	private CompanyInfoLogic infoLogic;
	
	@Override
	public Company createCompany(CompanyVo vo) {
		Company comp = this.logic.save(this.generateFromVo(vo));
		this.infoLogic.save(this.generateFromVo(vo.getInfoVo(), comp.getId()));
		return this.findOne(comp.getId());
	}

	/**
	 * 从vo生成新的客户
	 * @param vo
	 * @return
	 */
	private Company generateFromVo(CompanyVo vo) {
		Company comp = new Company();
		comp.setCode(vo.getCode());
		comp.setName(vo.getName());
		comp.setStatus(CompanyStatus.valueOf(vo.getStatus()));
		comp.setVip(vo.getVip());
		return comp;
	}
	/**
	 * 从vo生成客户信息
	 * @param vo
	 * @return
	 */
	private CompanyInfo generateFromVo(CompanyInfoVo vo, Long companyId) {
		CompanyInfo info = new CompanyInfo();
		info.setCompanyId(companyId);
		info.setScale(vo.getScale());
		info.setIndustry(vo.getIndustry());
		info.setContactEmail(vo.getEmail());
		info.setContactPhone(vo.getPhone());
		return info;
	}
}
