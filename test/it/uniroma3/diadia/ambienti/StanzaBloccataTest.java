package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class StanzaBloccataTest {

	StanzaBloccata stanzaBloccata = new StanzaBloccata("stanza", Direzione.valueOf("est"), "osso");
	Stanza stanzaAdiacente = new Stanza("stanzaAdiacente");
	Attrezzo attrezzoKey = new Attrezzo("osso",1);
	IO io = new IOConsole(new Scanner(System.in));
	
	@Test
    public void testGetSanzaAdiacente_SenzaAttrezzo() {
    	assertEquals(this.stanzaBloccata,this.stanzaBloccata.getStanzaAdiacente(Direzione.valueOf("est")));
    }
    
    @Test
    public void testGetSanzaAdiacente_ConAttrezzo() {
    	stanzaBloccata.impostaStanzaAdiacente(Direzione.valueOf("est"), stanzaAdiacente);
    	stanzaBloccata.addAttrezzo(attrezzoKey);
    	assertEquals(this.stanzaAdiacente,this.stanzaBloccata.getStanzaAdiacente(Direzione.valueOf("est")));
    }
}
