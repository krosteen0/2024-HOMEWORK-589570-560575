package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaProtected extends StanzaProtected {

	final static private int SOGLIA_MAGICA_DEFAULT = 3;

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public void addAttrezzo(Attrezzo attrezzo) {

		this.contatoreAttrezziPosati++;

		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		super.addAttrezzo(attrezzo);
	}

	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {

		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();

		attrezzo = new Attrezzo(nomeInvertito.toString(), attrezzo.getPeso() * 2);

		return attrezzo;
	}

}