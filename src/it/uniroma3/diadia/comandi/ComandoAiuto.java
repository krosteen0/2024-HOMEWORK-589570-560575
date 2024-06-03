package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "fine", "prendi", "posa", "guarda"};
	private final static String NOME = "aiuto";
	
	private IO io;
	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Scrivi uno di questi comandi:");
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
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
