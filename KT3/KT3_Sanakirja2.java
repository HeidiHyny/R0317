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
// Tallennetaan olio sarjallistettuna tiedostoon

	static  HashMap<String, String> deserializeFromXML(HashMap<String, String> parit) throws Exception {
		FileInputStream fis = new FileInputStream("Kirjakirjanen.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		HashMap<String, String> decodedParit = (HashMap<String, String>) decoder.readObject();
		decoder.close();
		fis.close();
return decodedParit;
}
	private static void serializeToXML(HashMap<String, String> parit) throws Exception  {
		FileOutputStream fos = new FileOutputStream("Kirjakirjanen.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(parit);
		encoder.close();
		fos.close();
}
	
	public static void main(String[] args) throws Exception  { // eli ei käsitellä poikkeuksia

		// Tiedostojen kirjoittamista varten
		FileOutputStream apuTied = new FileOutputStream("SanaparitTallessa.oma");
		ObjectOutputStream talteen = new ObjectOutputStream(apuTied);

		// Tiedostojen lukemista varten
		FileInputStream fis = new FileInputStream("SanaparitTallessa.oma");
		ObjectInputStream luettuData = new ObjectInputStream(fis);

		
		// Luodaan uusi hashmap sanapareilla
		HashMap<String, String> parit = new HashMap<>(); {

			String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
			String[] englanti = { "cat", "dog", "horse", "car", "boat" };

			for (int i = 0; i < suomi.length; i++) {
				parit.put(suomi[i], englanti[i]);
			}		
		}

		
		//Kysytään käyttäjältä tulostettava avainpari ja tulostetaan se
		//tuodaan mukaan skanneriluokka
		Scanner lukija = new Scanner(System.in);
		
		//Luodaan tarvittavat muuttujat
		
		//Kysytään käyttäjältä tulostettava avainpari ja tulostetaan se
		System.out.println(parit);
		
		while (true)  {
		System.out.print("Minkä sanan käännöksen haluat tietää? (tyhjä sana lopettaa) ");
		String sana = lukija.nextLine();
		if (!sana.isEmpty()) {
		System.out.println("Sanan \"" + sana + "\" käännös on \"" + parit.get(sana) + "\" ");
		}
		if (sana.isEmpty()) {
			System.out.println("Ohjelma lopetetaan, kiitos käynnistä!");
			break;
		}}
	

while (true) {
	System.out.println("Sana alkukielellä? (tyhjä lopettaa) ");
	String alku = lukija.nextLine();
	if (alku.isEmpty()) {
		System.out.println("Ohjelma lopetetaan, kiitos käynnistä!");
		break;
	}
	if (!alku.isEmpty()) {
		System.out.println("Sana käännettynä? (tyhjä lopettaa) ");
		String kaannos = lukija.nextLine();
		
		parit.put(alku, kaannos);
		serializeToXML(parit);
		
		System.out.println("Sanan \"" + alku + "\" käännös on \"" + parit.get(alku) + "\"");
	}
	
}

parit = deserializeFromXML(parit);

System.out.println("\nSanat sanakirjasta:\n");

Iterator<Entry<String, String>> it = parit.entrySet().iterator();
while (it.hasNext()) {
	HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) it.next();
	System.out.println(alkio.getKey() + " = " + alkio.getValue());
}	
					
		
	}}
