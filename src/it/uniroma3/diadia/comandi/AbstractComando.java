package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	
	private static final String NOME = "AbstractComando";
	private String parametro;

	abstract public void esegui(Partita partita);
	
	@Override
	public void setParametro(String parametro) {
		this.parametro  = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
