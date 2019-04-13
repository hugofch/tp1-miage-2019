package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.domain.MailComparator;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSideuxMailEgaux() {
		Mail mail1 = new Mail();
		Mail mail2 = mail1;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test 
	public final void secondPlusPetitSiSujetEgalNul() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("Quelquechose").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder(null).important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(-1));	
	}
	
	@Test 
	public final void premierPlusPetitSiImportanceInferieure() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("Quelquechose").important(true).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("Quelquechose").important(false).statut(Statut.UNSENT).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}
	
	
	@Test 
	public final void secondPlusPetitSiImportanceInferieure() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("Quelquechose").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("Quelquechose").important(true).statut(Statut.UNSENT).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(-1));	
	}
	
}
