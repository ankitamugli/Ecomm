package com.dcl.user.service;

public interface MailSender {
	
	void sendMail(String to,String subject,String body);

}
