package com.me.pojo;




import org.springframework.mail.SimpleMailMessage;
public class EmailSender {
		
	public SimpleMailMessage sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		return message;
			
	}
}
