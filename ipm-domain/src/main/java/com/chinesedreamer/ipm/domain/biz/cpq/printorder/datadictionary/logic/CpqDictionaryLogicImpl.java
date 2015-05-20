package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.repository.CpqDictionaryRepository;

@Service
public class CpqDictionaryLogicImpl extends IpmLogicImpl<CpqDictionary, Long> implements CpqDictionaryLogic{
	@Resource
	private CpqDictionaryRepository repository;
	
	@Override
	public CpqDictionary findByTypeAndKey(CpqDictionaryType type, String key) {
		return this.repository.findByTypeAndKey(type, key);
	}

	@Override
	public CpqDictionary findByTypeAndValue(CpqDictionaryType type, String value) {
		return this.repository.findByTypeAndValue(type, value);
	}

}
