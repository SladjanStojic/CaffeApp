package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import Kontroler.Kontroler;
import Model.Racun;
import Model.Sto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;

public class KreirajPorudzbinu extends JFrame {

	private JPanel contentPane;
	JToggleButton tglbtnSto;
	private int id_racuna;
	private int id_stola;
	private JToggleButton tglbtn;
	private int id;
	

	public KreirajPorudzbinu(int i) {
		System.out.println("ID je " + i);
		id=i;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 389);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				dodajStoloveNaFormu();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dodajStoloveNaFormu();
		dodajEventHandler();
		
	}
	private void dodajEventHandler() {
		// TODO Auto-generated method stub
		for(Component component : contentPane.getComponents()) {
			if(component instanceof JToggleButton) {
				JToggleButton tglbtn= (JToggleButton) component;
				tglbtn.addActionListener(new ActionListener() {
				
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
						
						Porudzbina p = new Porudzbina(tglbtn,id);
						
						
						p.setVisible(true);
					}
					
				});
	}
		}
	}

	private void dodajStoloveNaFormu() {
	    List<Sto> listaStolova = Kontroler.getInstanca().vratiListuStolova();
	    List<Racun> listaOtvorenihRacuna = Kontroler.getInstanca().vratiSveRacune();
	   for (Sto sto : listaStolova) {
	        JToggleButton tglbtnSto = new JToggleButton(sto.getNazivStola());
	        tglbtnSto.setBounds(sto.getX(), sto.getY(), 103, 65);
	        tglbtnSto.putClientProperty("id_stola", sto.getId_stola());
	        id_stola=(int) tglbtnSto.getClientProperty("id_stola");
	        Racun povezaniRacun = null;
	        for (Racun racun : listaOtvorenihRacuna) {
	            if (racun.getId_stola() == sto.getId_stola()) {
	                povezaniRacun = racun;
	              tglbtnSto.putClientProperty("id_racuna2", povezaniRacun.getId_racuna());
	              id_racuna=(int) tglbtnSto.getClientProperty("id_racuna2");
	              System.out.println("tgl" +id_racuna);
	              
	                break;
	          } 
	      }
	        if (povezaniRacun!=null) {
	            tglbtnSto.setBackground(Color.RED);
	        } else {
	        	 tglbtnSto.setBackground(Color.WHITE);
	         }

	        contentPane.add(tglbtnSto);
	    }

	    contentPane.revalidate();
	    contentPane.repaint();
	}
}
