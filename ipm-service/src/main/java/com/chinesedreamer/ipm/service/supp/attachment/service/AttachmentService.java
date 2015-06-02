package com.chinesedreamer.ipm.service.supp.attachment.service;

import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:04:18 PM
 * @version beta
 */
public interface AttachmentService{
	
	/**
	 * 保存附件
	 * @param file
	 * @param userId
	 * @return
	 */
	public Attachment save(MultipartFile file, Long userId);
}
