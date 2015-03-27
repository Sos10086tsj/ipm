package com.chinesedreamer.ipm.service.supp.dictionary.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.service.impl.IpmServiceImpl;
import com.chinesedreamer.ipm.domain.supp.dictionary.logic.DictionaryLogic;
import com.chinesedreamer.ipm.domain.supp.dictionary.model.Dictionary;
import com.chinesedreamer.ipm.domain.supp.dictionary.model.DictionaryStatus;
import com.chinesedreamer.ipm.domain.supp.dictionary.model.DictionaryType;
import com.chinesedreamer.ipm.service.supp.dictionary.model.DictionaryVo;
import com.chinesedreamer.ipm.service.supp.dictionary.service.DictionaryService;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 201512:38:15 PM
 * @version beta
 */
@Service
public class DictionaryServiceImpl extends IpmServiceImpl<Dictionary, Long> implements DictionaryService{
	@Resource
	private DictionaryLogic logic;

	@Override
	public void createDictionary(DictionaryVo vo) {
		Dictionary dictionary = new Dictionary();
		dictionary.setName(vo.getName());
		dictionary.setCode(vo.getCode());
		dictionary.setType(DictionaryType.valueOf(vo.getType()));
		this.logic.save(dictionary);
	}

	@Override
	public void updateDictionary(DictionaryVo vo) {
		Dictionary dictionary = this.logic.findOne(vo.getId());
		dictionary.setName(vo.getName());
		this.logic.update(dictionary);
	}

	@Override
	public void changeDictionaryStatus(DictionaryVo vo) {
		Dictionary dictionary = this.logic.findOne(vo.getId());
		dictionary.setStatus(DictionaryStatus.DELETED);
		this.logic.update(dictionary);
	}
	
}
