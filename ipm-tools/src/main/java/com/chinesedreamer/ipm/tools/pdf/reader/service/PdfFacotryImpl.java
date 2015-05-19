package com.chinesedreamer.ipm.tools.pdf.reader.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.tools.pdf.reader.constant.PdfReaderType;

@Service
public class PdfFacotryImpl implements PdfFactory{
	@Resource(name = "boxpdfReader")
	private PdfReader boxpdfReader;
	
	@Override
	public PdfReader getPdfReader(PdfReaderType type) {
		PdfReader reader = null;
		switch (type) {
		case BOXPDF:
			reader = boxpdfReader;
			break;
		default:
			reader = boxpdfReader;
			break;
		}
		return reader;
	}

}
