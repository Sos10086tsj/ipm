package com.chinesedreamer.ipm.domain.biz.cpq.file.repository;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;

public interface CpqFileRepository extends IpmRepository<CpqFile, Long>{
	public List<CpqFile> findByFileNameLike(String fileName);
	
	public List<CpqFile> findByDeletedFalseOrderByUploadDateDesc();
}
