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

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public boolean isAutoRestituita() {
		return autoRestituita;
	}

	public void setAutoRestituita(boolean autoRestituita) {
		this.autoRestituita = autoRestituita;
	}

}
