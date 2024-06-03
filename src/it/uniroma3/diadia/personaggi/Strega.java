package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Visto che sei stato gentile, ti farò un favore!";
	private static final String MESSAGGIO_NON_SALUTATA = "Sei una persona maleducata, pagherai per questo!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getListStanzeAdiacenti();

		Stanza maxAttrezzi = stanzeAdiacenti.get(0);
		Stanza minAttrezzi = stanzeAdiacenti.get(0);

		for (Stanza s : stanzeAdiacenti) {
			if (s != null) {
				if (s.getNumeroAttrezzi() > maxAttrezzi.getNumeroAttrezzi())
					maxAttrezzi = s;
				if (s.getNumeroAttrezzi() < minAttrezzi.getNumeroAttrezzi())
					minAttrezzi = s;
			}
		}

		if (this.haSalutato()) {
			partita.setStanzaCorrente(maxAttrezzi);
			return MESSAGGIO_SALUTATA + "\n" + partita.getStanzaCorrente().getNome();
		} else {
			partita.setStanzaCorrente(minAttrezzi);
			return MESSAGGIO_NON_SALUTATA + "\n" + partita.getStanzaCorrente().getNome();
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return "AH AH AH il tuo attrezzo ora è mio!";
	}

}
