package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ObjasnjenjeBaza extends JFrame {

	private JPanel contentPane;

	
	public ObjasnjenjeBaza() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 992, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(ObjasnjenjeBaza.class.getResource("/slike/ModelovanjeBaze.PNG")));
		lblNewLabel_1.setBounds(0, 130, 946, 532);
		contentPane.add(lblNewLabel_1);
	}

}
