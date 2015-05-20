package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.repository;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;

public interface CpqDictionaryRepository extends IpmRepository<CpqDictionary, Long>{
	public CpqDictionary findByTypeAndKey(CpqDictionaryType type, String key);
	public CpqDictionary findByTypeAndValue(CpqDictionaryType type, String value);
}
