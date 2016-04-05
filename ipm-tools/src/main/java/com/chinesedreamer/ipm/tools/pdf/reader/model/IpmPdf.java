package com.chinesedreamer.ipm.tools.pdf.reader.model;

import java.util.Date;
import java.util.List;

import com.chinesedreamer.ipm.tools.image.model.IpmImage;

/**
 * parse result return model
 * @author Paris
 *
 */
public  class IpmPdf{
	
	private String filePaht;//path of read file
	private int pageNumber;// total number of pdf
	private String title;
	private String subject;
	private String author;
	private Date readDate;
	private String content;
	private List<IpmImage> images;
	public String getFilePaht() {
		return filePaht;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public String getTitle() {
		return title;
	}
	public String getSubject() {
		return subject;
	}
	public String getAuthor() {
		return author;
	}
	public Date getReadDate() {
		return readDate;
	}
	public String getContent() {
		return content;
	}
	public List<IpmImage> getImages() {
		return images;
	}
	public void setFilePaht(String filePaht) {
		this.filePaht = filePaht;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setImages(List<IpmImage> images) {
		this.images = images;
	}
	
	
}
