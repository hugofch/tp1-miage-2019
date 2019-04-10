package com.acme.mailreader.service;
import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.infrastructure.*;

public class MailService {
	public MailService() {};
	public void envoyerMail(String sujet, boolean production) {
		Mail mail = new Mail();
		mail.setSujet(sujet);
		
		MailSender sender;
		if(production) {
			sender = new MailSenderProduction();
		} else {
			sender = new MailSenderRecette();
		}
		sender.envoyerMail(mail);
	}

}
