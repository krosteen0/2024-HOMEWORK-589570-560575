package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {	

	Stanza stanza1 = new Stanza("stanza1");
	Stanza stanza2= new Stanza("stanza2");
	Attrezzo osso = new Attrezzo("osso", 5);
	
	@Test
	public void testGetStanzaAdiacente() {
		assertNull(stanza1.getStanzaAdiacente(Direzione.valueOf("est")));
	}
	

	@Test
	public void testImpostaStanzaAdiacente() {
		stanza1.impostaStanzaAdiacente(Direzione.valueOf("est"), stanza2);
		assertEquals(stanza2, stanza1.getStanzaAdiacente(Direzione.valueOf("est")));
	}
	
	@Test
	public void testAddAttrezzo() {
		
		assertEquals(null,stanza1.getAttrezzo(osso.getNome()));
		stanza1.addAttrezzo(osso);
		assertEquals(osso,stanza1.getAttrezzo(osso.getNome()));
	}
	

}
