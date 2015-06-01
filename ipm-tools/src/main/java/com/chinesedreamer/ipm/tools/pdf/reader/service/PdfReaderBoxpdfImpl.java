package com.chinesedreamer.ipm.tools.pdf.reader.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.tools.pdf.reader.model.IpmPdf;

@Service("boxpdfReader")
public class PdfReaderBoxpdfImpl implements PdfReader {
	private Logger logger = LoggerFactory.getLogger(PdfReaderBoxpdfImpl.class);

	@Override
	public IpmPdf read(String filePath) {
		IpmPdf pdf = new IpmPdf();
		InputStream input = null;
		File pdfFile = new File(filePath);
		PDDocument document = null;
		try {
			input = new FileInputStream(pdfFile);
			// 加载 pdf 文档
			document = PDDocument.load(input);

			/** 文档属性信息 **/
			PDDocumentInformation info = document.getDocumentInformation();
			pdf.setTitle(info.getTitle());
			pdf.setSubject(info.getSubject());
			pdf.setAuthor(info.getAuthor());

			// 获取内容信息
			PDFTextStripper pts = new PDFTextStripper();
			String content = pts.getText(document);
			pdf.setContent(content);

			/** 文档页面信息 **/
			PDDocumentCatalog cata = document.getDocumentCatalog();
			pdf.setPageNumber(cata.getAllPages().size());
			return pdf;
		} catch (Exception e) {
			logger.error("pdf parse error", e);
			return pdf;
		} finally {
			if (null != input)
				try {
					input.close();
				} catch (IOException e) {
					logger.error("IOExeption:", e);
				}
			if (null != document)
				try {
					document.close();
				} catch (IOException e) {
					logger.error("IOExeption:", e);
				}
		}
	}

}
