package Carrozeria;

public class Socio {

	String cf;
	String cognome;
	String nome;
	String indirizzo;
	String telefono;
	int i=0;
	
	public Socio (String cf, String cognome, String nome, String indirizzo, String telefono, int i){
		this.cf=cf;
		this.cognome = cognome;
		this.nome=nome;
		this.indirizzo=indirizzo;
		this.telefono=telefono;
		this.i=i;
	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognonome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
