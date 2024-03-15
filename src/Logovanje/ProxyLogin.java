package Logovanje;

import javax.swing.JOptionPane;

import Kontroler.Kontroler;
import Model.Role;
import Model.User;
import view.MedjuForma;

public class ProxyLogin implements IProxy {

	@Override
	public void login(User u) {
	    try {
	        User potvrdjenUser = Kontroler.getInstanca().proveriIvratiUsera(u);
	        if (potvrdjenUser.getId_usera() > 0) {
	            if (potvrdjenUser.getRola().length() > 0) {
	                IProxy mf = new MedjuForma();
	                mf.login(potvrdjenUser);
	                System.out.println(potvrdjenUser.getRola());
	                return;
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Pogresna lozinka");
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom prijave: ");
	    }
	}


}
