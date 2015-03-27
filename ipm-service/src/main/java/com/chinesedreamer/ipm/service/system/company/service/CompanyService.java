package com.chinesedreamer.ipm.service.system.company.service;

import com.chinesedreamer.ipm.domain.base.service.IpmService;
import com.chinesedreamer.ipm.domain.system.company.model.Company;
import com.chinesedreamer.ipm.service.system.company.model.CompanyVo;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:21:54 AM
 * @version beta
 */
public interface CompanyService extends IpmService<Company, Long>{
	/**
	 * 注册客户
	 * @param vo
	 * @return
	 */
	public void register(CompanyVo vo);
	
	/**
	 * 更新客户信息
	 * @param vo
	 */
	public void updateCompany(CompanyVo vo);
	
	/**
	 * 修改客户状态，管理员权限
	 * @param companyId
	 * @param status
	 */
	public void changeStatus(Long companyId, String status);
	
	/**
	 * 客户vip等级变更
	 * @param companyId
	 * @param vip
	 */
	public void rankVipLevel(Long companyId, Integer vip);
}
