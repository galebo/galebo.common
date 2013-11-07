package com.galebo.common;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailService {

    Log logger = LogFactory.getLog(MailService.class);
	public static boolean sendMail = true;
	public static int count = 0;
	static public String _newline = "\n";

	static boolean startMailService = true;

	public void setStartMailService(boolean _startMailService) {
		startMailService = _startMailService;
	}

	public String sendMail(String mailMessages, String subject) {
		logger.info("正在发送邮件...");
		if (MailService.sendMail) {
			if (subject != null && subject.length() > 0) {
				mailMessageInfo.setSubject(subject);
			}
			mailMessageInfo.setText(mailMessages);
			try {
				mailSender.send(mailMessageInfo);
			} catch (MailException e) {
				logger.info("邮件发送失败!");
				count++;
				if (count > 2) {
					sendMail = false;
					count = 0;
				}
				e.printStackTrace();
			}
			logger.info("邮件发送完毕");
		} else {
			logger.info("未有邮件设置，不发送");
		}
		return null;
	}

	MailSender mailSender;
	SimpleMailMessage mailMessageInfo;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessageInfo = mailMessage;
	}

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void send(String message, String subject) {
		MailService mailService = (MailService) SpringContext.getApplicationContext().getBean("mailService");
		if (startMailService) mailService.sendMail(format.format(new Date()).toString() + _newline + message, subject);
	}
}
