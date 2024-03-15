package Model;

import java.util.ArrayList;
import java.util.List;

public class Korpa {
	
	private List<RacunStavka>lista= new ArrayList<>();
	
	public void dodaj(RacunStavka rs) {
		lista.add(rs);
	}
	public List<RacunStavka> getList() {
		return lista;
	}
	@Override
	public String toString() {
		return "Korpa [lista=" + lista + "]";
	}
	
	
}
