package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	IO io;
	Labirinto labirinto;
	Partita partita;
	Attrezzo attrezzo;
	Attrezzo attrezzoPesante;
	ComandoPrendi comando;
	
	@Before
	public void setUp() throws Exception {
		labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		partita = new Partita(labirinto);
		attrezzo = new Attrezzo("osso", 1);
		attrezzoPesante = new Attrezzo("incudine", 11);
		comando = new ComandoPrendi();
		io = new IOConsole(new Scanner(System.in));
		comando.setIO(io);
	}
	
	@Test
	public void testAttrezzoPreso() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	public void testAttrezzoNonPresente() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	public void testAttrezzoPesante() {
		partita.getStanzaCorrente().addAttrezzo(attrezzoPesante);
		comando.setParametro("incudine");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("incudine"));
	}
}
