package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ObjasnjenjeRegistracija extends JFrame {

	private JPanel contentPane;


	public ObjasnjenjeRegistracija() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblObjasnjenje = new JLabel("New label");
		lblObjasnjenje.setVerticalAlignment(SwingConstants.TOP);
		lblObjasnjenje.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjasnjenje.setText("<html>Registracija: Na Formi za logovanje nalazi se dugme za registraciju koje kada kliknemo otvara nam se registracijona forma koja je implementirana preko Proxy patterna.<br>Nakon toga se ostvaruje konekcija sa bazom koja kriptuje pasword i unosi novogs usera u bazu a nakon toga mu dodjeljuje rolu admin.</html>");
		
		lblObjasnjenje.setBounds(27, 11, 559, 93);
		contentPane.add(lblObjasnjenje);
		
		
		JLabel lblSlika1 = new JLabel("New label");
		lblSlika1.setIcon(new ImageIcon(ObjasnjenjeRegistracija.class.getResource("/slike/RegistracijaKod.PNG")));
		lblSlika1.setBounds(10, 114, 590, 308);
		contentPane.add(lblSlika1);

	}
}
