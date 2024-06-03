package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;


public class LabirintoTest {
	Labirinto labirinto;
	Stanza biblioteca;
	Stanza DS1;

	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		biblioteca = new Stanza("Biblioteca");
		DS1 = new Stanza("DS1");
	}


	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}


	@Test
	public void testSetStanzaCorrente() {
		labirinto.setStanzaCorrente(DS1);
		assertEquals(DS1, labirinto.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
	}

}
