package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private Direzione nomeDirezioneBloccata;
	private String attrezzoChiave;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoKey) {
		super(nome);
		this.nomeDirezioneBloccata = direzioneBloccata;
		this.attrezzoChiave = attrezzoKey;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (!super.hasAttrezzo(attrezzoChiave) && nomeDirezioneBloccata.equals(direzione))
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("\nSei nella stanza: " + getNome());
		s.append("\nUscite: ");
		s.append("\n");
	
		
		if (!super.hasAttrezzo(attrezzoChiave))
			s.append("La direzione " + this.nomeDirezioneBloccata + " è bloccata! Prendi " + attrezzoChiave + " per sbloccarla");
		else {
			s.append(super.toString());
			s.append("\n");
		}
		
		if (this.getPersonaggio() != null) {
			s.append("\n\nPersonaggi: ");
			s.append(this.getPersonaggio().getNome());
		}
		else
			s.append("\nNon c'è nessuno nella stanza!");
		
		return s.toString();

	}
}
