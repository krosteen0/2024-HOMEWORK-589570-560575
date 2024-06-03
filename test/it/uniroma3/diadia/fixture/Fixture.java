package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Fixture {
	public static IOSimulator creaSimulazionePartitaEGiocaEasy(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaHard(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto 		 labirinto = Labirinto.newBuilder("labirinto4.txt").getLabirinto();


		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
	}

	public static Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
