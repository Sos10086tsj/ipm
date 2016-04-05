package com.chinesedreamer.ipm.message.center.email.message;

public class EmailRecipient {
	private String[] to = new String[]{};
	private String[] cc = new String[]{};
	private String[] bcc = new String[]{};
	public String[] getTo() {
		return to;
	}
	public String[] getCc() {
		return cc;
	}
	public String[] getBcc() {
		return bcc;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	
	
}
