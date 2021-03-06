package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.constant.CpqDictionaryType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.model.CpqDictionary;

public interface CpqDictionaryLogic extends IpmLogic<CpqDictionary, Long>{
	public List<CpqDictionary> findByTypeAndProperty(CpqDictionaryType type, String property);
	public List<CpqDictionary> findByType(CpqDictionaryType type);
}
