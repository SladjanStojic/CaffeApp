package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DTO.KorpaDTO;
import DTO.MagacinDTO;
import DTO.RacunDTO;
import Kontroler.Kontroler;
import Model.Artikal;
import Model.Korpa;
import Model.Racun;
import Model.RacunStavka;
import Model.Stavka;
import Model.Sto;
import Model.Vrsta;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Porudzbina extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JComboBox comboBoxVrste;
	JComboBox comboBoxPojedinacniArtikli;
	private DefaultTableModel dtm= new DefaultTableModel();
	private DefaultTableModel dtm2=new DefaultTableModel();
	private Artikal artikal;
	private Korpa korpa= new Korpa();
	int brojRacuna=1;
	private int id_stola;
	private Sto sto;
	private JToggleButton tglbtn;
	private JTable table_1;
	private double ukupno;
	Racun racun;
	private int id_racuna;
	JComboBox comboBoxPlacanje;
	double ukupnaCenaSvih;
	private int id;
	int poslednjiRedniBroj;
	int redniBrojStavke;
	JSpinner spinnerKolicina;
	double trenutnaCenaRacuna;
	double ukupnaCenaPriKreiranju;
	
	Porudzbina(JToggleButton tglbtn,int id) { 
	    this.tglbtn = tglbtn;
	    this.id=id;
	    System.out.println("OVOO JE ID KONOBARA" + id);
	    this.id_stola = (int) tglbtn.getClientProperty("id_stola");
	    Integer idRacuna = (Integer) tglbtn.getClientProperty("id_racuna2");
	    this.id_racuna = idRacuna != null ? idRacuna.intValue() : 0; // Postavljamo na 0 ako je null
	  
	   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		comboBoxVrste = new JComboBox();
		comboBoxVrste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id_vrste=((Vrsta) comboBoxVrste.getSelectedItem()).getId_vrste();
				ucitajPodatkeZaPojedinacneArtikleKojiSuDostupni(id_vrste);
				
			}
		});
		comboBoxVrste.setBounds(10, 11, 133, 22);
		contentPane.add(comboBoxVrste);
		
		comboBoxPojedinacniArtikli = new JComboBox();
		comboBoxPojedinacniArtikli.setBounds(166, 11, 118, 22);
		contentPane.add(comboBoxPojedinacniArtikli);
		
		spinnerKolicina = new JSpinner();
		spinnerKolicina.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerKolicina.setBounds(305, 12, 47, 20);
		contentPane.add(spinnerKolicina);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			kreirajKorpu();	
			}
		});
		btnDodaj.setBounds(360, 11, 89, 23);
		contentPane.add(btnDodaj);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 353, 176);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		JButton btnNaplati = new JButton("Naplati");
		btnNaplati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izabranoPlacanje=(String) comboBoxPlacanje.getSelectedItem();
					if(izabranoPlacanje.equalsIgnoreCase("kartica")) {
						
						Kartica k= new Kartica(id,id_racuna,dtm2);
						k.setVisible(true);
					} else {
						Gotovina g= new Gotovina(id,id_racuna,dtm2);
						g.setVisible(true);
					}
				}
			}
		);
		btnNaplati.setBounds(305, 313, 89, 23);
		contentPane.add(btnNaplati);
		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int redZaBrisanje=table.getSelectedRow();
				if(redZaBrisanje!=-1) {
					korpa.getList().remove(redZaBrisanje);
					dtm.removeRow(redZaBrisanje);
					poslednjiRedniBroj--;

				}
			}
		});
		btnObrisi.setBounds(10, 58, 89, 23);
		contentPane.add(btnObrisi);
		
		
		JButton btnPoruci = new JButton("Poruci");
		btnPoruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prebaciPodatkeIzJedneTabeleUDrugu();
				kreirajUpdajtujRacun(korpa);
				
			   	}});
		btnPoruci.setBounds(109, 58, 89, 23);
		contentPane.add(btnPoruci);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(416, 45, 244, 259);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(dtm2);
		scrollPane_1.setViewportView(table_1);
		
		comboBoxPlacanje = new JComboBox();
		comboBoxPlacanje.addItem("Kartica");
		comboBoxPlacanje.addItem("Gotovina");
		comboBoxPlacanje.setBounds(166, 313, 118, 22);
		contentPane.add(comboBoxPlacanje);
		
		
		Object [] kolona1= {"RB","NAZIV","KOLICINA","CENA", "PDV","UKUPNO"};
		dtm2.addColumn(kolona1[0]);
		dtm2.addColumn(kolona1[1]);
		dtm2.addColumn(kolona1[2]);
		dtm2.addColumn(kolona1[3]);
		dtm2.addColumn(kolona1[4]);
		dtm2.addColumn(kolona1[5]);
		
		
		
		Object [] kolona= {"RB", "NAZIV","KOLICINA","CENA", "PDV","UKUPNO"};
		dtm.addColumn(kolona[0]);
		dtm.addColumn(kolona[1]);
		dtm.addColumn(kolona[2]);
		dtm.addColumn(kolona[3]);
		dtm.addColumn(kolona[4]);
		dtm.addColumn(kolona[5]);
			
		prebaciPodatkeIzJedneTabeleUDrugu();
		postaviPodatkeZaVrste();
		popuniTabeluRacun();
		poslednjiRedniBroj();
		
		
		
		
	}
	
	private void kreirajKorpu() {
		// TODO Auto-generated method stub
		artikal=(Artikal)comboBoxPojedinacniArtikli.getSelectedItem();
		poslednjiRedniBroj();
		String naziv=artikal.getNaziv();
		double cena=artikal.getCena();
		double pdv=artikal.getPdv();
		RacunStavka rs= new RacunStavka();
		rs.setId_artikla(artikal.getId_artikla());
		rs.setTrenutna_cena(cena);
		rs.setTrenutni_pdv(pdv);
		rs.setKolicina((int) spinnerKolicina.getValue());
		System.out.println("KORPA ID_RACUNA"+ id_racuna);
		if(id_racuna>0) {
			rs.setRedni_bs(++poslednjiRedniBroj);
			//poslednjiRedniBroj++;
		} else {
			rs.setRedni_bs(redniBrojStavke);
			redniBrojStavke++;
		}
		korpa.dodaj(rs);
		ukupno=(rs.getKolicina()*(cena+(cena*pdv/100)));
		Object [] red= {rs.getRedni_bs(),naziv,rs.getKolicina(),cena, pdv,ukupno};
		dtm.addRow(red);
		Kontroler.getInstanca().updateMagacina(artikal.getId_artikla(),rs.getKolicina());
		System.out.println("KORPA LISTA" + korpa.getList());
		
	}
	private void prebaciPodatkeIzJedneTabeleUDrugu() {
		// TODO Auto-generated method stub
		int brojReda = dtm.getRowCount();
		for (int i = 0; i < brojReda; i++) {
		    Object[] rowData = new Object[dtm.getColumnCount()];
		    for (int j = 0; j < dtm.getColumnCount(); j++) {
		        rowData[j] = dtm.getValueAt(i, j);
		       
		    }
		    dtm2.addRow(rowData);
		}
		dtm.setRowCount(0);
	}
	private void kreirajUpdajtujRacun(Korpa korpa) {
	    System.out.println("OVO JE KORPA" + korpa);
	    
	    // Ako postoji veæ kreiran raèun
	    if (id_racuna > 0) {
	        double ukupnaCenaSvih = 0.0;
	        
	        // Izraèunaj ukupnu cijenu svih stavki u korpi
	        for (RacunStavka rs2 : korpa.getList()) {
	            double ukupnaCenaZaArtikal = rs2.getKolicina() * (rs2.getTrenutna_cena() + (rs2.getTrenutna_cena() * rs2.getTrenutni_pdv() / 100));
	            ukupnaCenaSvih += ukupnaCenaZaArtikal;
	        }
	        
	        // Dobavi trenutnu cijenu raèuna
	       trenutnaCenaRacuna = Kontroler.getInstanca().vratiUkupanIznosSaRacuna(id_racuna);
	        System.out.println("OVOO JE UKUPNA CENAA" + trenutnaCenaRacuna);
	        
	        // Inicijalizacija nove ukupne cijene raèuna
	        double novaUkupnaCenaRacuna = trenutnaCenaRacuna;
	        
	        // Dodaj nove stavke na raèun
	        Kontroler.getInstanca().dodajStavkuNaRacun(id_racuna, korpa);
	        
	        // Ažuriraj novu ukupnu cijenu raèuna
	        for (RacunStavka rs2 : korpa.getList()) {
	            novaUkupnaCenaRacuna += rs2.getKolicina() * (rs2.getTrenutna_cena() + (rs2.getTrenutna_cena() * rs2.getTrenutni_pdv() / 100));
	            System.out.println("OVOO JE UKUPNA CENAA" + novaUkupnaCenaRacuna);
	        }
	        Kontroler.getInstanca().azurirajNovuCenuRacuna(id_racuna, novaUkupnaCenaRacuna);
	    } else { // Ako raèun nije još kreiran
	    	for(RacunStavka rs2 : korpa.getList()) {
	    		  trenutnaCenaRacuna+=rs2.getKolicina() * (rs2.getTrenutna_cena() + (rs2.getTrenutna_cena() * rs2.getTrenutni_pdv() / 100));
	    	}
	        id_racuna = Kontroler.getInstanca().upisiRacun(korpa, brojRacuna, id_stola, id,   trenutnaCenaRacuna);
	        tglbtn.putClientProperty("id_racuna2", id_racuna);
	    }
	}
	private void poslednjiRedniBroj() {
	    // TODO Auto-generated method stub
	    if (id_racuna > 0) {
	    	 poslednjiRedniBroj=Kontroler.getInstanca().vratiPoslednjiRedniBroj(id_racuna);
	    	System.out.println("OVO JE ID RACUN"+id_racuna);
	    	System.out.println("OVO JE POSLEDNJI REDNI BROJ" + poslednjiRedniBroj);
	       for(RacunStavka rs2:korpa.getList()) {
	         rs2.setRedni_bs(++poslednjiRedniBroj);
	        }
	        
	    } else {
	    	redniBrojStavke=1;
	        for (RacunStavka rs2 : korpa.getList()) {
	            rs2.setRedni_bs(redniBrojStavke);
	            redniBrojStavke++;
	        }
	    }
	}

		
		private void popuniTabeluRacun() {
		    dtm2.setRowCount(0);
		    List<RacunDTO> podaciZaTabelu = Kontroler.getInstanca().vratiPodatkeZaRacunTabelu(id_stola);
		    for (RacunDTO r : podaciZaTabelu) {
		        Object[] red = {r.getRedni_bs(), r.getNaziv(), r.getKolicina(), r.getCena(), r.getPdv(), r.getKolicina() * (r.getCena() + (r.getCena() * r.getPdv() / 100))}; 
		        dtm2.addRow(red);
		    }
		}
	


	private void ucitajPodatkeZaPojedinacneArtikleKojiSuDostupni(int id_vrste) {
		comboBoxPojedinacniArtikli.removeAllItems();
		for(Artikal a:Kontroler.getInstanca().vratiArtikleZaVrstuKojiSuDostupnin(id_vrste)) {
			comboBoxPojedinacniArtikli.addItem(a);
			
		}
		
	}

	private void postaviPodatkeZaVrste() {
	// TODO Auto-generated method stub
	 for (Vrsta v : Kontroler.getInstanca().vratiVrsteIzBaze()) {
	        comboBoxVrste.addItem(v);
	    }
}
}
