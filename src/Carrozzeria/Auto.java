package Carrozzeria;

public class Auto {

	String targa;
	String marca;
	String modello;
	int costo;
	int i = 0;

	public Auto(String targa, String marca, String modello, int costo, int i) {
		this.targa = targa;
		this.i = i;
		this.marca = marca;
		this.modello = modello;
		this.costo = costo;
	}

	public Auto() {
		
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
