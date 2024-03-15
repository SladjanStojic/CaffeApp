package StrategyPattern;

public class KarticnoPlacanje implements StrategyPlacanje {
	
	private String broj;
	private String imeNosioca;
	private String dautmIsteka;
	private String ccv;
	
	public KarticnoPlacanje (String broj,String imeNosioca,String dautmIsteka,String ccv ) {
		this.broj=broj;
		this.imeNosioca=imeNosioca;
		this.dautmIsteka=dautmIsteka;
		this.ccv=ccv;
	}


	@Override
	public void plati(double ukupno) {
		// TODO Auto-generated method stub
		
	}

}
