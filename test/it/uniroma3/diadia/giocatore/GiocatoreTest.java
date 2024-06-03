package it.uniroma3.diadia.giocatore;
import static org.junit.Assert.*;

import org.junit.Test;


public class GiocatoreTest {
	
	Giocatore giocatore = new Giocatore();
	
	@Test
	public void testGetCfuDefault() {
		assertEquals(20, giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(3);
		assertEquals(3, giocatore.getCfu());
	}

	@Test
	public void testGetBorsaDefault() {
		assertNotNull(giocatore.getBorsa());
	}
}
