package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;

public class ComandoVai extends AbstractComando {
	
	private final static String NOME = "vai";
	private String direzione;
	private IO io;
	
	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}

	public ComandoVai() {}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		

		if (this.direzione == null) {
			io.mostraMessaggio("Dove vuoi andare?");
			this.direzione = io.leggiRiga();
		}

		try {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(this.getParametro())));
			} catch(IllegalArgumentException e) {
				this.io.mostraMessaggio("Direzione inesistente");
				return;
			}

		if (partita.getStanzaCorrente() != null)
			io.mostraMessaggio(partita.getStanzaCorrente().getNome());

		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);

	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public String getNome() {
		return NOME;
	}
	
	public void setIO(IO io) {
		this.io = io;
		
	}
	
	public IO getIO() {
		return this.io;
	}
}