package com.chinesedreamer.ipm.domain.supp.attachment.logic;

import java.util.List;

import com.chinesedreamer.ipm.domain.base.logic.IpmLogic;
import com.chinesedreamer.ipm.domain.supp.attachment.constant.AttachBizType;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;

/**
 * Description: 
 * @author Paris
 * @date May 27, 20152:02:24 PM
 * @version beta
 */
public interface AttachmentLogic extends IpmLogic<Attachment, Long>{
	public List<Attachment> findByBizTypeAndBizReference(AttachBizType bizType,String bizReference);
}
