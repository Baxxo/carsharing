package Carrozeria;

import java.sql.Date;

public class Noleggio {

	int codice;
	int i=0;
	Auto auto;
	Socio socio;
	Date inizio;
	Date fine;
	boolean autoRestituita;

	public Noleggio(int codice, Auto auto, Socio socio, Date inizio, Date fine, boolean autoRestituita, int i) {
		this.codice = codice;
		this.i=i;
		this.auto = auto;
		this.socio = socio;
		this.inizio = inizio;
		this.fine = fine;
		this.autoRestituita = autoRestituita;
	}

	public Noleggio() {
		
	}

}
