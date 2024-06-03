package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	private final static String NOME = "non valido";
	private IO io;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Il comando inserito non è valido! Riprovare.");
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