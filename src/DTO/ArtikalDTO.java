package DTO;

public class ArtikalDTO {
	
	private int id;
	private String naziv;
	private String vrsta;
	private double cena;
	private double PDV;
	private double ukupnaCena;
	
	
	
	
	
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public double getPDV() {
		return PDV;
	}
	public void setPDV(double pDV) {
		PDV = pDV;
	}
	public double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	
	
}
	
