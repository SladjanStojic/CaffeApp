package Model;


import javax.swing.JToggleButton;

public class JTogleButtonSTO extends JToggleButton implements StoInterface{
	
	

	public JTogleButtonSTO (String naziv,int x, int y) {
		super(naziv);
		setBounds(x, y, 103, 5);
	}
	
	
	
	@Override
	public String getNaziv() {
		// TODO Auto-generated method stub
		return super.getText();
	}
	
	@Override
	public int getX() {
		return super.getX();
	}
	
	@Override
	public int getY() {
		return super.getY();
	}

}
