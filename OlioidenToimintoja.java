
import java.util.Scanner;

public class OlioidenToimintoja {
    public static void main(String args[]) {
        Tulostaja olio = new Tulostaja();
        olio.Tulosta();
        
    }
}

class Tulostaja { //Sinun teht�v�si on tehd� luokka Tulostaja
	int eka; 
	int toka;
	//joka kysyy k�ytt�j�lt� kaksi kokonaislukua:
	Scanner lukija = new Scanner(System.in);{
		System.out.print("Sy�t� ensimm�inen kokonaisluku: ");
	    eka = lukija.nextInt();
	    System.out.print("Sy�t� toinen kokonaisluku: ");
	    toka = lukija.nextInt();
	}
	public void Tulosta() { //Metodi Tulosta() tulostaa saadun summan n�yt�lle
		System.out.println("Lukujen summa: " + Laskin.Summa(eka, toka));
		//kutsuu luokan Laskin metodia Summa() v�litt�en metodille sy�tteen� saadut kokonaisluvut.
	}
	}

class Laskin {
    static int Summa(int eka, int toka) {
        int summa = eka + toka;
        return summa;
    }
}

