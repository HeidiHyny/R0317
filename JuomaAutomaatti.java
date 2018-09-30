
public class JuomaAutomaatti {
	
	//Luodaan konstruktori
	
	public JuomaAutomaatti() {
		this.kaakaota = 50; 
		this.kahvia = 50;
		this.teet� = 50;
		}
	
	//Luokan attribuutit
	
	private int teet�;
	private int kahvia;
	private int kaakaota;
	
	//Getterit ja setterit
	
	public int getTeet�() {
		return teet�;
	}
	public void setTeet�(int teet�) {
		this.teet� = teet�;
	}
	public int getKahvia() {
		return kahvia;
	}
	public void setKahvia(int kahvia) {
		this.kahvia = kahvia;
	}
	public int getKaakaota() {
		return kaakaota;
	}
	public void setKaakaota(int kaakaota) {
		this.kaakaota = kaakaota;
	}
	
	//metodit juomien valmistukseen
	
	public void valmistaKahvi() {
		System.out.println("Odota hetki, Kahvisi valmistuu. ");
		if (this.kahvia -10 < 0) { //tarkistaa, ett� paino ei ole pienempi kuin nolla
	    	this.kahvia = 0; 
	    	System.out.println("Kahvia ei ole en�� j�ljell�! T�yt� s�ili�.");
	    	}
	    	
	    	else this.kahvia -= 10; //v�hent�� juoman m��r�� -10
		
		System.out.println("Kahvia on j�ljell�: " + this.kahvia + " yksikk��. Teet� on j�ljell�: " + this.teet� + " yksikk��. Kaakaota on j�ljell�: " + this.kaakaota + " yksikk��." +"\n");
	}
	public void valmistaTee() {
		System.out.println("Odota hetki, Teesi valmistuu. ");
		if (this.teet� -10 < 0) { //tarkistaa, ett� paino ei ole pienempi kuin nolla
	    	this.teet� = 0; 
	    	System.out.println("Teet� ei ole en�� j�ljell�! T�yt� s�ili�.");
	    	}
	    	
	    	else this.teet� -= 10; //v�hent�� juoman m��r�� -10
		
		System.out.println("Kahvia on j�ljell�: " + this.kahvia + " yksikk��. Teet� on j�ljell�: " + this.teet� + " yksikk��. Kaakaota on j�ljell�: " + this.kaakaota + " yksikk��." +"\n");
		
	}
	public void valmistaKaakao() {
		System.out.println("Odota hetki, Kaakaosi valmistuu. ");
		if (this.kaakaota -10 < 0) { //tarkistaa, ett� paino ei ole pienempi kuin nolla
	    	this.kaakaota = 0; 
	    	System.out.println("Kaakaota ei ole en�� j�ljell�! T�yt� s�ili�.");
	    	}
	    	
	    	else this.kaakaota -= 10; //v�hent�� juoman m��r�� -10
		
		System.out.println("Kahvia on j�ljell�: " + this.kahvia + " yksikk��. Teet� on j�ljell�: " + this.teet� + " yksikk��. Kaakaota on j�ljell�: " + this.kaakaota + " yksikk��." +"\n");
	}
	public boolean onnistuuko() {
		int luku = (int)(Math.random() * 100+1);
		if (luku <= 25 && luku > 0) {
		return false; }
		else return true;

}}
