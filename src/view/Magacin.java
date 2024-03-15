package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import DTO.MagacinDTO;
import Kontroler.Kontroler;
import Model.Artikal;
import Model.CustomRowRenderer;
import Model.Vrsta;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class Magacin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel dtm= new DefaultTableModel();
	JComboBox comboBoxVrsteArtikala;
	private Artikal artikal;
	private int id_artikla;
	private int id_vrste;
	JComboBox comboBoxPojedinacniArtikal;
	
	

	
	public Magacin() {
		Model.Magacin m= new Model.Magacin();
		m.setId_magacina(1);
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArtikal = new JLabel("Artikal");
		lblArtikal.setBounds(10, 51, 87, 14);
		contentPane.add(lblArtikal);
		
		comboBoxVrsteArtikala = new JComboBox();
		comboBoxVrsteArtikala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id_vrste=((Vrsta) comboBoxVrsteArtikala.getSelectedItem()).getId_vrste();
				ucitajPodatkeZaPojedinacneArtikle(id_vrste);
				
			}
		});
		comboBoxVrsteArtikala.setBounds(52, 47, 135, 22);
		contentPane.add(comboBoxVrsteArtikala);
		
		JSpinner spinnerKolicina = new JSpinner();
		spinnerKolicina.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerKolicina.setBounds(358, 48, 60, 20);
		contentPane.add(spinnerKolicina);
		
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MedjuForma mf= new MedjuForma();
				mf.setVisible(true);
			}
		});
		btnMenu.setBounds(390, 11, 89, 23);
		contentPane.add(btnMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 448, 164);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		table.setDefaultRenderer(Object.class, new CustomRowRenderer());
		
		
		JButton btnUnesiUMagacin = new JButton("Unesi");
		btnUnesiUMagacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Artikal izabraniArtikal=(Artikal) comboBoxPojedinacniArtikal.getSelectedItem();
				id_artikla=izabraniArtikal.getId_artikla();
				int kolicina=(int) spinnerKolicina.getValue();
				Kontroler.getInstanca().unesiUMagacinIliUpdate(id_artikla,kolicina);
				postaviPodatkeZaMagacin();
				
			}
		});
		btnUnesiUMagacin.setBounds(358, 89, 89, 23);
		contentPane.add(btnUnesiUMagacin);
		
		comboBoxPojedinacniArtikal = new JComboBox();
		comboBoxPojedinacniArtikal.setBounds(197, 47, 135, 22);
		contentPane.add(comboBoxPojedinacniArtikal);
		
		Object [] kolona= {"ID", "NAZIV", "STANJE", "CENA","DOSTUPNO"};
		dtm.addColumn(kolona[0]);
		dtm.addColumn(kolona[1]);
		dtm.addColumn(kolona[2]);
		dtm.addColumn(kolona[3]);
		dtm.addColumn(kolona[4]);
		
		
		
		ucitajPodatkeZaVrste();
		postaviPodatkeZaMagacin();
		
		
	
	}


	private void postaviPodatkeZaMagacin() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		for(MagacinDTO m : Kontroler.getInstanca().vratiPodatkeZaMagacin()) {
			Object[] red= {m.getId_artikla(),m.getNaziv(),m.getStanje(),m.getCena(),m.isDostupno()}; 
			dtm.addRow(red);
		}
		
	}


	private void ucitajPodatkeZaPojedinacneArtikle(int id_vrste) {
		comboBoxPojedinacniArtikal.removeAllItems();
		for(Artikal a:Kontroler.getInstanca().vratiArtikleZaVrstu(id_vrste)) {
			comboBoxPojedinacniArtikal.addItem(a);
			System.out.println(a.getId_artikla());
		}
		
	}

	private void ucitajPodatkeZaVrste() {
	   for (Vrsta v : Kontroler.getInstanca().vratiVrsteIzBaze()) {
	        comboBoxVrsteArtikala.addItem(v);
	    }
	}
}

