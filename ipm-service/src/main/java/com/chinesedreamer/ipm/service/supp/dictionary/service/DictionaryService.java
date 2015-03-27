package com.chinesedreamer.ipm.service.supp.dictionary.service;

import com.chinesedreamer.ipm.domain.base.service.IpmService;
import com.chinesedreamer.ipm.domain.supp.dictionary.model.Dictionary;
import com.chinesedreamer.ipm.service.supp.dictionary.model.DictionaryVo;

/**
 * Description: 系统字典表service接口。只有管理员拥有修改权限。可以新增某类数据。如果新增类型需要修改 {@link com.chinesedreamer.ipm.domain.supp.dictionary.model.DictionaryType}
 * @author Paris
 * @date Mar 27, 201512:36:32 PM
 * @version beta
 */
public interface DictionaryService extends IpmService<Dictionary, Long>{
	/**
	 * 创建字典
	 * @param vo
	 */
	public void createDictionary(DictionaryVo vo);
	
	/**
	 * 更新字典
	 * @param vo
	 */
	public void updateDictionary(DictionaryVo vo);
	
	/**
	 * 更新字典状态
	 * @param vo
	 */
	public void changeDictionaryStatus(DictionaryVo vo);
}
