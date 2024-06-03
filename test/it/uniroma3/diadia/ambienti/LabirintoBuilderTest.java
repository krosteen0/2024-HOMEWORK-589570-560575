package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder("labirinto.txt");
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale).getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
	}

	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada", 1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3).getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
		assertEquals("spada", monolocale.getStanzaCorrente().getAttrezzo("spada").getNome());
		assertEquals("spadina", monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}

	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada", 1)
				.addAttrezzo("spada", 1).getLabirinto();
		int size = monolocale.getStanzaCorrente().getAttrezzi().size();
		assertTrue(size == 1);
		assertEquals(Arrays.asList(new Attrezzo("spada", 1)), monolocale.getStanzaCorrente().getAttrezzi());
	}

	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, Direzione.valueOf("nord"))
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, Direzione.valueOf("sud")).getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),
				bilocale.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("nord")));
		assertEquals(Collections.singletonList(Direzione.valueOf("nord")), bilocale.getStanzaCorrente().getDirezioni());
		assertEquals(Collections.singletonList(Direzione.valueOf("sud")), bilocale.getStanzaVincente().getDirezioni());
	}

	@Test
	public void testTrilocale() {
		Labirinto trilocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca").addAdiacenza(nomeStanzaIniziale, "biblioteca", Direzione.valueOf("sud"))
				.addAdiacenza("biblioteca", nomeStanzaIniziale, Direzione.valueOf("nord"))
				.addAttrezzo("libro antico", 5).addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, Direzione.valueOf("est"))
				.addAdiacenza(nomeStanzaVincente, "biblioteca", Direzione.valueOf("ovest")).getLabirinto();
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",
				trilocale.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("sud")).getNome());
	}

	@Test
	public void testTrilocaleConStanzaDuplicata() {
		labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza("stanza generica").addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", Direzione.valueOf("nord")).getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size() <= 2);
	}

	@Test
	public void testQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza("stanza 1")
				.addStanza("stanza 2").addStanza("stanza 3").addStanza("stanza 4").addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", Direzione.valueOf("nord"))
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", Direzione.valueOf("ovest"))
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", Direzione.valueOf("sud"))
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", Direzione.valueOf("est")).getLabirinto();
		assertTrue(maze.getStanzaCorrente().getMapStanzeAdiacenti().size() <= 4);
		Map<Direzione, Stanza> mappa = new HashMap<>();
		mappa.put(Direzione.valueOf("nord"), new Stanza("stanza 1"));
		mappa.put(Direzione.valueOf("ovest"), new Stanza("stanza 2"));
		mappa.put(Direzione.valueOf("sud"), new Stanza("stanza 3"));
		mappa.put(Direzione.valueOf("est"), new Stanza("stanza 4"));
		assertEquals(mappa.toString(), maze.getStanzaCorrente().getMapStanzeAdiacenti().toString());
	}

	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder.addStanzaIniziale(this.nomeStanzaIniziale).addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale").getLabirinto();
		assertEquals("nuova iniziale", maze.getStanzaCorrente().getNome());
	}

	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso).getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		assertEquals(attrezzo, maze.getStanzaCorrente().getAttrezzo(nomeAttrezzo));
	}

	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza(nomeStanza).addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi()
				.contains(new Attrezzo(nomeAttrezzo, peso)));
		assertEquals(new Attrezzo(nomeAttrezzo, peso),
				this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso).getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		List<Attrezzo> attrezzi = labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi();
		assertEquals(attrezzo, attrezzi.get(attrezzi.indexOf(attrezzo)));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder.addStanza(nomeStanza1).addAttrezzo(nomeAttrezzo1, peso1).addAttrezzo(nomeAttrezzo2,
				peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}

	@Test // verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder.addStanza(nomeStanza1).addStanza(nomeStanza2).addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder.addStanza(nomeStanza1).addAttrezzo(nomeAttrezzo1, peso1).addStanza(nomeStanza2)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testLabirintoConStanzaMagica() {
		String nomeStanzaMagica = "Stanza Magica";
		int sogliaMagica = 1;
		this.labirintoBuilder.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica) labirintoBuilder.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}

	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2 * 2;
		String nomeStanzaMagica = "Stanza Magica";
		int sogliaMagica = 1;
		this.labirintoBuilder.addStanzaMagica(nomeStanzaMagica, sogliaMagica).addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv, peso2_x2),
				listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}

	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", Direzione.valueOf("nord"), "chiave").addAttrezzo("chiave", 1)
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", Direzione.valueOf("nord"))
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, Direzione.valueOf("sud"))
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, Direzione.valueOf("nord"))
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", Direzione.valueOf("sud"));
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		// Asserisce che in presenza di passepartout, invocato il metodo
		// getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente.getNome(), labirintoBuilder.getListaStanze().get("stanza bloccata")
				.getStanzaAdiacente(Direzione.valueOf("nord")).getNome());
	}

	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", Direzione.valueOf("nord"), "chiave")
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", Direzione.valueOf("nord"))
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, Direzione.valueOf("sud"))
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, Direzione.valueOf("nord"))
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", Direzione.valueOf("sud"));
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", Direzione.valueOf("nord"), "chiave");
		// Asserisce che in caso di mancanza di passepartout, invocato il metodo
		// getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata.getNome(), labirintoBuilder.getListaStanze().get("stanza bloccata")
				.getStanzaAdiacente(Direzione.valueOf("nord")).getNome());
	}

	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		Labirinto labirintoCompleto = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente).addStanza("corridoio").addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1).addStanzaBloccata("corridoio bloccato", Direzione.valueOf("nord"), "chiave")
				.addStanzaMagica("stanza magica", 1).addStanzaBuia("stanza buia", "lanterna").addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", Direzione.valueOf("nord"))
				.addAdiacenza("corridoio", nomeStanzaIniziale, Direzione.valueOf("sud"))
				.addAdiacenza("corridoio", "corridoio bloccato", Direzione.valueOf("nord"))
				.addAdiacenza("corridoio bloccato", "corridoio", Direzione.valueOf("sud"))
				.addAdiacenza("corridoio bloccato", "Aula 1", Direzione.valueOf("nord"))
				.addAdiacenza("Aula 1", "corridoio bloccato", Direzione.valueOf("sud"))
				.addAdiacenza("Aula 1", nomeStanzaVincente, Direzione.valueOf("nord"))
				.addAdiacenza(nomeStanzaVincente, "Aula 1", Direzione.valueOf("sud"))
				.addAdiacenza("corridoio", "stanza magica", Direzione.valueOf("est"))
				.addAdiacenza("stanza magica", "corridoio", Direzione.valueOf("ovest"))
				.addAdiacenza("corridoio", "stanza buia", Direzione.valueOf("ovest"))
				.addAdiacenza("stanza buia", "corridoio", Direzione.valueOf("est")).getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("nord"));
		assertEquals("corridoio", corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList(Direzione.valueOf("ovest"),
				Direzione.valueOf("est"), Direzione.valueOf("nord"), Direzione.valueOf("sud"))));
		Map<Direzione, Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(Direzione.valueOf("nord"), new StanzaBloccata("corridoio bloccato", Direzione.valueOf("nord"), "chiave"));
		mapAdiacenti.put(Direzione.valueOf("sud"), new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(Direzione.valueOf("est"), new StanzaMagica("stanza magica"));
		mapAdiacenti.put(Direzione.valueOf("ovest"), new StanzaBuia("stanza buia", "lanterna"));
		assertEquals(mapAdiacenti.keySet(), corridoio.getMapStanzeAdiacenti().keySet());
		Attrezzo a1 = new Attrezzo("chiave", 1);
		Attrezzo a2 = new Attrezzo("lanterna", 1);
		assertEquals(Arrays.asList(a1, a2), corridoio.getAttrezzi());
	}
}