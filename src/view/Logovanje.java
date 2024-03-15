package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Logovanje.IProxy;
import Logovanje.ProxyLogin;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Logovanje extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatMacDarkLaf());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logovanje frame = new Logovanje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logovanje() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAplikacija = new JLabel("Caffe App");
		lblAplikacija.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		lblAplikacija.setBounds(149, 25, 114, 28);
		contentPane.add(lblAplikacija);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(177, 119, 132, 20);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblUserName = new JLabel("UserName");
		lblUserName.setBounds(109, 122, 89, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(109, 159, 46, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 156, 132, 20);
		contentPane.add(passwordField);
		
		JButton btnUloguj = new JButton("Uloguj se");
		btnUloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnUloguj.setBounds(156, 208, 89, 23);
		contentPane.add(btnUloguj);
		
		JButton btnRegistracija = new JButton("Registrujte se");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Registracija r= new Registracija();
				r.setVisible(true);
			}
		});
		btnRegistracija.setBounds(156, 248, 89, 23);
		contentPane.add(btnRegistracija);
		
		JButton btnObjasnjenje = new JButton("Objasnjenje Registracija");
		btnObjasnjenje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjasnjenjeRegistracija o=new ObjasnjenjeRegistracija();
				o.setVisible(true);
			}
		});
		btnObjasnjenje.setBounds(270, 293, 154, 23);
		contentPane.add(btnObjasnjenje);
		
		JButton btnObjasnjenje2 = new JButton("Objasnjenje Logovanje");
		btnObjasnjenje2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjasnjenjeLogovanje o= new ObjasnjenjeLogovanje();
				o.setVisible(true);
			}
		});
		btnObjasnjenje2.setBounds(270, 259, 154, 23);
		contentPane.add(btnObjasnjenje2);
		
		JButton btnObjasnjenjeBaza = new JButton("Objasnjenje Baza");
		btnObjasnjenjeBaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjasnjenjeBaza o= new ObjasnjenjeBaza();
				o.setVisible(true);
			}
		});
		btnObjasnjenjeBaza.setBounds(270, 228, 154, 23);
		contentPane.add(btnObjasnjenjeBaza);
	}

	protected void login() {
		// TODO Auto-generated method stub
		String user=tfUserName.getText();
		String pass=String.valueOf(passwordField.getPassword());
		
		String pass2=BCrypt.hashpw(pass, BCrypt.gensalt());
		
		User u= new User();
		u.setUserName(user);
		u.setPass(pass);
		IProxy ip= new ProxyLogin();
		ip.login(u);
		
	}
}
