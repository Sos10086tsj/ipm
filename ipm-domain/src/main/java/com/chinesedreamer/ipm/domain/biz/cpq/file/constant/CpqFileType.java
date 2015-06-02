package com.chinesedreamer.ipm.domain.biz.cpq.file.constant;

import org.apache.commons.lang.StringUtils;

public enum CpqFileType {
	DEAFULT,PDF,EXCEL;
	
	public static CpqFileType getType(String fileName){
		if (StringUtils.isEmpty(fileName)) {
			return DEAFULT;
		}
		if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
			return EXCEL;
		}
		if (fileName.endsWith(".pdf")) {
			return PDF;
		}
		return DEAFULT;
	}
}
