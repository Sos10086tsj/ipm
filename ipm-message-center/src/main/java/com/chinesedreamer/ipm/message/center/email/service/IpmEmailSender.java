package com.chinesedreamer.ipm.message.center.email.service;

import com.chinesedreamer.ipm.message.center.email.entity.VelocityTemplate;
import com.chinesedreamer.ipm.message.center.email.message.EmailRecipient;

public interface IpmEmailSender {
	public void sendEmail(String from, EmailRecipient recipient, String subject, String content);
	
	public void sendHtmlEmail(String from, EmailRecipient recipient, String subject, String content);
	
	public void sendCaptureEmail(String from, EmailRecipient recipient, String subject, String content, String inline,String capture);
	
	public void sendAttachEmail(String from, EmailRecipient recipient, String subject, String content, String attachPath);
	
	public void sendTemplateEmail(String from, EmailRecipient recipient, String subject, String templatePath, VelocityTemplate velocityTemplate);
}
