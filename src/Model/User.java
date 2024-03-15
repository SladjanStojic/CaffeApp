package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id_usera;
	private String ime;
	private String prezime;
	private String userName;
	private String pass;
	
	private String rola;
	
	
	public int getId_usera() {
		return id_usera;
	}
	public void setId_usera(int id_usera) {
		this.id_usera = id_usera;
	}

	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	@Override
	public String toString() {
		return  ime + " "  + prezime;
	}

	
		
	
	

}
