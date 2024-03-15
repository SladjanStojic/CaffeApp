package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Kontroler.Kontroler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;

public class Gotovina extends JFrame {

	private JPanel contentPane;
	private JTextField tfNovac;
	private double ukupno;
	JLabel lblUkupno;
	JLabel lblKusur;
	JLabel lblIznosKusura;
	private double iznos;
	private double kusur;
	private int id_racuna;
	private int id_usera;
	DefaultTableModel dtm2=new DefaultTableModel();
	

	public Gotovina(int id, int id_racuna, DefaultTableModel dtm2) {
		this.id_racuna=id_racuna;
		this.id_usera=id;
		this.dtm2=dtm2;
		System.out.println(id_racuna);
		ukupno=Kontroler.getInstanca().vratiUkupanIznosSaRacuna(id_racuna);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGotovina = new JLabel("Gotovina");
		lblGotovina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGotovina.setHorizontalAlignment(SwingConstants.CENTER);
		lblGotovina.setBounds(141, 23, 93, 14);
		contentPane.add(lblGotovina);
		
		JLabel lblIznos = new JLabel("Za naplatu");
		lblIznos.setBounds(105, 73, 68, 14);
		contentPane.add(lblIznos);
		
		lblUkupno = new JLabel("New label");
		lblUkupno.setBounds(183, 73, 68, 14);
		lblUkupno.setText(String.valueOf(ukupno));
		contentPane.add(lblUkupno);
		
		tfNovac = new JTextField();
		tfNovac.setBounds(183, 105, 86, 20);
		contentPane.add(tfNovac);
		tfNovac.setColumns(10);
		
		JLabel lblNovac = new JLabel("Unesi novac");
		lblNovac.setBounds(105, 108, 68, 14);
		contentPane.add(lblNovac);
		
		JButton btnNaplati = new JButton("Naplati");
		btnNaplati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izracunajKusur();
				Kontroler.getInstanca().updateRacun2(id_racuna);
				lblIznosKusura.setText(String.valueOf(kusur));
				JOptionPane.showMessageDialog(null, "Uspesno ste izvrsili placanje");
				stampajRacun(id,id_racuna);
			}
		});
		btnNaplati.setBounds(161, 157, 89, 23);
		contentPane.add(btnNaplati);
		
		lblKusur = new JLabel("KUSUR");
		lblKusur.setBounds(148, 209, 46, 14);
		lblKusur.setText("Kusur");
		contentPane.add(lblKusur);
		
		lblIznosKusura = new JLabel("New label");
		lblIznosKusura.setBounds(223, 209, 46, 14);
		
		contentPane.add(lblIznosKusura);
		//kreirajRacunUDatoteku(id_racuna);
		
	}
	protected void stampajRacun(int id, int id_racuna) {
		// TODO Auto-generated method stub
			DefaultTableModel model = dtm2;
			String imeDatoteke="racun.txt";
			double ukupanRacun=0.0;
			Timestamp vremeNaplate=Kontroler.getInstanca().vratiVremeNaplate(id_racuna);
			String ime=Kontroler.getInstanca().vratiImeUsera(id);
            String prezime=Kontroler.getInstanca().vratiPrezimeUsera(id);
           
			
			try(PrintWriter pw=new PrintWriter(imeDatoteke)) {
				 	pw.println();
				 	pw.println("VREME\t" + vremeNaplate);
			        pw.println("KONOBAR\t" + ime +"\t" + prezime);
				 pw.println("RB\tNAZIV\tKOLICINA\tCENA\tPDV\tUKUPNO");
			     pw.println("---------------------------------------");
			     
			     for (int i = 0; i < model.getRowCount(); i++) {
			    	 
			            int redniB = Integer.parseInt(model.getValueAt(i, 0).toString());
			            String naziv = model.getValueAt(i, 1).toString();
			            int kolicina=Integer.parseInt(model.getValueAt(i, 2).toString());
			            double cena=Double.parseDouble( model.getValueAt(i, 3).toString());
			            double pdv=Double.parseDouble( model.getValueAt(i, 4).toString());
			            double ukupno=Double.parseDouble( model.getValueAt(i, 5).toString());
			            
			            ukupanRacun+=ukupno;
			            pw.println();
			            pw.println(redniB + "\t" + naziv +"\t" + kolicina+"\t"+ cena +"\t"+ pdv+"\t"+ ukupno);
			           
			        }
			     pw.println("---------------------------------------");
			        pw.println("\t\t\t\t\tUKUPNO: " + ukupanRacun);
			        
			      
			     System.out.println("Narud�benica je generirana i spremljena u datoteku: " + imeDatoteke);

			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Do�lo je do gre�ke prilikom pisanja u datoteku: " + e.getMessage());
			}
		
	}

	protected void izracunajKusur() {
		// TODO Auto-generated method stub
		String tekst=tfNovac.getText();
		iznos=Double.parseDouble(tekst);
		kusur=iznos-ukupno;
		System.out.println(kusur);
	}
	
}
