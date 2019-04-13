package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;

public class MailTest {
	
	MailComparator comparator = new MailComparator();
	

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1970() throws DateIncorrecteException {
					
	}
	
	@Test 
	public final void premierPlusPetitSiInferieur() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.SENT).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}
	
	@Test
	public final void premierPlusPetitSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
				
	}
	
	@Test 
	public final void premierPlusPetitSiImportanceEgalAFalse() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}

}