package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	private final static String NOME = "fine";
	public static final String MESSAGGIO_FINE = "Grazie di aver giocato!";
	private IO io;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(MESSAGGIO_FINE);
		partita.setFinita();
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
