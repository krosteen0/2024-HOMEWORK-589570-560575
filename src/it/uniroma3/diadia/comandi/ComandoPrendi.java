package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	private final static String NOME = "prendi";
	private String nomeAttrezzo;
	private IO io;
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}

	public ComandoPrendi() {}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {

		if (this.nomeAttrezzo == null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere dalla stanza?");
			this.nomeAttrezzo = io.leggiRiga();
		}

		if (!(partita.getStanzaCorrente().getClass() == StanzaBuia.class
				&& !partita.getStanzaCorrente().hasAttrezzo("torcia"))) {
			if (partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo)) {
				if (partita.getGiocatore().getBorsa().getPeso() + partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo)
						.getPeso() <= partita.getGiocatore().getBorsa().getPesoMax()) {
					Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);

					partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere);
					partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);

					io.mostraMessaggio(this.nomeAttrezzo + " preso!");
				} else
					io.mostraMessaggio("Impossibile prendere l'attrezzo, è troppo pesante!");
			} else
				io.mostraMessaggio("L'attrezzo non è presente nella stanza");
		} else
			io.mostraMessaggio("Qui c'è buio pesto...Serve un lanterna...");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
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