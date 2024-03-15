package Kontroler;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JToggleButton;

import org.mindrot.jbcrypt.BCrypt;

import com.toedter.calendar.JDateChooser;

import Broker.Broker;
import DTO.ArtikalDTO;
import DTO.MagacinDTO;
import DTO.RacunDTO;
import DTO.StatistikaDTO;
import Model.Artikal;
import Model.JTogleButtonSTO;
import Model.Korpa;
import Model.Magacin;
import Model.Racun;
import Model.Role;
import Model.Stavka;
import Model.Sto;
import Model.User;
import Model.Vrsta;

public class Kontroler {
	
	private static Kontroler instanca;
	
	private Kontroler() {
		
	}
	public static Kontroler getInstanca() {
		if(instanca==null) {
			instanca= new Kontroler();
		}
		return instanca;
	}
	public void upisiArtikalUBazu(String naziv, int id_vrste, Double cena, Double pdv, Boolean uProdaji) {
		// TODO Auto-generated method stub
		Artikal a= new Artikal();
		a.setNaziv(naziv);
		a.setId_vrste(id_vrste);
		a.setCena(cena);
		a.setPdv(pdv);
		a.setDostupno(uProdaji);
		
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().upisiArtikalUBazu(a);
		Broker.getInstanca().zatvoriKonekciju();
		
	}
	public List<ArtikalDTO> vratiArtikleIzBaze() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<ArtikalDTO> listaArtikala= Broker.getInstanca().vratiArtikleIzBaze();
		Broker.getInstanca().zatvoriKonekciju();
		return listaArtikala;
	}
	public void izmeniArtikal(int id_artikla, String naziv, int id_vrste, double parseDouble, double parseDouble2) {
		// TODO Auto-generated method stub
		Artikal a=new Artikal();
		a.setId_artikla(id_artikla);
		a.setNaziv(naziv);
		a.setId_vrste(id_vrste);
		a.setCena(parseDouble);
		a.setPdv(parseDouble2);
		
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().izmeniArtikalUBazi(a);
		Broker.getInstanca().otvoriKonekciju();
		
	}
	public List<Artikal> vratiIzBazeBezAlkoholnaPica() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Artikal> listaBezalkoholnih= Broker.getInstanca().vratiBezalkoholna();
		Broker.getInstanca().zatvoriKonekciju();
		return listaBezalkoholnih;
	}
	public List<Artikal> vratiIzBazeTopleNapitke() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Artikal> listaTopliNapici= Broker.getInstanca().vratiTopleNapitke();
		Broker.getInstanca().zatvoriKonekciju();
		return listaTopliNapici;
	}
	public List<Artikal> vratiIzBazeAlkoholnaPica() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Artikal> listaAlkoholnihPica= Broker.getInstanca().vratiAlkoholnaPica();
		Broker.getInstanca().zatvoriKonekciju();
		return listaAlkoholnihPica;
		}
	public List<Artikal> vratiVodeIzBaze() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Artikal> listaVoda= Broker.getInstanca().vratiVodeIzBaze();
		Broker.getInstanca().zatvoriKonekciju();
		return listaVoda;
	}
	public void unesiUMagacinIliUpdate(int id_artikla,int kolicina) {
		// TODO Auto-generated method stub
		
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().UnesiUMagacinIliUpdate(id_artikla, kolicina);
		Broker.getInstanca().zatvoriKonekciju();
		
	}

	public List<Vrsta> vratiVrsteIzBaze() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Vrsta> listaVrsta=Broker.getInstanca().vratiVrsteIzBaze();
		Broker.getInstanca().zatvoriKonekciju();
		return listaVrsta;
	}
	public List<Artikal> vratiArtikleZaVrstu(int id_vrste) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Artikal>listaPojedinacnih=Broker.getInstanca().vratiPojedinacneArtikle(id_vrste);
		Broker.getInstanca().zatvoriKonekciju();
		return listaPojedinacnih;
	}
	public List<MagacinDTO> vratiPodatkeZaMagacin() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<MagacinDTO>podaciZaMagacin=Broker.getInstanca().vratiPodatkeZaMagacin();
		Broker.getInstanca().zatvoriKonekciju();
		return podaciZaMagacin;
	}
	public List<Role> vratiRole() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Role>listaRola=Broker.getInstanca().vratiRole();
		Broker.getInstanca().zatvoriKonekciju();
		return listaRola;
	}
	public void sacuvajUsera(User u, int id_rola) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().unesiUsera(u,id_rola);
		Broker.getInstanca().zatvoriKonekciju();
	}

	public int upisiStoUBazu(String nazivStola, int x, int y) {
		// TODO Auto-generated method stub
		Sto s= new Sto();
		s.setNazivStola(nazivStola);
		s.setX(x);
		s.setY(y);
		Broker.getInstanca().otvoriKonekciju();
		int tempId=Broker.getInstanca().upisiStoUBazu(s);
		Broker.getInstanca().zatvoriKonekciju();
		return tempId;
	}

	public List<Sto> vratiListuStolova() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Sto> stolovi= Broker.getInstanca().vratiStolove();
		Broker.getInstanca().zatvoriKonekciju();
		return stolovi;
	}
	public void updateStoUBazi(int id_stola, String nazivStola, int x, int y) {
		// TODO Auto-generated method stub
		Sto s= new Sto();
		s.setNazivStola(nazivStola);
		s.setId_stola(id_stola);
		s.setX(x);
		s.setY(y);
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().updateStola(s);
		Broker.getInstanca().zatvoriKonekciju();
	}
	public void obrisiSto(int id_stola) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().izbrisiSto(id_stola);
		Broker.getInstanca().zatvoriKonekciju();
	}
	public int upisiRacun( Korpa korpa, int brojRacuna, int id_stola, int id ,double ukupnaCenaSvih) {
		// TODO Auto-generated method stub
		Racun r= new Racun();
		r.setBr_racuna(brojRacuna);
		r.setId_stola(id_stola);
		r.setId_usera(id);
		r.setUkupno(ukupnaCenaSvih);
		r.setNaplacen(null);
		
		Broker.getInstanca().otvoriKonekciju();
		int tempId=Broker.getInstanca().upisiRacun(r,korpa);
		Broker.getInstanca().zatvoriKonekciju();
		return tempId;
		
	}
	public List<Artikal> vratiArtikleZaVrstuKojiSuDostupnin(int id_vrste) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Artikal>dostpuniArtikli=Broker.getInstanca().vratiDostupneArtikle(id_vrste);
		Broker.getInstanca().zatvoriKonekciju();
		return dostpuniArtikli;
	}
	public void updateMagacina(int id_artikla, int kolicina) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().updateMagacina(id_artikla,kolicina);
		Broker.getInstanca().zatvoriKonekciju();
	}
	
	
	
	public List<RacunDTO> vratiPodatkeZaRacunTabelu(int id_stola) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<RacunDTO>lista=Broker.getInstanca().vratiPodatkeZaRacunTabelu(id_stola);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	
	
	public void dodajStavkuNaRacun(int id_racuna, Korpa korpa) {
		
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().upisiStavku(id_racuna,korpa);
		Broker.getInstanca().zatvoriKonekciju();
	}
	public double vratiUkupanIznosSaRacuna(int id_racuna) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		double ukupanIznos=Broker.getInstanca().vratiUkupanIznosZaRacun(id_racuna);
		Broker.getInstanca().zatvoriKonekciju();
		return ukupanIznos;
	}
	public void updateRacun(int id_racuna) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().updateRacunUMagacinu(id_racuna);
		Broker.getInstanca().zatvoriKonekciju();
		
	}
	public List<Racun> vratiSveRacune() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Racun> listaRacuna=Broker.getInstanca().vratiSveRacune();
		Broker.getInstanca().zatvoriKonekciju();
		return listaRacuna;
	}
	public User proveriIvratiUsera(User u) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		User proverenUser=Broker.getInstanca().proveriIVratiUseraIzBaze(u);
		Broker.getInstanca().zatvoriKonekciju();
		return proverenUser;
	}
	public void registrujNovogKorisnika(User u) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Role rola=Broker.getInstanca().vratiRoluPoImenu("ADMIN");
		String pass2=BCrypt.hashpw(u.getPass(), BCrypt.gensalt());
		u.setPass(pass2);
		int id=Broker.getInstanca().registrujNovogKorisnika(u);
		Broker.getInstanca().dodeliRoluKorisniku(id,rola.getId_role());
		Broker.getInstanca().zatvoriKonekciju();
	}
	public List<User> vratiKonobareIzBaze() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<User>listaKonobara=Broker.getInstanca().vratiKonobareIzBaze();
		Broker.getInstanca().zatvoriKonekciju();
		return listaKonobara;
	}
	public List<StatistikaDTO> vratiPodatkeZaStatistiku(int id_konobara, JDateChooser dc1) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<StatistikaDTO>lista=Broker.getInstanca().vratiStatistiku(id_konobara,dc1);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public void updateRacun2(int id_racuna) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().updateRacunUMagacinu2(id_racuna);
		Broker.getInstanca().zatvoriKonekciju();
	}
	public void azurirajNovuCenuRacuna(int id_racuna, double novaUkupnaCenaRacuna) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().azurirajNovuCenuRacuna(id_racuna,novaUkupnaCenaRacuna);
		Broker.getInstanca().zatvoriKonekciju();
	}
	public String vratiImeUsera(int id) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		String ime=Broker.getInstanca().vratiImeUsera(id);
		Broker.getInstanca().zatvoriKonekciju();
		return ime;
	}
	public String vratiPrezimeUsera(int id) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		String prezime=Broker.getInstanca().vratiPrezimeUsera(id);
		Broker.getInstanca().zatvoriKonekciju();
		return prezime;
	}
	public int vratiPoslednjiRedniBroj(int id_racuna) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int poslednjiBroj=Broker.getInstanca().vratiNajveciRedniBroj(id_racuna);
		Broker.getInstanca().zatvoriKonekciju();
		return poslednjiBroj;
	}
	public boolean daLiJeNaplacenRacun(int id_stola) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		boolean naplacen=Broker.getInstanca().DaLiJeNaplacen(id_stola);
		Broker.getInstanca().zatvoriKonekciju();
		return naplacen;
	}
	public int daLiJeStoCrven(int id) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int crven=Broker.getInstanca().daLiJeStoCrven(id);
		Broker.getInstanca().zatvoriKonekciju();
		return crven;
	}
	public Timestamp vratiVremeNaplate(int id_racuna) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Timestamp vremeNaplate=Broker.getInstanca().vratiVreme(id_racuna);
		Broker.getInstanca().zatvoriKonekciju();
		return vremeNaplate;
	}
	
	
	}
	
	
	

