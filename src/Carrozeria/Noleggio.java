package Carrozeria;

import java.sql.Date;

public class Noleggio {

	int codice;
	String auto;
	String socio;
	Date inizio;
	Date fine;
	boolean autoRestituita;

	public Noleggio(int codice, String auto, String socio, Date inizio, Date fine, boolean autoRestituita) {
		this.codice = codice;
		this.auto = auto;
		this.socio = socio;
		this.inizio = inizio;
		this.fine = fine;
		this.autoRestituita = autoRestituita;
	}

	public Noleggio() {
		
	}

}
