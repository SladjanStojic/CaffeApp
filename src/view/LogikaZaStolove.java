package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LogikaZaStolove extends JFrame {

	private JPanel contentPane;


	public LogikaZaStolove() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setText("<html>Logika za stolove<br>\r\n"
				+ "Kako bih rijesio problem kako da postavim stolove na formu i da oni ostanu na istom mjestu nakon ucitavanja stolova iz baze<br>\r\n"
				+ "Morao sam da imam atribute x i y koji su preuzimali vrijednost kordinata i koji su bili cuvani u bazi tako da prilikom iscitavanja iz baze preuzimale su se kordinate tih stolova<br>\r\n"
				+ "i setovani su bili ponovo na isto mjesto <br> \r\n"
				+ "Logika za zauzete stolove <br>\r\n"
				+ "Problem oko zauzetih stolova sam rijesio na nacin da prilikom iscitavanja stoloa na formi iscitavamo i sve otvorene racune koji nisu naplaceni <br>\r\n"
				+ "Ako postoji neki racun koji nije naplacen provjerava se id_stola na tom racun i veze se za taj sto ako i nakon toga taj sto postaje crven <br>\r\n"
				+ "nakon naplate istog tog racuna u bazi setujem da je racun naplacen i automatski se taj sto oslobadja od tog racuna i on pono postaje bijel.\r\n"
				+ "<html>\r\n"
				+ "");
		lblNewLabel.setBounds(20, 25, 662, 210);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(LogikaZaStolove.class.getResource("/slike/STOLOVI2.PNG")));
		lblNewLabel_1.setBounds(10, 232, 786, 506);
		contentPane.add(lblNewLabel_1);
	}

}
