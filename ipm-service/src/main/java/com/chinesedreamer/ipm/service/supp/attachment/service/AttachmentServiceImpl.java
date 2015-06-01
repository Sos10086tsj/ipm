package com.chinesedreamer.ipm.service.supp.attachment.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.ipm.common.utils.io.PropertiesUtils;
import com.chinesedreamer.ipm.domain.supp.attachment.constant.AttachBizType;
import com.chinesedreamer.ipm.domain.supp.attachment.logic.AttachmentLogic;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.service.supp.attachment.constant.AttachmentVo;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:08:32 PM
 * @version beta
 */
@Service
public class AttachmentServiceImpl implements AttachmentService{
	private Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Resource
	private AttachmentLogic logic;

	@Override
	public AttachmentVo save(MultipartFile file, AttachBizType bizType,
			String bizReference) {
		//1. 保存文件
		PropertiesUtils pu = new PropertiesUtils("config.properties");
		String prefix = pu.getProperty("file.upload.prefix") + bizType.toString() + "/";
		File folder = new File(prefix);
		if (!folder.exists()) {
			folder.mkdir();
		}
		File uploadFile = new File(prefix + file.getOriginalFilename());
		try {
			file.transferTo(uploadFile);
		} catch (Exception e) {
			this.logger.error("{}", e);
		}
		//2. 保存到db
		Attachment attachment = new Attachment();
		attachment.setBizType(bizType);
		attachment.setBizReference(bizReference);
		attachment.setFileName(file.getOriginalFilename());
		attachment.setFilePath(bizType.toString() + "/" + file.getName());
		return null;
	}

	@Override
	public List<AttachmentVo> find(AttachBizType bizType, String bizReference) {
		List<Attachment> attachments = this.logic.findByBizTypeAndBizReference(bizType, bizReference);
		List<AttachmentVo> vos = new ArrayList<AttachmentVo>();
		for (Attachment attach : attachments) {
			vos.add(new AttachmentVo(attach.getFileName(), attach.getFilePath(), attach.getFileSize()));
		}
		return vos;
	}

}
