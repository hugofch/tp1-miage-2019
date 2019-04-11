package com.acme.mailreader.domain;

import java.util.Comparator;

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
	
	//Méthode compare : méthode principale de la classe
	public int compare(Mail mail1, Mail mail2) {
		//Si un des mails est nul
		if (mail1 == null || mail2 == null) {
			return IMPORTANCE_EGALE;
		}
		//Si l'importance est différente
		if (importancesDifferentes(mail1, mail2)) {
			triParImportance(mail1, mail2);
		}
		//Si pas les statuts sont différents
		if (statutsDifferents(mail1, mail2)) {
			triParStatut(mail1, mail2);
		}
		//Si pas les sujets sont différents
		if (sujetsDifferents(mail1, mail2)) {
			triParSujet(mail1, mail2);
		}
		return mail2.getDate().compareTo(mail1.getDate());				
	}

//-----Méthodes déportées pour améliorer la lisibilité de la méthode "compare"-----//
	
	private boolean importancesDifferentes(Mail mail1, Mail mail2) {
		return mail1.isImportant() != mail2.isImportant();
	}

	private boolean statutsDifferents(Mail mail1, Mail mail2) {
		return mail1.getStatut() != mail2.getStatut();
	}

	private boolean sujetsDifferents(Mail mail1, Mail mail2) {
		if (mail1.getSujet() == null || mail2.getSujet() == null) {
			return true;
		}
		return !mail1.getSujet().equals(mail2.getSujet());
	}
	
	private int triParImportance(Mail mail1, Mail mail2) {
		if (mail1.isImportant() && !mail2.isImportant()) {
			return MAIL1_PLUS_IMPORTANT;
			}
		else {
			return MAIL1_MOINS_IMPORTANT;
			}
	}

	private int triParStatut(Mail mail1, Mail mail2) {
		int comp = mail1.getStatut().ordinal() - mail2.getStatut().ordinal();
		return comp > 0 ? MAIL1_PLUS_IMPORTANT : MAIL1_MOINS_IMPORTANT;
	}

	private int triParSujet(Mail mail1, Mail mail2) {
		return mail2.getSujet().compareTo(mail1.getSujet());
	}
}
