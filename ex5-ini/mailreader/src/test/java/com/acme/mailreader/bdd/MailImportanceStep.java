package com.acme.mailreader.bdd;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.acme.mailreader.domain.DateIncorrecteException;
import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.domain.MailComparator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Les steps (actions) du test
 * 
 * <p>
 * A noter qu'une nouvelle instance de cette classe est créée à chaque scenario
 * </p>
 *
 */

public class MailImportanceStep {

	private Mail mail;
	private String resultat;
	private Boolean importance;


	@Given("^un mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_premier_mail(boolean importance, Statut statut,
			String sujet, String date) throws DateIncorrecteException {
		
		this.mail = new Mail.Builder(sujet).important(importance).statut(statut).date(Instant.parse(date)).build();
		this.importance = importance;
	}

	
	@When("^je test$")
	public void je_trie() throws Throwable {
		
		if ( importance != true || importance != false ) {
			resultat = "INVALID";
		} else resultat = "VALID";
	}

	@Then("^test doit retourner \"([^\"]*)\"$")
	public void le_tri_doit_retourner(String resu_statut) throws Throwable {
		assertThat(resu_statut, is(resultat));
	}
	

}