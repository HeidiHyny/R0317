import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class KT3_Sanakirja2 {
// Tallennetaan Auto-olio sarjallistettuna tiedostoon

	public static void main(String[] args) throws Exception  { // eli ei k�sitell� poikkeuksia

		// Tiedostojen kirjoittamista varten
		FileOutputStream apuTied = new FileOutputStream("SanaparitTallessa.oma");
		ObjectOutputStream talteen = new ObjectOutputStream(apuTied);

		// Tiedostojen lukemista varten
		FileInputStream fis = new FileInputStream("SanaparitTallessa.oma");
		ObjectInputStream luettuData = new ObjectInputStream(fis);
		
		//XML tiedostoja varten
		FileOutputStream tiedosto = new FileOutputStream("Kirjakirjanen.xml");
		XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));

		// Luodaan uusi olio
		Sanoja a = new Sanoja();

		//Luodaan hahsmap ja asetetaan olion taulukoiden sis�lt� sinne
		HashMap<String, String> parit = new HashMap<>();
		for (int i = 0; i < a.kaupungit.length; i++) {
			parit.put(a.avaimet[i], a.kaupungit[i]);
		}
		
		//Muodostetaan Iterator joka j�rjestelee taulukon itr nimiseen muuttujaan
		Iterator<Entry<String, String>> itr = parit.entrySet().iterator(); 
		//Tulostetaan sanat hashmapista
		System.out.print("Sanakirjan sis�lt�: {");
		while (itr.hasNext()) {
			HashMap.Entry<String,String> alkio = (HashMap.Entry<String,String>) itr.next();
			System.out.print( alkio.getKey() + " = " + alkio.getValue() + ", ");
		}
		System.out.print("}");
		System.out.println();
		
		//Kysyt��n k�ytt�j�lt� tulostettava avainpari ja tulostetaan se
		//tuodaan mukaan skanneriluokka
		Scanner lukija = new Scanner(System.in);
		//Luodaan tarvittavat muuttujat
		String sana = null;
		String alku = null;
		String kaannos = null;
		
		//Kysyt��n k�ytt�j�lt� tulostettava avainpari ja tulostetaan se
		do  {
		System.out.print("Mink� sanan k��nn�ksen haluat tiet��? (tyhj� sana lopettaa) ");
		sana = lukija.nextLine();
		System.out.println("Sanan \"" + sana + "\" k��nn�s on \"" + parit.get(sana) + "\"");
	} 
		while (!sana.equals("")); {
			System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!");
		}
		//Kysyt��n k�ytt�j�lt� uudet arvot avainpariksi
		do  {
			System.out.println("Sana alkukielell�? (tyhj� lopettaa) ");
			alku = lukija.nextLine();
			if (alku.equals("")){
			
			//Loppusanoman printtaus
			System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!");
			}
			else {
			System.out.println("Sana k��nnettyn�? (tyhj� lopettaa) ");
			kaannos = lukija.nextLine();
			parit.put(alku, kaannos);
			
			System.out.println("Sanan \"" + alku + "\" k��nn�s on \"" + parit.get(alku) + "\"");
			}
		}
			while (!alku.equals("")); {
			
			
			}

			//tallennetaan sanaparit tiedostoihin
			enc.writeObject(parit+"");
			talteen.writeObject(parit);
			
			//suljetaan my�s ohjelman k��nt�minen tiedostoon jos tyhj�
			enc.close();
			talteen.flush();
			apuTied.close();

		// Luetaan oliot takaisin levylt� EI TOIMI

		Sanoja uusi =  (Sanoja) luettuData.readObject();
		Sanoja toinenUusi = (Sanoja) luettuData.readObject();

		//Sanaparit2 c = deserializeFromXML();
		

		// Tulostetaan ne
		System.out.println(uusi);
		System.out.println(toinenUusi);
		
	}
}