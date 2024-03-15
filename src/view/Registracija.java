package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.User;
import Registracija.IProxyR;
import Registracija.ProxyReg;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Registracija extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfUserName;
	private JPasswordField passF1;
	private JPasswordField passF2;

	
	public Registracija() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(92, 11, 46, 14);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(92, 47, 46, 14);
		contentPane.add(lblPrezime);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(92, 78, 46, 14);
		contentPane.add(lblUserName);
		
		JLabel lblLozinka = new JLabel("lozinka");
		lblLozinka.setBounds(92, 109, 46, 14);
		contentPane.add(lblLozinka);
		
		JLabel lblPonovljenaLozinka = new JLabel("Ponovljena lozinka");
		lblPonovljenaLozinka.setBounds(92, 140, 46, 14);
		contentPane.add(lblPonovljenaLozinka);
		
		tfIme = new JTextField();
		tfIme.setBounds(148, 8, 143, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setColumns(10);
		tfPrezime.setBounds(148, 44, 143, 20);
		contentPane.add(tfPrezime);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		tfUserName.setBounds(148, 75, 143, 20);
		contentPane.add(tfUserName);
		
		passF1 = new JPasswordField();
		passF1.setBounds(148, 106, 143, 20);
		contentPane.add(passF1);
		
		passF2 = new JPasswordField();
		passF2.setBounds(148, 137, 143, 20);
		contentPane.add(passF2);
		
		JButton btnRegistruj = new JButton("Registruj me");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrujMe();
			}
		});
		btnRegistruj.setBounds(171, 168, 89, 23);
		contentPane.add(btnRegistruj);
	}


	protected void registrujMe() {
		// TODO Auto-generated method stub
		if(tfUserName.getText().length()>4 &&passF1.getPassword().length >= 5 && Arrays.equals(passF1.getPassword(), passF2.getPassword())) {
		    User u=new User();
		    u.setIme(tfIme.getText());
		    u.setPrezime(tfPrezime.getText());
		    u.setUserName(tfUserName.getText());
		    u.setPass(String.valueOf(passF1.getPassword()));
		    
		    IProxyR reg= new ProxyReg();
		    reg.registruj(u);
		    JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali");
		    dispose();
		} else {
			JOptionPane.showConfirmDialog(null, "Paswordi se ne poklapaju ili su kraci od 5");
		}
			
		}
	}

