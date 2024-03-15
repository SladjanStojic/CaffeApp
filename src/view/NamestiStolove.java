package view;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import Kontroler.Kontroler;
import Model.Sto;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class NamestiStolove extends JFrame {

	
	private JPanel contentPane;
	private int brojStola = 1;
	private List<JToggleButton> listaStolova = new ArrayList<>();
	private Sto sto;
	private int id_stola;
	JToggleButton tglbtnSto;
	private JTextField textFieldNaziv;
	private JToggleButton stoZaBrisanje; 
	private Point pocetnaPozicija;
	List<Sto> lista;
	
	public NamestiStolove() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 538, 360);
		contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnDodajSto = new JButton("Novi Sto");
		btnDodajSto.setBounds(107, 10, 99, 23);
		btnDodajSto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dodajSto(33,33);
			upisiStoUBazu(tglbtnSto);
			
		
			}

		});
		contentPane.setLayout(null);
		contentPane.add(btnDodajSto);
		
		JButton btnMenu = new JButton("Menu");
		
		btnMenu.setBounds(415, 10, 95, 23);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MedjuForma mf= new MedjuForma();
				mf.setVisible(true);
			}
		});
		contentPane.add(btnMenu);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setBounds(10, 11, 86, 20);
		contentPane.add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JButton btnObrisiSto = new JButton("Obrisi Sto");
		btnObrisiSto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				obristiSto(tglbtnSto);
				 
				
				
			}
		});
		btnObrisiSto.setBounds(316, 10, 89, 23);
		contentPane.add(btnObrisiSto);
		dodajStoloveNaFormu();
	
		 
	}
	private void dodajStoloveNaFormu() {
	    lista = Kontroler.getInstanca().vratiListuStolova();

	    for (Sto sto : lista) {
	        JToggleButton tglbtnSto = new JToggleButton(sto.getNazivStola());
	        tglbtnSto.setBounds(sto.getX(), sto.getY(), 103, 65);
	        tglbtnSto.putClientProperty("id", sto.getId_stola());
	        dodajInterakcijuStola(tglbtnSto); // Dodajemo interakciju na sto
	        contentPane.add(tglbtnSto);
	    }

	    contentPane.revalidate();
	    contentPane.repaint();
	}

	private void dodajInterakcijuStola(JToggleButton tglbtnSto) {
	    tglbtnSto.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int idStola = (int) tglbtnSto.getClientProperty("id");
	            System.out.println("ID stola: " + idStola);
	            stoZaBrisanje = tglbtnSto;
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	            pocetnaPozicija = e.getPoint();
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	            updateStoUBazu(tglbtnSto);
	        }
	    });

	    tglbtnSto.addMouseMotionListener(new MouseAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            int deltaX = e.getX() - pocetnaPozicija.x;
	            int deltaY = e.getY() - pocetnaPozicija.y;
	            int novaX = tglbtnSto.getX() + deltaX;
	            int novaY = tglbtnSto.getY() + deltaY;
	            tglbtnSto.setLocation(novaX, novaY);
	        }
	    });
	}

	private void dodajSto(int x, int y) {
	    tglbtnSto = new JToggleButton(textFieldNaziv.getText());
	    tglbtnSto.setBounds(x, y, 103, 65);
	    listaStolova.add(tglbtnSto);
	    contentPane.add(tglbtnSto);
	    dodajInterakcijuStola(tglbtnSto); // Dodajemo interakciju na sto
	    contentPane.revalidate();
	    contentPane.repaint();
	}

	public void obristiSto(JToggleButton tglbtnSto) {
		// TODO Auto-generated method stub
		int id_stola=(int) stoZaBrisanje.getClientProperty("id");
		System.out.println("Za BRISANJE "+id_stola);
		Kontroler.getInstanca().obrisiSto(id_stola);
		 for (int i = 0; i < listaStolova.size(); i++) {
		        JToggleButton button = listaStolova.get(i);
		        int id = (int) button.getClientProperty("id");
		        if (id == id_stola) {
		        	  contentPane.remove(tglbtnSto);
		                contentPane.revalidate();
		                contentPane.repaint();
		            listaStolova.remove(i);
		            break; 
		        }
		 }
	}

	public void upisiStoUBazu(JToggleButton tglbtnSto) {
	    String nazivStola = tglbtnSto.getText(); // Dohvatite tekst stol
	    int x = tglbtnSto.getX();
	    int y = tglbtnSto.getY();
	    int tempId=Kontroler.getInstanca().upisiStoUBazu(nazivStola, x, y);
	    tglbtnSto.putClientProperty("id", tempId);
		
	}


	public void updateStoUBazu(JToggleButton tglbtnSto) {
			id_stola=(int) tglbtnSto.getClientProperty("id");
		 String nazivStola = tglbtnSto.getText(); // Dohvatite tekst stola
		    int x = tglbtnSto.getX();
		    int y = tglbtnSto.getY();
		    Kontroler.getInstanca().updateStoUBazi(id_stola,nazivStola, x, y);
		   
	}
	

}
