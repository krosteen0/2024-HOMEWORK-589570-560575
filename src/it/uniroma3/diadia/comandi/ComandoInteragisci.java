package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {

	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private final static String NOME = "interagisci";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if (personaggio != null) {
			io.mostraMessaggio(personaggio.agisci(partita));

		} else
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
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
