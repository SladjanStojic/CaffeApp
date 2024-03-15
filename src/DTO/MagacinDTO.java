package DTO;

public class MagacinDTO {
	
	private int id_artikla;
	private String naziv;
	private int stanje;
	private double cena;
	private boolean dostupno;
	public int getId_artikla() {
		return id_artikla;
	}
	public void setId_artikla(int id_artikla) {
		this.id_artikla = id_artikla;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getStanje() {
		return stanje;
	}
	public void setStanje(int stanje) {
		this.stanje = stanje;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public boolean isDostupno() {
		return dostupno;
	}
	public void setDostupno(boolean dostupno) {
		this.dostupno = dostupno;
	}
	
	
	


}
