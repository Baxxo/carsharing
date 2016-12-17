package Carrozeria;

public class Auto {

	String targa;
	String marca;
	String modello;
	int costo;

	public Auto(String targa, String marca, String modello, int costo) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.costo = costo;
	}
	
	public Auto(){
		
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

}
