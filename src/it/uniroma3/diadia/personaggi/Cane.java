package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static String MESSAGGIO_CANE = "Bau bau, ti ho tolto un CFU!";
	private static String CIBO_PREFERITO = "osso";

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);

		return MESSAGGIO_CANE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getGiocatore().getBorsa().removeAttrezzo(CIBO_PREFERITO);
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("giocattolo", 2));
			return "Bau Bau, è il mio preferito!";
		} else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			return "Bau Bau, non mi piace!";
		}
	}

}
