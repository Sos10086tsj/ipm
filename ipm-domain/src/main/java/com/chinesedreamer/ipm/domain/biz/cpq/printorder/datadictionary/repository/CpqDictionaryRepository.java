package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.repository;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;

public interface CpqDictionaryRepository extends IpmRepository<CpqDictionary, Long>{
	public CpqDictionary findByTypeAndProperty(CpqDictionaryType type, String property);
	public CpqDictionary findByTypeAndValue(CpqDictionaryType type, String value);
	public List<CpqDictionary> findByType(CpqDictionaryType type);
}
