package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Kontroler.Kontroler;
import Logovanje.IProxy;
import Model.Role;
import Model.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class MedjuForma extends JFrame implements IProxy{
	JLabel lblIme;
	private JPanel contentPane;
	private User u;
	JButton btnUnesiPice;
	JButton btnUnazad;
	JButton btnStanje;
	JButton btnRasporedStolova;
		JButton btnKreirajPorudzbinu;
		JButton btnZaposli;
		JButton btnSanitarije;
		private int id_usera;
		private JButton btnNewButton;
	
	public MedjuForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnUnesiPice = new JButton("Novo Pice");
		btnUnesiPice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UnesiNovoPiceForma up= new UnesiNovoPiceForma();
				up.setVisible(true);
			}
		});
		btnUnesiPice.setBounds(24, 45, 138, 23);
	//	btnUnesiPice.setVisible(false);
		contentPane.add(btnUnesiPice);
		
		btnUnazad = new JButton("LogOut");
		btnUnazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Logovanje l= new Logovanje();
				l.setVisible(true);
			}
		});
		btnUnazad.setBounds(374, 11, 89, 23);
		contentPane.add(btnUnazad);
		
		btnStanje = new JButton("Magacin");
		btnStanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Magacin m= new Magacin();
				m.setVisible(true);
			}
		});
		btnStanje.setBounds(24, 103, 138, 23);
	//	btnStanje.setVisible(false);
		contentPane.add(btnStanje);
		
		btnZaposli = new JButton("Dodaj Zaposlenog");
		btnZaposli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DodajZaposlenog d= new DodajZaposlenog();
				d.setVisible(true);
			}
		});
		btnZaposli.setBounds(126, 240, 138, 23);
		//btnZaposli.setVisible(false);
		contentPane.add(btnZaposli);
		
		btnRasporedStolova = new JButton("Namesti Stolove");
		btnRasporedStolova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NamestiStolove n= new NamestiStolove();
				n.setVisible(true);
				
			}
		});
		btnRasporedStolova.setBounds(212, 45, 138, 23);
		contentPane.add(btnRasporedStolova);
	//	btnRasporedStolova.setVisible(false);
		setVisible(true);
		
		 btnSanitarije = new JButton("Sanitarije");
		btnSanitarije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sanitarije s= new Sanitarije();
				s.setVisible(true);
			}
		});
		btnSanitarije.setBounds(212, 161, 138, 23);
	//	btnSanitarije.setVisible(false);
		contentPane.add(btnSanitarije);
		
			
		btnKreirajPorudzbinu = new JButton("Kreiraj Porudzbinu");
		btnKreirajPorudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KreirajPorudzbinu k= new KreirajPorudzbinu(u.getId_usera());
				k.setVisible(true);
			}
		});
		btnKreirajPorudzbinu.setBounds(212, 103, 138, 23);
	//	btnKreirajPorudzbinu.setVisible(false);
		contentPane.add(btnKreirajPorudzbinu);
		
		
		lblIme = new JLabel("New label");
		lblIme.setBounds(21, 11, 82, 14);
		contentPane.add(lblIme);
		
		JButton btnStatistika = new JButton("Statistika prodaje");
		btnStatistika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistika s= new Statistika();
				s.setVisible(true);
			}
		});
		btnStatistika.setBounds(24, 161, 138, 23);
		contentPane.add(btnStatistika);
		
		btnNewButton = new JButton("Logika Za Stolove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogikaZaStolove l= new LogikaZaStolove();
				l.setVisible(true);
			}
		});
		btnNewButton.setBounds(302, 285, 161, 23);
		contentPane.add(btnNewButton);
	}


	@Override
	public void login(User u) {
		// TODO Auto-generated method stub
		lblIme.setText(u.getIme() + " " + u.getPrezime());
		System.out.println("ID USERA" + u.getId_usera());
		this.u=u;
		if(u.getRola().equals("ADMIN")) {
			System.out.println(u.getRola());
			btnKreirajPorudzbinu.setVisible(true);
			btnRasporedStolova.setVisible(true);
			btnSanitarije.setVisible(true);
			btnStanje.setVisible(true);
			btnUnesiPice.setVisible(true);
			btnZaposli.setVisible(true);
		}
		else if(u.getRola().equals("MENADZER")) {
			btnKreirajPorudzbinu.setVisible(false);
			btnRasporedStolova.setVisible(true);
			btnSanitarije.setVisible(false);
			btnStanje.setVisible(true);
			btnUnesiPice.setVisible(true);
			btnZaposli.setVisible(true);
		}
		else if(u.getRola().equals("KONOBAR")) {
			btnKreirajPorudzbinu.setVisible(true);
			btnRasporedStolova.setVisible(true);
			btnSanitarije.setVisible(false);
			btnStanje.setVisible(true);
			btnUnesiPice.setVisible(false);
			btnZaposli.setVisible(false);
		}
		else {
			btnKreirajPorudzbinu.setVisible(false);
			btnRasporedStolova.setVisible(false);
			btnSanitarije.setVisible(true);
			btnStanje.setVisible(false);
			btnUnesiPice.setVisible(false);
			btnZaposli.setVisible(false);
		}
		
	}
}
