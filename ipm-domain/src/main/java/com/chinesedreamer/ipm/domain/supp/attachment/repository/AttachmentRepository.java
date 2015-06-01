package com.chinesedreamer.ipm.domain.supp.attachment.repository;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.repository.IpmRepository;
import com.chinesedreamer.ipm.domain.supp.attachment.constant.AttachBizType;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:00:50 PM
 * @version beta
 */
public interface AttachmentRepository extends IpmRepository<Attachment, Long>{
	public List<Attachment> findByBizTypeAndBizReference(AttachBizType bizType,String bizReference);
}
