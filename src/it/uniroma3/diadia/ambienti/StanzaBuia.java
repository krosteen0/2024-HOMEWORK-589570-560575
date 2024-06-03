package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoChiave;

	public StanzaBuia(String nome, String attrezzoKey) {
		super(nome);
		this.attrezzoChiave = attrezzoKey;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.hasAttrezzo(attrezzoChiave)) {
			s.append("Qui c'è buio pesto...");
		} else
			s.append(super.toString());
		return s.toString();

	}

	@Override
	public String toStringAttrezzi() {
		StringBuilder s = new StringBuilder();

		if (super.getAttrezzi().size() != 0) {
			if (this.hasAttrezzo(this.attrezzoChiave))
				s.append(super.toStringAttrezzi());
		} else
			s.append("\nLa stanza è vuota.");

		return s.toString();
	}
}