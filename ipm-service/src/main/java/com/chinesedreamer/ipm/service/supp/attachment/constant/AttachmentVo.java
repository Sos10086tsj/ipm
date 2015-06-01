package com.chinesedreamer.ipm.service.supp.attachment.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:04:56 PM
 * @version beta
 */
public @Getter @Setter class AttachmentVo {
	
	private String fileName;
	private String filePath;
	private Long fileSize;
	
	public AttachmentVo(String fileName,String filePath,Long fileSize){
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
}
