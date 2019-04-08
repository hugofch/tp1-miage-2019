package com.acme.mailreader.domaine;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {
	
	private static final int IMPORTANCE_EGALE = 0;
	private static final int MAIL1_MOINS_IMPORTANT = 1;
	private static final int MAIL1_PLUS_IMPORTANT = -1;
	
	public int compare(Mail mail1, Mail mail2) {
		
		//Si la même importance
		if (mail1 == null || mail2 == null) {
			return IMPORTANCE_EGALE;
		}
		//Si pas la même importance
		if (mail1.isImportant() != mail2.isImportant()) {
			triParImportance(mail1, mail2);
		}
		
		else {
			
			//Si pas le meme statut
			if (mail1.getStatut() != mail2.getStatut()) {
				int comp = mail1.getStatut().ordinal() - mail2.getStatut().ordinal();
				return comp > 0 ? -1 : 1;
			}
			
			//Si pas le même sujet
			else if (mail1.getSujet() != mail2.getSujet()) {
				return mail2.getSujet().compareTo(mail1.getSujet());
			}
			
			else {
				return mail2.getDate().compareTo(mail1.getDate());				
			}	
		}
	}
	
	private int triParImportance(Mail mail1, Mail mail2) {
		if (mail1.isImportant() && !mail2.isImportant()) {
			return MAIL1_PLUS_IMPORTANT;
		} else {
			return MAIL1_MOINS_IMPORTANT;
		}
	}
}
