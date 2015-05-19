package com.chinesedreamer.ipm.tools.pdf.reader.service;

import com.chinesedreamer.ipm.tools.pdf.reader.constant.PdfReaderType;

public interface PdfFactory {
	/**
	 * 获取对应的解析器
	 * @param type
	 * @return
	 */
	public PdfReader getPdfReader(PdfReaderType type);
}
