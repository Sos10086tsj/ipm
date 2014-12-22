package com.chinesedreamer.ipm.message.center.email.sender;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.ipm.common.base.SpringTest;
import com.chinesedreamer.ipm.message.center.email.message.EmailRecipient;
import com.chinesedreamer.ipm.message.center.email.service.IpmEmailSender;

public class IpmEmailSenderImplTest extends SpringTest{
	@Resource
	private IpmEmailSender sender;
	@Test
	public void testSendEmail() {
		EmailRecipient recipient = new EmailRecipient();
		recipient.setTo(new String[]{"taosj@cyyun.com"});
		recipient.setCc(new String[]{"407414976@qq.com"});
		this.sender.sendEmail("paris1989@163.com", recipient, "Test spring email", "this is a test email");
	}

	@Test
	public void testHtmlEmail(){
		EmailRecipient recipient = new EmailRecipient();
		recipient.setTo(new String[]{"taosj@cyyun.com"});
		recipient.setCc(new String[]{"407414976@qq.com"});
		this.sender.sendHtmlEmail("paris1989@163.com", recipient, "Test spring email", "html><head></head><body><h1>This is a test email of spring html mail sender!</h1></body></html>");
	}
	
	@Test
	public void testCapture(){
		EmailRecipient recipient = new EmailRecipient();
		recipient.setTo(new String[]{"taosj@cyyun.com"});
		recipient.setCc(new String[]{"407414976@qq.com"});
		String inline = "capture";
		String capture = "C:/Users/Paris/Desktop/采集exception.png";
		String content = "<html><head></head><body><h1>hello!!spring image html mail</h1>"  
                        + "<img src=\"cid:capture\"/></body></html>";
		this.sender.sendCaptureEmail("paris1989@163.com", recipient, "Test image email", content, inline, capture);
	}
	
	@Test
	public void testAttach(){
		EmailRecipient recipient = new EmailRecipient();
		recipient.setTo(new String[]{"taosj@cyyun.com"});
		recipient.setCc(new String[]{"407414976@qq.com"});
		String attachPath = "C:/Users/Paris/Desktop/phabricator修改默认密码.zip";
		String content = "<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>";
		this.sender.sendAttachEmail("paris1989@163.com", recipient, "Test attach email", content,attachPath);
	}
}
