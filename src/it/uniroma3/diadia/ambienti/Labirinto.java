package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaCorrente;
	private Stanza stanzaFinale;

	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaCorrente = c.getStanzaIniziale();
		this.stanzaFinale = c.getStanzaVincente();
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanza) {
		this.stanzaCorrente = stanza;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaFinale;
	}

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaFinale = stanza;
	}
	
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> stanze;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.stanze = new HashMap<String, Stanza>();
		}

		public Map<String, Stanza> getStanze() {
			return stanze;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.stanze;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza s = new Stanza(stanzaIniziale);
			this.ultimaStanzaAggiuntaEAggiorna(s);
			this.labirinto.setStanzaCorrente(s);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(s);
			this.ultimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public LabirintoBuilder addStanza(String stanza) {
			Stanza s = new Stanza(stanza);
			this.ultimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
			Attrezzo a = new Attrezzo(attrezzo, peso);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, Direzione direzione) {
			Stanza c = this.stanze.get(stanzaCorrente);
			Stanza a = this.stanze.get(stanzaAdiecente);
			c.impostaStanzaAdiacente(direzione, a);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
			Stanza stanza = new StanzaMagica(nome, sogliaMagica);
			this.ultimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
			this.ultimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
			Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
			this.ultimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public void ultimaStanzaAggiuntaEAggiorna(Stanza stanza) {
			this.ultimaStanzaAggiunta = stanza;
			this.stanze.put(stanza.getNome(), stanza);
		}
		
		public LabirintoBuilder addCane(String nome, String presentazione) {
			Cane c = new Cane(nome, presentazione);
			this.ultimaStanzaAggiunta.setPersonaggio(c);
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago m = new Mago(nome, presentazione, attrezzo);
			this.ultimaStanzaAggiunta.setPersonaggio(m);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione) {
			Strega s = new Strega(nome, presentazione);
			this.ultimaStanzaAggiunta.setPersonaggio(s);
			return this;
		}
	}
}
