package Model;

public class Artikal {
	
	private int id_artikla;
	private String naziv;
	private int id_vrste;
	private double cena;
	private double pdv;
	private boolean dostupno;
	
	
	
	
	
	public int getId_vrste() {
		return id_vrste;
	}
	public void setId_vrste(int id_vrste) {
		this.id_vrste = id_vrste;
	}
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
	public boolean isDostupno() {
		return dostupno;
	}
	public void setDostupno(boolean dostupno) {
		this.dostupno = dostupno;
	}
	@Override
	public String toString() {
		return  naziv;
	}
	
	
	
	
	

}
