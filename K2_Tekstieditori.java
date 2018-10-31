import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
public class K2_Tekstieditori {
	public static void main(String args[]){
	// Luodaan frame
	JFrame frame = new JFrame("Testi-frame");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300,300);
	frame.setVisible(true);
	BorderLayout sijoittelija = new BorderLayout();  
	frame.getContentPane().setLayout(sijoittelija);
	JPanel toolbars = new JPanel( new GridLayout(0, 1) );
	
	//Luodaan tekstieditori jo luokan alussa
	JTextArea ta = new JTextArea(30,30);
	
	//Luodaan menu ja 2 menuitemi‰
	JMenuBar mb = new JMenuBar();

	JMenu m1 = new JMenu("Tiedosto");
	JMenuItem m11 = new JMenuItem("Avaa");
	//Lis‰t‰‰n Avaa-toiminnolle kuuntelija
	m11.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	//Lis‰t‰‰n toiminnalisuus valmiin tiedoston valitsemiseksi
	JFileChooser valintaikkuna = new JFileChooser();
	valintaikkuna.showOpenDialog(null);
	String rivi = "";
	String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();	
	//Luetaan tiedostoa rivi kerrallaan ja varaudutaan poikkeuksiin try-catch-rakenteella
	try {
		Scanner lukija = null;
		File tiedosto = new File(uusiTiedosto);
		lukija = new Scanner(tiedosto);
		
		while (lukija.hasNextLine()) {
			rivi += lukija.nextLine()+"/n";
			System.out.println(rivi);
		}
	} catch (FileNotFoundException p) {
		System.out.println("Tiedostoa ei lˆydy");
	}
	ta.setText(rivi);
	}
		
	});
	
	//Luodaan menuvalikko ja tapahtumakuuntelijat
	//Tallennus-toiminallisuus
	m11.setIcon(new ImageIcon(OmaEditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
	JMenuItem m12 = new JMenuItem("Tallenna");
	//Lis‰t‰‰n tapahtumakuuntelija
	m12.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	//Tiedoston tallenusikkuna
	JFileChooser valintaikkuna = new JFileChooser();
	valintaikkuna.showSaveDialog(null);
	
	String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
	System.out.println("Kirjoitettava tiedosto: " +uusiTiedosto);
			
	//Lis‰t‰‰n tallennuksen hoitava koodi
	try {
	PrintWriter writer = new PrintWriter( uusiTiedosto );
	String sisalto = ta.getText();
				
	writer.println( sisalto );
				
	//vakiotoimet
	writer.flush();
	writer.close();
				
	} catch (Exception e1) {
	System.out.println("Tallennuksessa tapahtui virhe");
	e1.printStackTrace();
	}
	}
	});
	m12.setIcon(new ImageIcon(OmaEditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
	JMenuItem m13 = new JMenuItem("Lopeta");
	m13.setIcon(new ImageIcon(OmaEditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
	JMenuItem m14 = new JMenuItem("Sulje");
	m14.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	JMenu m2 = new JMenu("Muokkaa");
	//Etsi-toiminnallisuus menuun
	JMenuItem m21 = new JMenuItem("Etsi");
	m21.setIcon(new ImageIcon(OmaEditori.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
	//Lis‰t‰‰n tapahtumakuuntelija
	m21.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
	//Lis‰t‰‰n t‰h‰n etsi-toiminnallisuus
	
	String sisalto = ta.getText();
	sisalto = sisalto.toLowerCase();
		
	String haettava = "auto";
	int indeksi = sisalto.indexOf(haettava);
	System.out.println("Indeksi: " +indeksi);
		
	//Valinnan v‰riin tehoste
	ta.setSelectedTextColor(Color.CYAN);
		
	ta.setSelectionStart(indeksi);
	ta.setSelectionEnd(indeksi + haettava.length());
	}
	});//Lopetetaan tapahtumakuuntelija
	//Korvaa-toiminallisuus menuun
	JMenuItem m22 = new JMenuItem("Korvaa");
	m22.setIcon(new ImageIcon(OmaEditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
	//Lis‰t‰‰n tapahtumakuuntelija
	m22.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	
	//Lis‰t‰‰n t‰h‰n replaceAll()-toiminnallisuus
	
	String korvaa = ta.getText(); 
	String replaceString=korvaa.replaceAll("auto","kaara");//replaces all occurrences of "is" to "was"
	ta.setText(replaceString); //sijoitetaan korvattavan tiedon sis‰lt‰v‰ muuttuja tekstikentt‰‰n
	}
	});
	
	//M‰‰ritet‰‰n t‰h‰n dialogi-ikkunan sis‰ltˆ
	//Paneeli
	JPanel myPanel = new JPanel();
	//Tekstikentt‰
	String text = 
	"Tekij‰" +"\n" +
	"Heidi Hynynen" +"\n"+
	"I Made this" +"\n";
    JTextArea textArea = new JTextArea(text);
textArea.setColumns(30);
textArea.setLineWrap( true );
textArea.setWrapStyleWord( true );
textArea.setSize(textArea.getPreferredSize().width, 1);
textArea.setForeground(Color.BLUE);
	myPanel.add(textArea);
	//Buttoni joka avaa hyperlinkin
	JButton FB = new JButton("Facebook");
	//Buttonin oma kuuntelija
	FB.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent eg) {
	try {
	    Desktop.getDesktop().browse(new URL("http://www.facebook.com").toURI());
	} catch (Exception eg1) {}
	}});
	//lis‰t‰‰n buttoni paneeliin
	myPanel.add(FB);
	
	//Kiinnitet‰‰n toiminnallisuus menuun : avautuva dialogi-ikkuna
	JMenu m3 = new JMenu("Tietoja");
	JMenuItem m31 = new JMenuItem("Tietoja ohjelmasta");
	m31.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog
			(
				null, 
				myPanel, 
				"Tekij‰n tiedot : ",
				JOptionPane.INFORMATION_MESSAGE
			);
			 
			
		}
	});
	
	
	//Sijoitetaan menupaneeli NORTHiin
	//frame.add(mb, BorderLayout.NORTH);
	//Lis‰t‰‰n itemeill‰ emos‰iliˆt
	mb.add(m1);
	mb.add(m2);
	mb.add(m3);
	m1.add(m11);
	m1.add(m12);
	m1.add(m13);
	m1.add(m14);
	m2.add(m21);
	m2.add(m22);
	m3.add(m31);
	//Luodaan uusi paneeli ikonimenulle
	JPanel jp = new JPanel();
	jp.setAlignmentX(Component.LEFT_ALIGNMENT);
	//Lis‰t‰‰n menuikonit
	ImageIcon open = new ImageIcon("images/save.jpg");
    
	ImageIcon save = new ImageIcon("Save");
    
    ImageIcon cut = new ImageIcon("Save");
	jp.setLayout(new GridLayout(0, 5, 0, 0));
	
	JButton btn3 = new JButton("");
	btn3.setBorder(null);
	btn3.setIcon(new ImageIcon(OmaEditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
	jp.add(btn3);
	
	JButton btn1 = new JButton("");
	btn1.setBorder(null);
	btn1.setIcon(new ImageIcon(OmaEditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
	btn1.setBorderPainted(false);
	jp.add(btn1);
	
	JButton btn2 = new JButton("");
	btn2.setIcon(new ImageIcon(OmaEditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
	btn2.setBorder(null);
	jp.add(btn2);

	//Lis‰t‰‰n ikonit paneeliin
	toolbars.add(mb);
	toolbars.add(jp);
	//Sijoitetaan paneeli
	frame.getContentPane().add(toolbars, BorderLayout.NORTH);
	
	
	//Luodaan uusi paneeli isolle tekstikent‰lle ikkunan keskell‰
	JPanel jp1 = new JPanel();
			
			
	jp1.add(ta);
	frame.getContentPane().add(jp1,BorderLayout.CENTER);
	
	
	}	}

