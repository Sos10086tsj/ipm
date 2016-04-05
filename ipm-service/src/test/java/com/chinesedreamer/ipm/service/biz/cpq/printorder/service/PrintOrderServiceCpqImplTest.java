package com.chinesedreamer.ipm.service.biz.cpq.printorder.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptorDictionary;
import org.apache.pdfbox.util.PDFText2HTML;
import org.junit.Test;

public class PrintOrderServiceCpqImplTest {

	@Test
	public void testReadPdf() {
		File file = new File("C:/Users/Paris/Desktop/cpq201511/test.pdf");

		try {
			PDFText2HTML stripper = new PDFText2HTML("utf-8");
			PDDocument pd = PDDocument.load(file);
			int pageCount = pd.getDocumentCatalog().getAllPages().size();
			for (int i = 0; i < pageCount; i++) {
				//stripper.setForceParsing(forceParsingValue);
				stripper.setStartPage(i);
				stripper.setEndPage(i);
				//FileOutputStream fos = new FileOutputStream(new File("C:/Users/Paris/Desktop/cpq201511/page_" + i + ".html"));
				Writer writer = new FileWriter(new File("C:/Users/Paris/Desktop/cpq201511/page_" + i + ".html"));
				stripper.writeText(pd, writer);
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
