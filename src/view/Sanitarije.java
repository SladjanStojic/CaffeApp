package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class Sanitarije extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNaziv;
	private JTable table;
	DefaultTableModel dtm= new DefaultTableModel();
	JSpinner spinner;


	public Sanitarije() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setBounds(84, 23, 142, 20);
		contentPane.add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JLabel lblArtikli = new JLabel("Artikli");
		lblArtikli.setBounds(11, 26, 63, 14);
		contentPane.add(lblArtikli);
		
		JLabel lblKolicina = new JLabel("Kolicina");
		lblKolicina.setBounds(11, 83, 46, 14);
		contentPane.add(lblKolicina);
		
		spinner = new JSpinner();
		spinner.setBounds(148, 80, 75, 20);
		contentPane.add(spinner);
		
		JButton btnUnazad = new JButton("MENU");
		btnUnazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MedjuForma mf= new MedjuForma();
				mf.setVisible(true);
			}
		});
		btnUnazad.setBounds(380, 11, 89, 23);
		contentPane.add(btnUnazad);
		
		JButton btnPoruci = new JButton("Poruci");
		btnPoruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kreirajPorudzbenicu();
			}
		});
		btnPoruci.setBounds(356, 283, 89, 23);
		contentPane.add(btnPoruci);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 113, 412, 159);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=textFieldNaziv.getText();
				int kolicina=(int) spinner.getValue();
				Object []red= {naziv,kolicina};
				dtm.addRow(red);
				textFieldNaziv.setText("");
				spinner.setValue(0);
			}
		});
		btnDodaj.setBounds(246, 79, 89, 23);
		contentPane.add(btnDodaj);
		
		
		Object [] kolona= {"NAZIV ARTIKLA", "KOLICINA"};
		dtm.addColumn(kolona[0]);
		dtm.addColumn(kolona[1]);
	}


	protected void kreirajPorudzbenicu() {
		// TODO Auto-generated method stub
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		String imeDatoteke="narudzbenica.txt";
		
		try(PrintWriter pw=new PrintWriter(imeDatoteke)) {
			 pw.println("Artikal\tKoli�ina");
		     pw.println("-------------------");
		     
		     for (int i = 0; i < model.getRowCount(); i++) {
		            String artikal = model.getValueAt(i, 0).toString();
		            int kolicina = Integer.parseInt(model.getValueAt(i, 1).toString());
		            pw.println(artikal + "\t" + kolicina);
		        }
		     System.out.println("Narud�benica je generirana i spremljena u datoteku: " + imeDatoteke);

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Do�lo je do gre�ke prilikom pisanja u datoteku: " + e.getMessage());
		}
	}
}
