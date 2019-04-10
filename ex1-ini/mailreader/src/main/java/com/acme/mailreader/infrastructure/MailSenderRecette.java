package com.acme.mailreader.infrastructure;
import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.service.MailSender;

public class MailSenderRecette implements MailSender{
	
	@Override
	public void envoyerMail(Mail mail) {	
		System.out.println("Envoi de mail à partir de l'environnement de recette.");
	}
}
