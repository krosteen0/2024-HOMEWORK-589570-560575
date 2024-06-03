package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	StanzaMagica stanza = new StanzaMagica("stanza");
	Attrezzo attrezzo = new Attrezzo("piano", 1);

	@Test
	public void modificaAttrezzoTest_Disattivata() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo.getNome(), stanza.getAttrezzo(attrezzo.getNome()).getNome());
	}

	@Test
	public void modificaAttrezzoTest_Attivata() {
		for (int i = 0; i < 3; i++) {
			stanza.addAttrezzo(attrezzo);
			stanza.removeAttrezzo(attrezzo);
		}

		stanza.addAttrezzo(attrezzo);
		
		Attrezzo attrezzoVerifica = new Attrezzo("onaip",2);
		assertTrue(stanza.hasAttrezzo(attrezzoVerifica.getNome()));

	}
}

