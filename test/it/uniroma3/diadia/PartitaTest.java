package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Labirinto labirinto;

	private Partita partita = new Partita(labirinto);
	private Stanza stanza = new Stanza("Stanza");

	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		partita = new Partita(labirinto);
		stanza = new Stanza("Stanza");
	}

	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", partita.getLabirinto().getStanzaVincente().getNome());
	}

	@Test
	public void testSetStanzaCorrente() {
		partita.getLabirinto().setStanzaCorrente(stanza);
		assertEquals(stanza, partita.getLabirinto().getStanzaCorrente());

	}

	@Test
	public void testIsFinita() {

		assertFalse(partita.isFinita());

		partita.getGiocatore().setCfu(0);
		assertEquals(true, partita.isFinita());

		partita.setFinita();
		assertEquals(true, partita.isFinita());
	}

}