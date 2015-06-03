package com.chinesedreamer.ipm.domain.biz.cpq.printorder.datadictionary.logic;

import java.util.List;

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
	public List<CpqDictionary> findByTypeAndProperty(CpqDictionaryType type, String property) {
		return this.repository.findByTypeAndPropertyOrderBySeqAsc(type, property);
	}

	@Override
	public List<CpqDictionary> findByType(CpqDictionaryType type) {
		return this.repository.findByTypeOrderBySeqAsc(type);
	}

}
