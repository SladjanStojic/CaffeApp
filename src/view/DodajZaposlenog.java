package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Broker.Broker;
import Kontroler.Kontroler;
import Model.Role;
import Model.User;
import Registracija.IProxyR;
import Registracija.ProxyReg;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DodajZaposlenog extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	JComboBox comboBox;
	private int id_rola;

	public DodajZaposlenog() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNaslov = new JLabel("Dodaj Zaposlenog");
		lblNaslov.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setBounds(116, 11, 203, 60);
		contentPane.add(lblNaslov);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(87, 82, 46, 14);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(87, 117, 46, 14);
		contentPane.add(lblPrezime);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(87, 151, 79, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPozicija = new JLabel("Pozicija");
		lblPozicija.setBounds(87, 215, 46, 14);
		contentPane.add(lblPozicija);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(87, 176, 79, 14);
		contentPane.add(lblPass);
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(176, 76, 148, 20);
		contentPane.add(textFieldIme);
		textFieldIme.setColumns(10);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setColumns(10);
		textFieldPrezime.setBounds(176, 114, 148, 20);
		contentPane.add(textFieldPrezime);
		
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		textFieldUser.setBounds(176, 148, 148, 20);
		contentPane.add(textFieldUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 173, 148, 20);
		contentPane.add(passwordField);
		
		JButton btnUnesiZaposlenog = new JButton("Unesi Zaposlenog");
		btnUnesiZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrujZaposlenog();
				JOptionPane.showMessageDialog(btnUnesiZaposlenog, "Uspesno ste uneli zaposlenog u bazu");
			}
		});
		btnUnesiZaposlenog.setBounds(143, 257, 125, 23);
		contentPane.add(btnUnesiZaposlenog);
		
		comboBox = new JComboBox();
		comboBox.setBounds(176, 211, 148, 22);
		contentPane.add(comboBox);
		
		ucitajPodatkeZaComboBox();
	}

protected void registrujZaposlenog() {
		// TODO Auto-generated method stub
			User u= new User();
			u.setIme(textFieldIme.getText());
			u.setPrezime(textFieldPrezime.getText());
			u.setUserName(textFieldUser.getText());
			u.setPass(passwordField.getText());
	
			IProxyR reg= new ProxyReg();
			reg.registruj(u);
	}

/*	protected void sacuvajUsera() {
		// TODO Auto-generated method stub
		///User u= new User();
	//	u.setIme(textFieldIme.getText());
		//u.setPrezime(textFieldPrezime.getText());
		//u.setUserName(textFieldUser.getText());
		//u.setPass(passwordField.getText());
	//	x
		Role izabranaRola=(Role) comboBox.getSelectedItem();
		id_rola=izabranaRola.getId_role();
		//Kontroler.getInstanca().sacuvajUsera(u,id_rola);
		
		
		
		textFieldIme.setText("");
		textFieldPrezime.setText("");
		textFieldUser.setText("");
		passwordField.setText("");
		
	}
*/
	private void ucitajPodatkeZaComboBox() {
		// TODO Auto-generated method stub
		for(Role r: Kontroler.getInstanca().vratiRole()) {
			comboBox.addItem(r);
		}
	}
}
