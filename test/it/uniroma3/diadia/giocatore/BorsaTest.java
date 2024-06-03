package it.uniroma3.diadia.giocatore;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;


public class BorsaTest {

	Borsa borsa = new Borsa(10);
	Attrezzo cacciavite;
	Attrezzo martello;
	Attrezzo vite;
	Attrezzo sasso;
	Attrezzo chiave;
	Attrezzo chiodo;
	
	@Before
	public void setUp() {
		cacciavite = new Attrezzo("cacciavite", 2);
		martello = new Attrezzo("martello", 16);
		vite = new Attrezzo("vite", 2);
		sasso = new Attrezzo("sasso", 5);
		chiave = new Attrezzo("chiave", 3);
		chiodo = new Attrezzo("chiodo",4);
	}

	@Test
	public void testAddAttrezzoPesoMinoreDiDieci() {
		assertTrue(borsa.addAttrezzo(cacciavite));

	}
	
	@Test
	public void testAddAttrezzoPesoMaggioreDiDieci() {
		assertFalse(borsa.addAttrezzo(martello));

	}
	
	@Test
	public void testGetPeso() {
		borsa.addAttrezzo(cacciavite);
		assertEquals(cacciavite, borsa.getAttrezzo("cacciavite"));

	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPesoAttrezziStessoPeso() {
		borsa.addAttrezzo(cacciavite);
		borsa.addAttrezzo(vite);
		
		Set<Attrezzo> expected = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		
		expected.add(cacciavite);
		expected.add(vite);
		
		assertArrayEquals(expected.toArray(), borsa.getSortedSetOrdinatoPerPeso().toArray());
	}


	@Test
	public void testGetSortedSetOrdinatoPerPesoAttrezziDiversiPesoDiverso() {
		borsa.addAttrezzo(sasso);
		borsa.addAttrezzo(chiave);
		
		Set<Attrezzo> expected = new TreeSet<>();
		
		expected.add(sasso);
		expected.add(chiave);
		
		assertArrayEquals(expected.toArray(), borsa.getSortedSetOrdinatoPerPeso().toArray());
	}
}