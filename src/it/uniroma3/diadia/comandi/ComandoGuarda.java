package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	private final static String NOME = "guarda";
	private IO io;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		/**
		 * Restituisce una rappresentazione stringa della stanza corrente, stampadone la
		 * descrizione, le uscite e gli eventuali attrezzi contenuti
		 * 
		 * @return la rappresentazione stringa
		 */
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());

		io.mostraMessaggio(partita.getStanzaCorrente().toStringAttrezzi());

		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());

		io.mostraMessaggio("\n" + partita.getGiocatore().getCfu() + "CFU disponibili.");

	}

	@Override
	public String getNome() {
		return NOME;
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