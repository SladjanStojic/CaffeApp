package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import DTO.StatistikaDTO;
import Kontroler.Kontroler;
import Model.User;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class Statistika extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JComboBox comboBox;
	private DefaultTableModel dtm=new DefaultTableModel();
	JDateChooser dc1;
	private int id_konobara;

	public Statistika() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKonobar = new JLabel("Konobar");
		lblKonobar.setBounds(14, 57, 58, 26);
		contentPane.add(lblKonobar);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_konobara=((User)comboBox.getSelectedItem()).getId_usera();
			}
		});
		comboBox.setBounds(82, 59, 104, 22);
		contentPane.add(comboBox);
		
		dc1 = new JDateChooser();
		dc1.setBounds(393, 57, 81, 20);
		dc1.setDate(new Date());
		contentPane.add(dc1);
	
		
		JButton btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popuniTabelu();
			}
		});
		btnPretrazi.setBounds(396, 263, 89, 23);
		contentPane.add(btnPretrazi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 100, 425, 143);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		
		Object[]kolone= {"IME", "PREZIME", "KARTICA", "GOTOVINA", "UKUPNO"};
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		
		
		vratiKonobareIzBaze();
		popuniTabelu();
		
		
	}

	private void popuniTabelu() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0); 
		for(StatistikaDTO s:Kontroler.getInstanca().vratiPodatkeZaStatistiku(id_konobara,dc1)) {
			Object [] red= {s.getIme(),s.getPrezime(),s.getGotovina(),s.getKartica(),s.getUkupno()};
			dtm.addRow(red);
		}
	
	}

	protected void vratiKonobareIzBaze() {
		// TODO Auto-generated method stub
		List<User> konobari = Kontroler.getInstanca().vratiKonobareIzBaze();
		for(User u :konobari) {
			comboBox.addItem(u);
		}
	}
}
