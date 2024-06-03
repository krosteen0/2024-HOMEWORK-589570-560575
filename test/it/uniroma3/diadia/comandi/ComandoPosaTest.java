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

public class ComandoPosaTest {

	Labirinto labirinto;
	IO io;
	Partita partita;
	Attrezzo attrezzo;
	Attrezzo attrezzoErrato;
	ComandoPosa comando;
	ComandoPosa comandoErrato;

	@Before
	public void setUp() throws Exception {
		labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		partita = new Partita(labirinto);
		attrezzo = new Attrezzo("martello", 2);
		attrezzoErrato = new Attrezzo("lampada", 2);
		comando = new ComandoPosa(attrezzo.toString());
		comandoErrato = new ComandoPosa(attrezzoErrato.toString());
		io = new IOConsole(new Scanner(System.in));
		comando.setIO(io);
		comandoErrato.setIO(io);
	}

	@Test
	public void eseguiTest() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		
		comando.esegui(partita);
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	public void attrezzoInesistenteTest() {

		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);

		comandoErrato.esegui(partita);

		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzoErrato.getNome()));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzoErrato.getNome()));

	}

}