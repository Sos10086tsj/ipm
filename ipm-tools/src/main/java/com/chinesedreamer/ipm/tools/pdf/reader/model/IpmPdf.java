package com.chinesedreamer.ipm.tools.pdf.reader.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.tools.image.model.IpmImage;

/**
 * parse result return model
 * @author Paris
 *
 */
public @Getter @Setter class IpmPdf{
	
	private String filePaht;//path of read file
	private int pageNumber;// total number of pdf
	private String title;
	private String subject;
	private String author;
	private Date readDate;
	private String content;
	private List<IpmImage> images;
}
