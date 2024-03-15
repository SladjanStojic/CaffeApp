package Broker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;
import com.toedter.calendar.JDateChooser;

import DTO.ArtikalDTO;
import DTO.MagacinDTO;
import DTO.RacunDTO;
import DTO.StatistikaDTO;
import Model.Artikal;
import Model.Korpa;
import Model.Magacin;
import Model.Racun;
import Model.RacunStavka;
import Model.Role;
import Model.Stavka;
import Model.Sto;
import Model.User;
import Model.Vrsta;
import Model.userRola;

public class Broker {
	private static Broker instanca;
	private Connection con;
	
	private Broker() {
		ucitajDrajver();
	}
	private void ucitajDrajver() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
	}
	public static Broker getInstanca() {
		if(instanca==null) {
			instanca=new Broker();
		}
		return instanca;
	}
	public void otvoriKonekciju() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/caffe_app","root","");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	public void zatvoriKonekciju() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	public void upisiArtikalUBazu(Artikal a) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO artikal (naziv,cena,id_vrste,dostupan,pdv) VALUES (?,?,?,?,?) ";
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, a.getNaziv());
			ps.setDouble(2, a.getCena());
			ps.setInt(3, a.getId_vrste());
			ps.setBoolean(4, a.isDostupno());
			ps.setDouble(5, a.getPdv());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public List<ArtikalDTO> vratiArtikleIzBaze() {
		// TODO Auto-generated method stub
		String sql="SELECT a.id_artikla, a.naziv, v.naziv,a.cena,a.pdv, a.cena+(a.cena*a.pdv/100) AS 'Ukupna Cena'"
				+ " FROM `artikal` a JOIN `vrsta_pica` v ON (a.id_vrste= v.id_vrste)"
				+ " GROUP BY a.id_artikla, a.naziv, v.naziv,a.cena,a.pdv;";
		List<ArtikalDTO> lista= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				ArtikalDTO a= new ArtikalDTO();
				a.setId(rs.getInt("id_artikla"));
				a.setNaziv(rs.getString("naziv"));
				a.setVrsta(rs.getString("v.naziv"));
				a.setCena(rs.getDouble("cena"));
				a.setPDV(rs.getDouble("pdv"));
				a.setUkupnaCena(rs.getDouble("Ukupna Cena"));
				lista.add(a);
			
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lista;
	}
	public void izmeniArtikalUBazi(Artikal a) {
		// TODO Auto-generated method stub
		String sql="UPDATE artikal SET naziv=? , id_vrste=? , cena=? , pdv=? WHERE id_artikla=?";
		
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, a.getNaziv());
			ps.setInt(2, a.getId_vrste());
			ps.setDouble(3, a.getCena());
			ps.setDouble(4, a.getPdv());
			ps.setInt(5, a.getId_artikla());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public List<Artikal> vratiBezalkoholna() {
		// TODO Auto-generated method stub
		String sql="SELECT a.id_artikla, a.naziv\r\n"
				+ "FROM `artikal` a \r\n"
				+ "WHERE vrsta='Bezalkoholno';";
		List<Artikal> lista= new ArrayList<>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Artikal a=new Artikal();
				a.setId_artikla(rs.getInt("id_artikla"));
				a.setNaziv(rs.getString("naziv"));
				lista.add(a);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lista;
		
	}
	public List<Artikal> vratiTopleNapitke() {
		// TODO Auto-generated method stub
		String sql="SELECT a.id_artikla, a.naziv FROM `artikal` a WHERE vrsta='Topli Napici';";
		List<Artikal>listaToplihNapitaka= new ArrayList<>();
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Artikal a=new Artikal();
				a.setId_artikla(rs.getInt("id_artikla"));
				a.setNaziv(rs.getString("naziv"));
				listaToplihNapitaka.add(a);
				System.out.println(listaToplihNapitaka);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaToplihNapitaka;
	}
	public List<Artikal> vratiAlkoholnaPica() {
		// TODO Auto-generated method stub
		String sql="SELECT a.id_artikla, a.naziv FROM `artikal` a WHERE vrsta='Alkohol';";
		List<Artikal>listaAlkoholnihPica= new ArrayList<>();
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Artikal a= new Artikal();
				a.setId_artikla(rs.getInt("id_artikla"));
				a.setNaziv(rs.getString("naziv"));
				listaAlkoholnihPica.add(a);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaAlkoholnihPica;
	}
	public List<Artikal> vratiVodeIzBaze() {
		// TODO Auto-generated method stub
		String sql="SELECT a.id_artikla, a.naziv FROM `artikal` a WHERE vrsta='Voda';";
		List<Artikal>listaVoda= new ArrayList<>();
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Artikal a= new Artikal();
				a.setId_artikla(rs.getInt("id_artikla"));
				a.setNaziv(rs.getString("naziv"));
				listaVoda.add(a);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaVoda;
	}
	public void UnesiUMagacinIliUpdate(int id_artikla, int kolicina) {
		// TODO Auto-generated method stub
		
		String sql="SELECT * FROM `magacin` WHERE id_artikla=?";
		try {
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id_artikla);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			 ps=con.prepareStatement("UPDATE magacin SET kolicina = kolicina + ? WHERE id_artikla = ?;");
			 ps.setInt(1, kolicina);
			 ps.setInt(2, id_artikla);
			 
				ps.executeUpdate();
		}
		else {
			ps=con.prepareStatement("INSERT INTO magacin (id_artikla,kolicina) VALUES (?,?)");
			ps.setInt(1, id_artikla);
			ps.setInt(2, kolicina);
			
			ps.executeUpdate();
		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public List<Vrsta> vratiVrsteIzBaze() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM `vrsta_pica`;";
		List<Vrsta> listaVrsta= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Vrsta v= new Vrsta();
				v.setId_vrste(rs.getInt("id_vrste"));
				v.setNaziv(rs.getString("naziv"));
				
				listaVrsta.add(v);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaVrsta;
	}
	public List<Artikal> vratiPojedinacneArtikle(int id_vrste) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM artikal a"
				+ " INNER JOIN vrsta_pica v ON a.id_vrste = v.id_vrste "
				+ "AND v.id_vrste = ?";
		
		List<Artikal> listaPojedinacnih= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_vrste);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Artikal a= new Artikal();
				a.setNaziv(rs.getString("naziv"));
				a.setId_artikla(rs.getInt("id_artikla"));
				a.setCena(rs.getDouble("cena"));
				a.setPdv(rs.getDouble("pdv"));
				a.setDostupno(rs.getBoolean("dostupan"));
			
				listaPojedinacnih.add(a);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listaPojedinacnih;
	}
	public List<MagacinDTO> vratiPodatkeZaMagacin() {
		// TODO Auto-generated method stub
		String sql="SELECT a.id_artikla,a.naziv,m.kolicina,a.cena+(a.cena*a.pdv/100) AS 'cena', a.dostupan\r\n"
				+ "FROM `magacin` m\r\n"
				+ "JOIN `artikal`a ON (m.id_artikla=a.id_artikla) \r\n"
				+ "GROUP BY a.id_artikla,a.naziv,m.kolicina,a.cena, a.dostupan;";
		List<MagacinDTO> podaciZaMagacin= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MagacinDTO m= new MagacinDTO();
				m.setId_artikla(rs.getInt("a.id_artikla"));
				m.setNaziv(rs.getString("a.naziv"));
				m.setStanje(rs.getInt("m.kolicina"));
				m.setCena(rs.getDouble("cena"));
				m.setDostupno(rs.getBoolean("dostupan"));
				
				podaciZaMagacin.add(m);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return podaciZaMagacin;
	}
	public List<Role> vratiRole() {
		// TODO Auto-generated method stub
		String sql="SELECT r.id_role,r.naziv FROM `role` r";
		List<Role> listaRola= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Role r= new Role();
				r.setId_role(rs.getInt("id_role"));
				r.setRola(rs.getString("naziv"));
				
				listaRola.add(r);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaRola;
	}
	public void unesiUsera(User u, int id_rola) {
		// TODO Auto-generated method stub
		int id_user=0;
		try {
			String sql="INSERT into user (ime,prezime,user,pass) VALUES (?,?,?,?)";
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getIme());
			ps.setString(2, u.getPrezime());
			ps.setString(3, u.getUserName());
			ps.setString(4, u.getPass());
			int affectedRows=ps.executeUpdate();
			
			
			try(ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					id_user=genKljuc.getInt(affectedRows);
				}
			}
			String rola="INSERT into user_role (id_user,id_rola) VALUES (?,?)";
			ps=con.prepareStatement(rola);
			ps.setInt(1, id_user);
			ps.setInt(2, id_rola );
			ps.executeUpdate();
			
			con.commit();
		}catch (SQLException e) {
				// TODO: handle exception
			System.out.println(e);
		
			}
		
		}
	  public int upisiStoUBazu(Sto s) {
	        String sql = "INSERT INTO sto (naziv_stola, x, y) VALUES (?, ?, ?)";
	        int generatedId = 0;
	        
	        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            ps.setString(1, s.getNazivStola());
	            ps.setInt(2, s.getX());
	            ps.setInt(3, s.getY());
	            
	            ps.executeUpdate();
	            
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    generatedId = rs.getInt(1); 
	                }
	                System.out.println(generatedId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        }
	        
	       return generatedId;
	        
	    }
	
	public List<Sto> vratiStolove() {
		// TODO Auto-generated method stub
		String sql="SELECT sto.*, racun.id_racuna, racun.naplacen FROM sto LEFT JOIN racun ON sto.id_stola = racun.id_stola;";
		List<Sto> listaStolova= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Sto s= new Sto();
				s.setId_stola(rs.getInt("id_stola"));
				s.setNazivStola(rs.getString("naziv_stola"));
				s.setX(rs.getInt("x"));
				s.setY(rs.getInt("y"));
				s.setId_racuna(rs.getInt("id_racuna"));
				
				listaStolova.add(s);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaStolova;
	}
	public void updateStola(Sto s) {
		// TODO Auto-generated method stub
		String sql="UPDATE sto SET naziv_stola=? , x=?, y=? WHERE id_stola=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, s.getNazivStola());
			ps.setInt(2, s.getX());
			ps.setInt(3, s.getY());
			ps.setInt(4, s.getId_stola());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void izbrisiSto(int id_stola) {
		// TODO Auto-generated method stub
		String sql="DELETE  FROM sto WHERE id_stola = ?";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, id_stola);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int upisiRacun(Racun r, Korpa korpa) {
		// TODO Auto-generated method stub
		int id_racuna = 0;
		try {
			String sql="INSERT into racun (br_racuna,id_usera,id_stola,ukupno) VALUES (?,?,?,?)";
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, r.getBr_racuna());
			ps.setInt(2, r.getId_usera());
			ps.setInt(3, r.getId_stola());
			ps.setDouble(4, r.getUkupno());
			
			
			
			
			int affectedRows=ps.executeUpdate();
			id_racuna=0;
			try (ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					id_racuna=genKljuc.getInt(1);
				}
		
			} 
			String racunStavke="INSERT into racun_stavke (id_racuna,id_artikla,kolicina,redni_bs,trenutna_cena,trenutni_pdv) VALUES (?,?,?,?,?,?)";
			
			for(RacunStavka rs:korpa.getList()) {
				ps=con.prepareStatement(racunStavke);
				ps.setInt(1, id_racuna);
				ps.setInt(2, rs.getId_artikla());
				ps.setInt(3, rs.getKolicina());
				ps.setInt(4, rs.getRedni_bs());
				ps.setDouble(5, rs.getTrenutna_cena());
				ps.setDouble(6, rs.getTrenutni_pdv());
				
				ps.executeUpdate();
			}
			con.commit();
			
		}catch (SQLException e) {
				// TODO: handle exception
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			}
		return id_racuna;
		
	}
	public List<Artikal> vratiDostupneArtikle(int id_vrste) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM artikal a\r\n"
				+ "INNER JOIN magacin m ON a.id_artikla=m.id_artikla\r\n"
				+ "INNER JOIN vrsta_pica v ON a.id_vrste = v.id_vrste \r\n"
				+ "WHERE v.id_vrste=? AND m.kolicina>0;";
		
		List<Artikal> listaDostupnih= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_vrste);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Artikal a= new Artikal();
				a.setNaziv(rs.getString("naziv"));
				a.setId_artikla(rs.getInt("id_artikla"));
				a.setCena(rs.getDouble("cena"));
				a.setPdv(rs.getDouble("pdv"));
				a.setDostupno(rs.getBoolean("dostupan"));
			
				listaDostupnih.add(a);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listaDostupnih;
	}
	public void updateMagacina(int id_artikla, int kolicina) {
		// TODO Auto-generated method stub
		System.out.println(id_artikla);
try {
    String sql = "SELECT kolicina FROM magacin WHERE id_artikla=?";
    String azuriranaKolicina = "UPDATE magacin SET kolicina=? WHERE id_artikla=?";

    // Dobijemo trenutnu koli�inu iz magacina
    int trenutnaKolicina = 0;
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, id_artikla);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            trenutnaKolicina = rs.getInt("kolicina");
        }
    }

    // Izra�unamo novu koli�inu tako �to od trenutne koli�ine oduzmemo novu koli�inu
    int novaVrijednostKolicine = trenutnaKolicina - kolicina;

    // A�uriramo stanje koli�ine u magacinu
    try (PreparedStatement stmt = con.prepareStatement(azuriranaKolicina)) {
        stmt.setInt(1, novaVrijednostKolicine);
        stmt.setInt(2, id_artikla);
        stmt.executeUpdate();
        System.out.println("Stanje koli�ine u magacinu je uspje�no a�urirano.");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
        
	}

	public List<RacunDTO> vratiPodatkeZaRacunTabelu(int id_stola) {
		// TODO Auto-generated method stub
		String sql="SELECT rs.redni_bs, a.naziv, rs.kolicina, rs.trenutna_cena,"
				+ " rs.trenutni_pdv, r.ukupno FROM racun_stavke rs JOIN"
				+ " racun r ON rs.id_racuna = r.id_racuna JOIN"
				+ " artikal a ON rs.id_artikla = a.id_artikla WHERE "
				+ "r.id_stola = ? AND r.naplacen IS NULL"
				+ " ORDER BY rs.redni_bs ASC;";
		List<RacunDTO> lista = new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_stola);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				RacunDTO r=new RacunDTO();
				r.setRedni_bs(rs.getInt("redni_bs"));
				r.setNaziv(rs.getString("naziv"));
				r.setKolicina(rs.getInt("kolicina"));
				r.setCena(rs.getDouble("trenutna_cena"));
				r.setPdv(rs.getDouble("trenutni_pdv"));
				r.setUkupno(rs.getDouble("ukupno"));
				
				lista.add(r);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lista;
	}
	public boolean proveriNeplaceniRacun(int id_stola) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM `racun` r\r\n"
				+ "JOIN `sto` s ON (r.id_stola=s.id_stola)\r\n"
				+ "WHERE r.naplacen IS NULL;";
		try { 
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_stola);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	public void upisiStavku(int id_racuna, Korpa korpa) {
	   String racunStavke = "INSERT INTO racun_stavke (id_racuna, id_artikla, kolicina, redni_bs, trenutna_cena, trenutni_pdv) VALUES (?,?,?,?,?,?)";
	    double konacnaCena=0.0;
		try {
	        PreparedStatement ps = con.prepareStatement(racunStavke);
	        for (RacunStavka rs : korpa.getList()) {
	            ps.setInt(1, id_racuna);  
	            ps.setInt(2, rs.getId_artikla());
	            ps.setInt(3, rs.getKolicina());
	            ps.setInt(4, rs.getRedni_bs());
	            ps.setDouble(5, rs.getTrenutna_cena());
	            ps.setDouble(6, rs.getTrenutni_pdv());
	            
	            ps.executeUpdate();
	        }
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}

public double vratiUkupanIznosZaRacun(int id_racuna) {
		// TODO Auto-generated method stub
		double ukupno = 0;
		String sql="SELECT ukupno FROM racun WHERE id_racuna=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_racuna);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				 ukupno=rs.getDouble("ukupno");
				 System.out.println(ukupno);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ukupno;
	}
	public void updateRacunUMagacinu(int id_racuna) {
		// TODO Auto-generated method stub
		String sql="UPDATE racun SET naplacen =  CURRENT_TIMESTAMP, kartica=1 WHERE id_racuna=? ";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_racuna);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Racun> vratiSveRacune() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM racun WHERE naplacen IS NULL";
		List<Racun> listaRacuna= new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Racun r=new Racun();
				r.setBr_racuna(rs.getInt("br_racuna"));
				r.setId_racuna(rs.getInt("id_racuna"));
				r.setId_stola(rs.getInt("id_stola"));
				r.setId_usera(rs.getInt("id_usera"));
				r.setNaplacen(rs.getDate("naplacen"));
				r.setUkupno(rs.getDouble("ukupno"));
				
				listaRacuna.add(r);
		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaRacuna;
	}
	public User proveriIVratiUseraIzBaze(User u) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM user WHERE user=?";
		User rezultat= new User();
		String kriptovanPass="";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			//ps.setString(2, u.getPass());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				rezultat.setId_usera(rs.getInt("id_user"));
				rezultat.setIme(rs.getString("ime"));
				rezultat.setPrezime(rs.getString("prezime"));
				rezultat.setUserName(rs.getString("user"));
				kriptovanPass=rs.getString("pass");
			}
			//if(rezultat.getId_usera()>0) {
			if(BCrypt.checkpw(u.getPass(), kriptovanPass)) {
				sql="SELECT * FROM user_role INNER JOIN role on (role.id_role=user_role.id_rola) WHERE id_user=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, rezultat.getId_usera());
				
				rs=ps.executeQuery();
				while(rs.next()) {
					Role r= new Role();
					r.setId_role(rs.getInt("id_role"));
					r.setRola(rs.getString("naziv"));
					r.setOpis(rs.getString("opis"));
				
					rezultat.setRola(r.getRola());
					
					
				}
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rezultat;
	}
	public Role vratiRoluPoImenu(String string) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM role WHERE role.naziv=? ";
		Role r= new Role();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, string);
			ResultSet rs=ps.executeQuery();
			if(rs.next())  {
				r.setId_role(rs.getInt("id_role"));
				r.setOpis(rs.getString("opis"));
				r.setRola(rs.getString("naziv"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}
	public int registrujNovogKorisnika(User u) {
		// TODO Auto-generated method stub
		String sql="INSERT into user (ime,prezime,user,pass) VALUES (?,?,?,?)";
		int id=0;
		try {
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getIme());
			ps.setString(2, u.getPrezime());
			ps.setString(3, u.getUserName());
			ps.setString(4, u.getPass());
			int ar=ps.executeUpdate();
			
			try (ResultSet genKljuc=ps.getGeneratedKeys()) {
				if(genKljuc.next()) {
					id=genKljuc.getInt(ar);
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}
	public void dodeliRoluKorisniku(int id, int id_role) {
		// TODO Auto-generated method stub
		String sql="INSERT into user_role (id_user, id_rola) VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, id_role);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<User> vratiKonobareIzBaze() {
		// TODO Auto-generated method stub
		String sql="SELECT u.*\r\n"
				+ "FROM user u\r\n"
				+ "INNER JOIN user_role ur ON u.id_user = ur.id_user\r\n"
				+ "INNER JOIN role r ON ur.id_rola = r.id_role\r\n"
				+ "WHERE r.naziv = 'KONOBAR'";
		List<User>listaKonobara= new ArrayList<User>();
		try {
			System.out.println("Broker");
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				User u=new User();
				u.setId_usera(rs.getInt("id_user"));
				u.setIme(rs.getString("ime"));
				u.setPrezime(rs.getString("prezime"));
				listaKonobara.add(u);
				System.out.println("Ovo su konobari" + listaKonobara);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaKonobara;
	}
	public void updateRacunUMagacinu2(int id_racuna) {
		// TODO Auto-generated method stub
		String sql="UPDATE racun SET naplacen =  CURRENT_TIMESTAMP, gotovina=1 WHERE id_racuna=? ";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_racuna);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<StatistikaDTO> vratiStatistiku(int id_konobara, JDateChooser dc1) {
		// TODO Auto-generated method stub
		String sql="SELECT  u.ime, u.prezime,"
				+ "SUM(CASE WHEN r.kartica = 1 THEN r.ukupno ELSE 0 END) AS ukupno_kartica,"
				+ "SUM(CASE WHEN r.gotovina = 1 THEN r.ukupno ELSE 0 END) AS ukupno_gotovina, "
				+ "SUM(r.ukupno) AS ukupno_naplaceno FROM racun r JOIN "
				+ "  user u ON r.id_usera = u.id_user WHERE id_user=? AND  DATE(r.naplacen) = ? GROUP BY "
				+ " u.ime, u.prezime";
		List<StatistikaDTO>lista=new ArrayList<StatistikaDTO>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_konobara);
			java.util.Date utilDate = dc1.getDate();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    ps.setDate(2, sqlDate);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				StatistikaDTO s= new StatistikaDTO();
				s.setIme(rs.getString("ime"));
				s.setPrezime(rs.getString("prezime"));
				s.setGotovina(rs.getDouble("ukupno_gotovina"));
				s.setKartica(rs.getDouble("ukupno_kartica"));
				s.setUkupno(rs.getDouble("ukupno_naplaceno"));
				
				lista.add(s);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lista;
	}
	public void azurirajNovuCenuRacuna(int id_racuna, double novaUkupnaCenaRacuna) {
		// TODO Auto-generated method stub
		String sql="UPDATE racun SET naplacen=naplacen, ukupno = ?  WHERE id_racuna = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setDouble(1, novaUkupnaCenaRacuna);
			ps.setInt(2, id_racuna);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String vratiImeUsera(int id) {
		// TODO Auto-generated method stub
		String imeKonobara="";
		String sql="SELECT ime FROM user WHERE id_user=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				User u=new User();
				imeKonobara=rs.getString("ime");
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return imeKonobara;
		
	}
	public String vratiPrezimeUsera(int id) {
		// TODO Auto-generated method stub
		String prezime="";
		String sql="SELECT prezime FROM user WHERE id_user=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				
				prezime=rs.getString("prezime");
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return prezime;
	}
	public int vratiNajveciRedniBroj(int id_racuna) {
		// TODO Auto-generated method stub
		String sql="SELECT MAX(redni_bs) AS 'poslednjiRedniBroj' FROM racun_stavke WHERE id_racuna=?";
		int poslednjiBroj=0;
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_racuna);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				poslednjiBroj=(rs.getInt("poslednjiRedniBroj"));
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return poslednjiBroj;
	}
	public boolean DaLiJeNaplacen(int id_stola) {
		// TODO Auto-generated method stub
		String sql = "SELECT naplacen FROM racun WHERE id_stola = ?";
		boolean jeNaplacen=false;
		try {
			PreparedStatement ps=con.prepareStatement(sql);
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id_stola);
	        ResultSet rs=ps.executeQuery();
	        
	        if (rs.next()) {
	            Timestamp naplacenTimestamp = rs.getTimestamp("naplacen");
	            jeNaplacen = (naplacenTimestamp != null);
	        }
	    } catch (SQLException e) {
	        // Handle SQL exception
	        e.printStackTrace();
	        System.out.println("GRESKA");
	    }
		return jeNaplacen;
	}
	public int daLiJeStoCrven(int id) {
		// TODO Auto-generated method stub
		int nemaAktivanRacun=0;
		String sql="SELECT \r\n"
				+ "    CASE \r\n"
				+ "        WHEN COUNT(r.id_racuna) = 0 THEN 0 -- ako sto nema nijedan racun\r\n"
				+ "        WHEN SUM(CASE WHEN r.naplacen IS NULL THEN 1 ELSE 0 END) > 0 THEN 1 -- ako sto ima bar jedan racun koji nije naplacen\r\n"
				+ "        ELSE 0\r\n"
				+ "    END AS nema_aktivan_racun\r\n"
				+ "FROM sto s\r\n"
				+ "LEFT JOIN racun r ON s.id_stola = r.id_stola\r\n"
				+ "WHERE s.id_stola = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				 nemaAktivanRacun = rs.getInt("nema_aktivan_racun");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nemaAktivanRacun;
	}
	public Timestamp vratiVreme(int id_racuna) {
		// TODO Auto-generated method stub
		Timestamp vreme=null;
		String sql="SELECT naplacen FROM racun WHERE id_racuna=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id_racuna);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vreme=rs.getTimestamp("naplacen");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vreme;
	}

	}





	
	

