package com.chinesedreamer.ipm.tools.office.service;

import com.chinesedreamer.ipm.tools.office.constant.OfficeType;

/**
 * Description: 
 * @author Paris
 * @date May 22, 20159:10:40 AM
 * @version beta
 */
public interface OfficeFactory {
	/**
	 * 获取office解析器
	 * @param type
	 * @return
	 */
	public OfficeService getService(OfficeType type);
}
