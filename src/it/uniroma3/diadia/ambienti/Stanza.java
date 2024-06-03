package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */
public class Stanza {

	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private String nome;
	private AbstractPersonaggio personaggio;

	public Stanza(String nome) {
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
		this.nome = nome;
	}

	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (this.stanzeAdiacenti.containsKey(direzione))
			return this.stanzeAdiacenti.get(direzione);
		return null;
	}

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	public List<Direzione> getDirezioni() {
		return new ArrayList<>(this.stanzeAdiacenti.keySet());
	}

	public void impostaStanzaAdiacente(Direzione dir, Stanza stanzaAdiacente) {
		if (this.stanzeAdiacenti.size() < 4)
			this.stanzeAdiacenti.put(dir, stanzaAdiacente);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return new ArrayList<>(this.attrezzi.values());
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return il numero di attrezzi nella stanza.
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @return aggiunge un attrezzo alla stanza.
	 */
	public void addAttrezzo(Attrezzo newAttrezzo) {
		this.attrezzi.put(newAttrezzo.getNome(), newAttrezzo);
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			return true;
		else
			return false;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {

		if (nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			return this.attrezzi.get(nomeAttrezzo);

		return null;

	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		this.attrezzi.remove(attrezzo.getNome());
		return !this.hasAttrezzo(attrezzo.getNome());

	}

	public int getNumeroStanzeAdiacenti() {
		return this.stanzeAdiacenti.size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("\nSei nella stanza: " + this.getNome());
		s.append("\nUscite: ");
		s.append(stanzeAdiacenti.keySet() + " ");
		
		if (this.getPersonaggio() != null) {
			s.append("\n\nPersonaggi: ");
			s.append(this.getPersonaggio().getNome());
		}
		else
			s.append("\nNon c'è nessuno nella stanza!");

		return s.toString();
	}

	public String toStringAttrezzi() {
		StringBuilder s = new StringBuilder();
		if (getNumeroAttrezzi() != 0) {
			s.append("\nAttrezzi nella stanza:");
			s.append("\n");
			s.append(attrezzi.keySet() + " ");

		} else
			s.append("\nNon ci sono attrezzi nella stanza.");

		return s.toString();
	}

	public String getDescrizione() {
		return this.toString();
	}

	public boolean isMagica() {
		if (this.getClass() == StanzaMagica.class)
			return true;
		else
			return false;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public List<Stanza> getListStanzeAdiacenti() {
		return new ArrayList<>(this.stanzeAdiacenti.values());
	}

}