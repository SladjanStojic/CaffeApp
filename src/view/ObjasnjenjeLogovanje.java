package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ObjasnjenjeLogovanje extends JFrame {

	private JPanel contentPane;

	public ObjasnjenjeLogovanje() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setText("<html>Logovanje: prilikom logovanja smo koristili Proxy pattern, gdje sa na samoj formi logovanje imamo metodu login unutar koje preuzimamo ime i prezime kao i pasword koji kriptujemo preko bCripty i setujemo na user <br> takodje u metodi nakon toga instanciramo interfejs Proxy i prosledjujemo mu instancu Klase ProxyLogin koja je implementirala taj interfejs.<br> U Klasi ProxyLogin provjeravamo da li taj User postoji u bazi i ako postoji otvara se medju forma koja se prilagodjava u odnosu na Rolu tog usera<html>");
		lblNewLabel.setBounds(22, 22, 611, 87);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(ObjasnjenjeLogovanje.class.getResource("/slike/ProxyLOGIN.PNG")));
		lblNewLabel_1.setBounds(10, 120, 744, 410);
		contentPane.add(lblNewLabel_1);
	}

}
