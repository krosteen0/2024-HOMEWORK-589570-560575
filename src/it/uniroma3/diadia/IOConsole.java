package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {

	Scanner scannerDiLinee;

	public IOConsole(Scanner s) {
		this.scannerDiLinee = s;
	}

	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}