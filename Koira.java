class Koira {
    int ik�;
    String nimi, rotu, ��ni;
    
	public Koira(int ik�, String nimi, String rotu, String ��ni) {
		this.ik� = ik�;
		this.nimi = nimi;
		this.rotu = rotu;
		this.��ni = ��ni;
	}
	public void tulostaTiedot() {// metodi tulostaTiedot()
		System.out.println("Nimi: " + nimi + "\n" + "Ik�: " + ik� + "\n" + "Rotu: " + rotu);
		
	}
	public String anna��ni() {// metodi anna��ni()
		return ��ni;
		
	}}

public class KoiranTesti {
    public static void main(String[] args) {
        Koira rekku = new Koira (2, "Rekku", "Dalmatialainen", "Hau!!!");
        Koira nelli = new Koira (4, "Nelli", "Australianterrieri", "hauhau");

        System.out.println("Koiran tiedot:");
        rekku.tulostaTiedot();
        System.out.println("Koira sanoo: " + rekku.anna��ni());
        System.out.println();
        nelli.tulostaTiedot();
        System.out.println("Koira sanoo: " + nelli.anna��ni());
    }
}