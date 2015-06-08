package com.chinesedreamer.ipm.domain.biz.cpq.file.logic;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.model.CpqFile;

public interface CpqFileLogic extends IpmLogic<CpqFile, Long>{
	/**
	 * 删除某张上传的pdf
	 * @param id
	 */
	public void deletePdf(Long id);
	
	/**
	 * 根据文件名查找pdf
	 * @param fileName
	 * @return
	 */
	public List<CpqFile> findByFileName(String fileName);
	
	public List<CpqFile> findByTypeOrderByUploadDate(CpqFileType type);
}
