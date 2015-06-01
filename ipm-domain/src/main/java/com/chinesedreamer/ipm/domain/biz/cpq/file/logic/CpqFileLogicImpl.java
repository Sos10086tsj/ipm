package com.chinesedreamer.ipm.domain.biz.cpq.file.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;
import com.chinesedreamer.ipm.domain.biz.cpq.file.repository.CpqFileRepository;

@Service
public class CpqFileLogicImpl extends IpmLogicImpl<CpqFile, Long> implements CpqFileLogic{
	@Resource
	private CpqFileRepository repository;
	@Override
	public void deletePdf(Long id) {
		//1. 删除本身
		CpqFile pdf = this.repository.findOne(id);
		pdf.setDeleted(Boolean.TRUE);
		this.repository.save(pdf);
	}
	@Override
	public List<CpqFile> findByFileName(String fileName) {
		return this.repository.findByFileNameLike("%" + fileName + "%");
	}
	
}
