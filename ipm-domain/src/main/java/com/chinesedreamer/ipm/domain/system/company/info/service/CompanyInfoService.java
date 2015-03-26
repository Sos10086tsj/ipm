package com.chinesedreamer.ipm.domain.system.company.info.service;

import com.chinesedreamer.ipm.domain.base.service.IpmService;
import com.chinesedreamer.ipm.domain.system.company.info.model.CompanyInfo;
import com.chinesedreamer.ipm.domain.system.company.info.model.vo.CompanyInfoVo;

public interface CompanyInfoService extends IpmService<CompanyInfo, Long>{
	/**
	 * 注册客户
	 * @return
	 */
	public CompanyInfo createInfo(CompanyInfoVo vo);
}
