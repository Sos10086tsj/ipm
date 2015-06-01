package com.chinesedreamer.ipm.tools.pdf.reader.service;

import com.chinesedreamer.ipm.tools.pdf.reader.model.IpmPdf;

public interface PdfReader {
	public IpmPdf read(String filePath);
}
