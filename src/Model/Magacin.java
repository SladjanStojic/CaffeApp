package Model;

public class Magacin {
	
	private int id_magacina;
	private int id_artikla;
	private int kolicina;
	private String naziv;
	
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getId_magacina() {
		return id_magacina;
	}
	public void setId_magacina(int id_magacina) {
		this.id_magacina = id_magacina;
	}
	public int getId_artikla() {
		return id_artikla;
	}
	public void setId_artikla(int id_artikla) {
		this.id_artikla = id_artikla;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	
	

}
