package Model;

import java.sql.Date;

public class Racun {
	
	private int id_racuna;
	private int id_usera;
	private int br_racuna;
	private Date kreiran;
	private Date naplacen;
	private double ukupno;
	private int id_stola;
	
	
	
	
	public int getId_racuna() {
		return id_racuna;
	}
	public void setId_racuna(int id_racuna) {
		this.id_racuna = id_racuna;
	}
	public int getId_usera() {
		return id_usera;
	}
	public void setId_usera(int id_usera) {
		this.id_usera = id_usera;
	}
	public int getBr_racuna() {
		return br_racuna;
	}
	public void setBr_racuna(int br_racuna) {
		this.br_racuna = br_racuna;
	}
	public Date getKreiran() {
		return kreiran;
	}
	public void setKreiran(Date kreiran) {
		this.kreiran = kreiran;
	}
	public Date getNaplacen() {
		return naplacen;
	}
	public void setNaplacen(Date naplacen) {
		this.naplacen = naplacen;
	}
	public double getUkupno() {
		return ukupno;
	}
	public void setUkupno(double ukupno) {
		this.ukupno = ukupno;
	}
	public int getId_stola() {
		return id_stola;
	}
	public void setId_stola(int id_stola) {
		this.id_stola = id_stola;
	}
	
	
	

}
