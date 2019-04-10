package com.acme.mailreader.infrastructure;
import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.service.MailSender;

public class MailSenderProduction implements MailSender{

	@Override
	public void envoyerMail(Mail leMail) {
		System.out.println("Envoi de mail à partir de l'environnement de production.");
	}
	
}
