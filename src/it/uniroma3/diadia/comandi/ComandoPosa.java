package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	private final static String NOME = "posa";
	private String nome;
	private IO io;
	
	public ComandoPosa(String nomeAttrezzo) {
		this.nome = nomeAttrezzo;
	}

	public ComandoPosa() {}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {

		if (partita.getGiocatore().getBorsa().getNumeroAttrezzi() != 0) {
			if (nome == null) {
				io.mostraMessaggio("Quale attrezzo vuoi posare nella stanza?");
				this.nome = io.leggiRiga();
			}

			if (partita.getGiocatore().getBorsa().hasAttrezzo(this.nome)) {
				Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(this.nome);

				partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
				partita.getGiocatore().getBorsa().removeAttrezzo(this.nome);

				io.mostraMessaggio(this.nome + " posato!");
			} else
				io.mostraMessaggio("L'attrezzo non è presente nella borsa");
		} else
			io.mostraMessaggio("Non ci sono attrezzi nella tua borsa.");
	}

	@Override
	public void setParametro(String parametro) {
		this.nome = parametro;
	}

	@Override
	public String getParametro() {
		return this.nome;
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