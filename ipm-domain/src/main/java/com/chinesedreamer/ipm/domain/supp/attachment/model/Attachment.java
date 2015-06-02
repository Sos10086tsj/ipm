package com.chinesedreamer.ipm.domain.supp.attachment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmEntity;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20151:50:54 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_supp_attachment")
public @Getter @Setter class Attachment extends IpmEntity<Long>{

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
}
