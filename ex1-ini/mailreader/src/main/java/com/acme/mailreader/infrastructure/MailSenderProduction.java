package infrastructure;
import domaine.Mail;
import service.MailSender;

public class MailSendProduction implements MailSender {

	@Override
	public void envoyerMail(Mail leMail) {
		System.out.println("Mail envoyé en production. (SMTP)");
		
	}

}