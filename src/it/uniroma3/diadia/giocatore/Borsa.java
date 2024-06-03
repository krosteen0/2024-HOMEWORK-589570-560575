package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa {

	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoAttuale;

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
		this.pesoAttuale = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && pesoAttuale + attrezzo.getPeso() <= pesoMax) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			this.pesoAttuale += attrezzo.getPeso();
			return true;
		}
		return false;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo != null)
			return this.attrezzi.get(nomeAttrezzo);
		else
			return null;
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public int getPeso() {
		return this.pesoAttuale;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo != null)
			return this.attrezzi.containsKey(nomeAttrezzo) == true;
		return false;
	}

	public void removeAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo != null) {
			if (nomeAttrezzo != null)
				this.pesoAttuale -= this.getAttrezzo(nomeAttrezzo).getPeso();
			this.attrezzi.remove(nomeAttrezzo);
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("\nAttrezzi nella borsa:" + " ( " + this.pesoAttuale + "Kg / " + this.pesoMax + "Kg )");
			s.append("\n");
			s.append("set per nome: " + this.getContenutoOrdinatoPerNome().toString());
			s.append("\nlista per peso: " + this.getContenutoOrdinatoPerPeso().toString());
			s.append("\nmappa per peso: " + this.getContenutoRaggruppatoPerPeso().toString());
			s.append("\nset per peso: " + this.getSortedSetOrdinatoPerPeso().toString());
		} else
			s.append("\nLa borsa è vuota.");
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = new ArrayList<>();
		lista.addAll(attrezzi.values());
		Collections.sort(lista, new ComparatoreAttrezziPerPeso());
		return lista;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ordinato = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPeso());
		ordinato.addAll(this.attrezzi.values());
		return ordinato;
	}

	public SortedSet<Attrezzo>  getContenutoOrdinatoPerNome() {
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();

		for (Attrezzo a : this.attrezzi.values()) {
			if (mappa.containsKey(a.getPeso())) {
				mappa.get(a.getPeso()).add(a);

			} else {
				Set<Attrezzo> s = new HashSet<Attrezzo>();
				s.add(a);
				mappa.put(a.getPeso(), s);
			}
		}
		return mappa;
	}
}