package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {

	private Stanza s1;
	private Stanza s2;
	private Comando vai;
	private Partita partita;
	List<String> righeDaLeggere;
	List<String> righeDaLeggere2;
	Labirinto labirinto;
	Labirinto labirinto2;
	private IO io;

	@Before
	public void setUp() throws Exception {
		io = new IOConsole(new Scanner(System.in));
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		vai = new ComandoVai();
		labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		partita = new Partita(labirinto);
		vai.setIO(io);
		righeDaLeggere = new ArrayList<>();
		righeDaLeggere2 = new ArrayList<>();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVaiDirezioneEsistente() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente(Direzione.sud, s2);
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(s2, partita.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente(Direzione.sud, s2);
		vai.setParametro("nord");
		vai.esegui(partita);
		assertNotEquals(s2, partita.getStanzaCorrente());
	}

	@Test
	public void testPartitaConComandoVai() throws Exception {
		righeDaLeggere.add("vai nord");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaEasy(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Biblioteca", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());

	}
}