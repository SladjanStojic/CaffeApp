package Model;

public class RacunStavka {
	
	private int id_rs;
	private int id_racuna;
	private int id_artikla;
	private int kolicina;
	private int redni_bs;
	private double trenutna_cena;
	private double trenutni_pdv;
	
	
	
	public int getId_rs() {
		return id_rs;
	}
	public void setId_rs(int id_rs) {
		this.id_rs = id_rs;
	}
	public int getId_racuna() {
		return id_racuna;
	}
	public void setId_racuna(int id_racuna) {
		this.id_racuna = id_racuna;
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
	public int getRedni_bs() {
		return redni_bs;
	}
	public void setRedni_bs(int redni_bs) {
		this.redni_bs = redni_bs;
	}
	public double getTrenutna_cena() {
		return trenutna_cena;
	}
	public void setTrenutna_cena(double trenutna_cena) {
		this.trenutna_cena = trenutna_cena;
	}
	public double getTrenutni_pdv() {
		return trenutni_pdv;
	}
	public void setTrenutni_pdv(double trenutni_pdv) {
		this.trenutni_pdv = trenutni_pdv;
	}
	@Override
	public String toString() {
		return "RacunStavka [kolicina=" + kolicina + ", trenutna_cena=" + trenutna_cena + ", trenutni_pdv="
				+ trenutni_pdv + "]";
	}
	
	
	

}
