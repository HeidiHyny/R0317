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

	public static void main(String[] args) throws Exception  { // eli ei käsitellä poikkeuksia

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

		//Luodaan hahsmap ja asetetaan olion taulukoiden sisältö sinne
		HashMap<String, String> parit = new HashMap<>();
		for (int i = 0; i < a.kaupungit.length; i++) {
			parit.put(a.avaimet[i], a.kaupungit[i]);
		}
		
		//Muodostetaan Iterator joka järjestelee taulukon itr nimiseen muuttujaan
		Iterator<Entry<String, String>> itr = parit.entrySet().iterator(); 
		//Tulostetaan sanat hashmapista
		System.out.print("Sanakirjan sisältö: {");
		while (itr.hasNext()) {
			HashMap.Entry<String,String> alkio = (HashMap.Entry<String,String>) itr.next();
			System.out.print( alkio.getKey() + " = " + alkio.getValue() + ", ");
		}
		System.out.print("}");
		System.out.println();
		
		//Kysytään käyttäjältä tulostettava avainpari ja tulostetaan se
		//tuodaan mukaan skanneriluokka
		Scanner lukija = new Scanner(System.in);
		//Luodaan tarvittavat muuttujat
		String sana = null;
		String alku = null;
		String kaannos = null;
		
		//Kysytään käyttäjältä tulostettava avainpari ja tulostetaan se
		do  {
		System.out.print("Minkä sanan käännöksen haluat tietää? (tyhjä sana lopettaa) ");
		sana = lukija.nextLine();
		System.out.println("Sanan \"" + sana + "\" käännös on \"" + parit.get(sana) + "\"");
	} 
		while (!sana.equals("")); {
			System.out.println("Ohjelma lopetetaan, kiitos käynnistä!");
		}
		//Kysytään käyttäjältä uudet arvot avainpariksi
		do  {
			System.out.println("Sana alkukielellä? (tyhjä lopettaa) ");
			alku = lukija.nextLine();
			if (alku.equals("")){
			
			//Loppusanoman printtaus
			System.out.println("Ohjelma lopetetaan, kiitos käynnistä!");
			}
			else {
			System.out.println("Sana käännettynä? (tyhjä lopettaa) ");
			kaannos = lukija.nextLine();
			parit.put(alku, kaannos);
			
			System.out.println("Sanan \"" + alku + "\" käännös on \"" + parit.get(alku) + "\"");
			}
		}
			while (!alku.equals("")); {
			
			
			}

			//tallennetaan sanaparit tiedostoihin
			enc.writeObject(parit+"");
			talteen.writeObject(parit);
			
			//suljetaan myös ohjelman kääntäminen tiedostoon jos tyhjä
			enc.close();
			talteen.flush();
			apuTied.close();

		// Luetaan oliot takaisin levyltä EI TOIMI

		Sanoja uusi =  (Sanoja) luettuData.readObject();
		Sanoja toinenUusi = (Sanoja) luettuData.readObject();

		//Sanaparit2 c = deserializeFromXML();
		

		// Tulostetaan ne
		System.out.println(uusi);
		System.out.println(toinenUusi);
		
	}
}