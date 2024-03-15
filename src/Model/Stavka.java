package Model;

public class Stavka {
	
	private int redni_bs;
	private String naziv;
	private int kolicina;
	private double cena;
	private double pdv;
	private double ukupno;
	
	
	
	
	public double getUkupno() {
		return ukupno;
	}
	public void setUkupno(double ukupno) {
		this.ukupno = ukupno;
	}
	public int getRedni_bs() {
		return redni_bs;
	}
	public void setRedni_bs(int redni_bs) {
		this.redni_bs = redni_bs;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public double getPdv() {
		return pdv;
	}
	public void setPdv(double pdv) {
		this.pdv = pdv;
	}
	
	
	

}
