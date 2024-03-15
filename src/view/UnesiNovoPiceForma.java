package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DTO.ArtikalDTO;
import Kontroler.Kontroler;
import Model.Artikal;
import Model.Vrsta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class UnesiNovoPiceForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfNaziv;
	private JTextField tfPDV;
	private JTextField tfCena;
	private JTable table;
	private Artikal artikal;
	private int id_artikla;
	DefaultTableModel dtm= new DefaultTableModel();
	JComboBox comboBoxVrsta;

	
	public UnesiNovoPiceForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setBounds(10, 11, 46, 14);
		contentPane.add(lblNaziv);
		
		JLabel lblVrsta = new JLabel("Vrsta Pica");
		lblVrsta.setBounds(10, 49, 80, 14);
		contentPane.add(lblVrsta);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(213, 49, 46, 14);
		contentPane.add(lblCena);
		
		JLabel lblPDV = new JLabel("PDV");
		lblPDV.setBounds(213, 11, 46, 14);
		contentPane.add(lblPDV);
		
		JCheckBox CheckBoxDostupan = new JCheckBox("U prodaji");
		CheckBoxDostupan.setBounds(360, 45, 97, 23);
		contentPane.add(CheckBoxDostupan);
		
		tfNaziv = new JTextField();
		tfNaziv.setBounds(66, 8, 86, 20);
		contentPane.add(tfNaziv);
		tfNaziv.setColumns(10);
		
		tfPDV = new JTextField();
		tfPDV.setColumns(10);
		tfPDV.setBounds(251, 8, 86, 20);
		contentPane.add(tfPDV);
		
		tfCena = new JTextField();
		tfCena.setColumns(10);
		tfCena.setBounds(251, 46, 86, 20);
		contentPane.add(tfCena);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MedjuForma mf= new MedjuForma();
				mf.setVisible(true);
			}
		});
		btnMenu.setBounds(368, 7, 89, 23);
		contentPane.add(btnMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 137, 399, 156);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red=table.getSelectedRow();
				id_artikla=(int)(table.getModel().getValueAt(red, 0));
				tfNaziv.setText(table.getModel().getValueAt(red, 1).toString());
				//tfVrstaPica.setText(table.getModel().getValueAt(red, 2).toString());
				tfCena.setText(table.getModel().getValueAt(red, 3).toString());
				tfPDV.setText(table.getModel().getValueAt(red, 4).toString());
				CheckBoxDostupan.isSelected();
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnUnesiPice = new JButton("Unesi Pice");
		btnUnesiPice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=tfNaziv.getText();
				int id_vrste=((Vrsta) comboBoxVrsta.getSelectedItem()).getId_vrste();
				Double cena=Double.parseDouble(tfCena.getText());
				Double pdv= Double.parseDouble(tfPDV.getText());
				Boolean uProdaji= CheckBoxDostupan.isSelected();
				
				Kontroler.getInstanca().upisiArtikalUBazu(naziv,id_vrste,cena,pdv,uProdaji);
				
				tfNaziv.setText("");
				tfCena.setText("");
				tfPDV.setText("");
				postaviPodatkeArtikla();
				JOptionPane.showMessageDialog(btnUnesiPice, "Uspesno ste uneli artikal");
			}
		});
		btnUnesiPice.setBounds(34, 95, 89, 23);
		contentPane.add(btnUnesiPice);
		
		JButton btnIzmeni = new JButton("Izmeni");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id_vrste=((Vrsta) comboBoxVrsta.getSelectedItem()).getId_vrste();
				Kontroler.getInstanca().izmeniArtikal(id_artikla,tfNaziv.getText(),id_vrste,Double.parseDouble(tfCena.getText()),Double.parseDouble(tfPDV.getText()));
				tfNaziv.setText("");
				tfCena.setText("");
				
				tfPDV.setText("");
				
				postaviPodatkeArtikla();
			}
		});
		btnIzmeni.setBounds(144, 95, 89, 23);
		contentPane.add(btnIzmeni);
		
		comboBoxVrsta = new JComboBox();
		comboBoxVrsta.setBounds(66, 45, 86, 22);
		contentPane.add(comboBoxVrsta);
		
		Object [] kolona= {"ID","NAZIV","VRSTA PICA", "CENA","PDV", "KONACNA CENA"};
		dtm.addColumn(kolona[0]);
		dtm.addColumn(kolona[1]);
		dtm.addColumn(kolona[2]);
		dtm.addColumn(kolona[3]);
		dtm.addColumn(kolona[4]);
		dtm.addColumn(kolona[5]);
		
		
		postaviPodatkeArtikla();
		postaviPodatkeComboBoxVrste();
	}
	


	private void postaviPodatkeComboBoxVrste() {
		// TODO Auto-generated method stub
		for(Vrsta v: Kontroler.getInstanca().vratiVrsteIzBaze()) {
			comboBoxVrsta.addItem(v);
		}
		
	}



	private void postaviPodatkeArtikla() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		for(ArtikalDTO a: Kontroler.getInstanca().vratiArtikleIzBaze()) {
			Object [] red= {a.getId(),a.getNaziv(),a.getVrsta(),a.getCena(),a.getPDV(),a.getUkupnaCena()};
			dtm.addRow(red);
		}
		
	}
}
