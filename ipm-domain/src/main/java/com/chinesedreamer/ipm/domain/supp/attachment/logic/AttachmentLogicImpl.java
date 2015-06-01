package com.chinesedreamer.ipm.domain.supp.attachment.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.ipm.domain.base.logic.impl.IpmLogicImpl;
import com.chinesedreamer.ipm.domain.supp.attachment.constant.AttachBizType;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;
import com.chinesedreamer.ipm.domain.supp.attachment.repository.AttachmentRepository;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:02:43 PM
 * @version beta
 */
@Service
public class AttachmentLogicImpl extends IpmLogicImpl<Attachment, Long> implements AttachmentLogic{
	@Resource
	private AttachmentRepository repository;
	@Override
	public List<Attachment> findByBizTypeAndBizReference(AttachBizType bizType,
			String bizReference) {
		return this.repository.findByBizTypeAndBizReference(bizType, bizReference);
	}

}
