package Model;

public class Sto {
	
	private int id_stola;
	private String nazivStola;
	private int x;
	private int y;
	private boolean slobodan;
	private int id_racuna;
	
	
	
	
	
	
	
	public int getId_racuna() {
		return id_racuna;
	}
	public void setId_racuna(int id_racuna) {
		this.id_racuna = id_racuna;
	}
	public boolean isSlobodan() {
		return slobodan;
	}
	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}
	public String getNazivStola() {
		return nazivStola;
	}
	public void setNazivStola(String nazivStola) {
		this.nazivStola = nazivStola;
	}
	public int getId_stola() {
		return id_stola;
	}
	public void setId_stola(int id_stola) {
		this.id_stola = id_stola;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
