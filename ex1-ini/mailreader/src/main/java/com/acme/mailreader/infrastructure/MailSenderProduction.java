package com.acme.mailreader.infrastructure;
import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.service.MailSender;

public class MailSendProduction implements MailSender {

	@Override
	public void envoyerMail(Mail leMail) {
		System.out.println("Mail envoyé en production. (SMTP)");
		
	}

}