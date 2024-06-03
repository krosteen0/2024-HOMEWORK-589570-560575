package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO & 589570 & 560575
 * 
 * @version 4.0
 */

public class DiaDia {

	public static final String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;

	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do {
			istruzione = io.leggiRiga();

		} while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {

		Comando comandoDaEseguire;

		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(io);

		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);

		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			partita.setFinita();
		}

		if (this.partita.getGiocatore().getCfu() == 0) {
			io.mostraMessaggio("Hai esaurito i CFU...");
			partita.setFinita();
		}

		return this.partita.isFinita();
	}

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);

		Labirinto labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();

		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		scanner.close();
	}
}