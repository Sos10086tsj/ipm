package com.chinesedreamer.ipm.service.supp.attachment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.ipm.domain.supp.attachment.constant.AttachBizType;
import com.chinesedreamer.ipm.service.supp.attachment.constant.AttachmentVo;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:04:18 PM
 * @version beta
 */
public interface AttachmentService{
	/**
	 * 保存单文件
	 * @param file
	 * @param bizType
	 * @param bizReference
	 * @return
	 */
	public AttachmentVo save(MultipartFile file, AttachBizType bizType, String bizReference);
	
	/**
	 * 查找file
	 * @param bizType
	 * @param bizReference
	 * @return
	 */
	public List<AttachmentVo> find(AttachBizType bizType, String bizReference);
}
