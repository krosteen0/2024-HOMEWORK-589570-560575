package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if(personaggio != null)
			this.io.mostraMessaggio(personaggio.saluta());
		else
			this.io.mostraMessaggio("Chi vuoi salutare? Non c'è nessuno nella stanza!");
		
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
