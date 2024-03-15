package Model;

public class Vrsta {
	
	private int id_vrste;
	private String naziv;
	
	
	public int getId_vrste() {
		return id_vrste;
	}
	public void setId_vrste(int id_vrste) {
		this.id_vrste = id_vrste;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	@Override
	public String toString() {
		return naziv ;
	}
	
	
	

}
