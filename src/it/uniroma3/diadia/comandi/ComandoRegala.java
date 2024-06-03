package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private String nomeAttrezzo;
	private IO io;

	public ComandoRegala(String attrezzo) {
		this.nomeAttrezzo = attrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		if (partita.getStanzaCorrente().getPersonaggio() == null) {
			io.mostraMessaggio("Non c'è nessuno a cui fare un regalo!");
		} else {
			AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
			Attrezzo attrezzo;
			
			if (this.nomeAttrezzo == null) {
				io.mostraMessaggio("Quale attrezzo vuoi regalare?");
				this.nomeAttrezzo = io.leggiRiga();
			}
			
			if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))
				attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			else {
				io.mostraMessaggio("Non sei in possesso di questo attrezzo!");
				return;
			}

			io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
		}
	}

	@Override
	public void setIO(IO io) {
		this.io = io;

	}

	@Override
	public IO getIO() {
		return this.io;
	}

}
