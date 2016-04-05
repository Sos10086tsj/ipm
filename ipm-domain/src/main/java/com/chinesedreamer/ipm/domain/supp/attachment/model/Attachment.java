package com.chinesedreamer.ipm.domain.supp.attachment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmEntity;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20151:50:54 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_supp_attachment")
public class Attachment extends IpmEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4794634995434553359L;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "file_size")
	private Long fileSize;
	
	@Column(name = "upload_date")
	private Date uploadDate;
	
	@Column(name = "upload_user")
	private Long uploadUser;

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public Long getUploadUser() {
		return uploadUser;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public void setUploadUser(Long uploadUser) {
		this.uploadUser = uploadUser;
	}
	
	
}
