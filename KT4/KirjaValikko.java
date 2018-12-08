
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JTable;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.JButton;
	import javax.swing.JTextField;
	import javax.swing.JPanel;
	import javax.swing.JOptionPane;

	public class KirjaValikko extends JFrame {
		
		final static int MAX_QTY = 10; // Code changed here 
		static int dbItems = 0;
		
		static Kirja[] myDB = new Kirja[MAX_QTY];
		
		static JTable taulukkoKirja; 
		static JButton btnLisaaKirja; 
		static JButton btnPaivita; 
		static JButton btnPoistaRivi; 
		
		public KirjaValikko(){
			super("Minun kirjani");

			initiateCarCollection();
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			BorderLayout sijoittelija = new BorderLayout();
			getContentPane().setLayout(sijoittelija); 
			setBounds(0,0,436,331); 
			setLocationRelativeTo(null); 

			JLabel Otsikko = new JLabel("Minun kirjani:");
			Otsikko.setBounds(10, 11, 187, 14);
			getContentPane().add(Otsikko, BorderLayout.NORTH);
			
			taulukkoKirja = new JTable();
			taulukkoKirja.setRowSelectionAllowed(false);
			taulukkoKirja.setModel(new DefaultTableModel(
				new Object[MAX_QTY][3],  
				new String[] {"nimi", "tekija", "julkaisvuosi"} 
			));
			taulukkoKirja.setBounds(10, 36, 240, 100);
			getContentPane().add(taulukkoKirja, BorderLayout.CENTER);
			
			populateTable();
			
			//Lis‰‰ kirja-nappi
			btnLisaaKirja = new JButton("Lis‰‰ kirja");
			btnLisaaKirja.setBounds(270, 36, 89, 23);
			MyEventHandler commandHandler = new MyEventHandler();
			btnLisaaKirja.addActionListener(commandHandler);
			
			//P‰ivit‰ kirjasto - nappi
			btnPaivita = new JButton("P‰ivit‰ taulukko");
			btnPaivita.setBounds(270, 36, 89, 23);
			MyEventHandler2 commandHandler2 = new MyEventHandler2();
			btnPaivita.addActionListener(commandHandler2);
			
			//Poista rivi-nappi
			btnPoistaRivi = new JButton("Poista valittu rivi");
			btnPoistaRivi.setBounds(270, 36, 89, 23);
			MyEventHandler3 commandHandler3 = new MyEventHandler3();
			btnPoistaRivi.addActionListener(commandHandler3);

			// Luodaan nappipaneeli ja lis‰t‰‰n nappeja
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(btnLisaaKirja);
			buttonPanel.add(btnPaivita);
			buttonPanel.add(btnPoistaRivi);
			
			//Lis‰t‰‰n paneeli ikkunaan
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		}

		//Metodit
		private void populateTable() {
			
			
			for (int row=0; row<dbItems; row++){
				taulukkoKirja.setValueAt(myDB[row].nimi, row, 0);  
				taulukkoKirja.setValueAt(myDB[row].tekija, row, 1);  
				taulukkoKirja.setValueAt(myDB[row].julkaisuvuosi, row, 2);
			}
			
		}
		
		private void initiateCarCollection(){
			
			try {
				 
	            // Luodaan tietokantayhteys
	            Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7268748", "sql7268748", "LeFCnV8taX");
	 
	            // Luodaan Statement-olio, joka keskustelee tietokannan kanssa
	            Statement stmt = con.createStatement();
	           
	            // Luodaan tulosjoukko, johon sijoitetaan kyselyn tulos
	            ResultSet rs = stmt.executeQuery("SELECT * FROM Kirja");
	            
	            // Tulosjoukko k‰yd‰‰n silmukassa l‰pi
	            for (int i = 0; rs.next(); i++) {
				myDB[i] = new Kirja(rs.getString(1), rs.getString(2), rs.getString(3));
				dbItems=i+1;
				
				}
				
				con.close();
	 
	            // Varaudutaan virheisiin
	        } catch (Exception e) {
	            System.out.println(e);
	        }
			
			
		}
		
//Nappien toiminnot
		private class MyEventHandler implements ActionListener
		{
			public void actionPerformed (ActionEvent myEvent) 
			{
				if (myEvent.getSource() == btnLisaaKirja){
					if (dbItems < MAX_QTY){
						getNewCarFromUser();
						populateTable();
						JOptionPane.showMessageDialog(null, "Tallennettu", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Kirjastosi on t‰ynn‰ - sinulla on " + dbItems + " kirjaa", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
						
				}
			}
			}
	
		private class MyEventHandler2 implements ActionListener
		{
			public void actionPerformed (ActionEvent myEvent) 
			{
				if (myEvent.getSource() == btnPaivita){
						initiateCarCollection();
						populateTable();
						JOptionPane.showMessageDialog(null, "Taulukossasi on nyt " + dbItems + " kirjaa", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
	}
			private class MyEventHandler3 implements ActionListener
			{
				public void actionPerformed (ActionEvent myEvent) 
				{
					if (myEvent.getSource() == btnPoistaRivi){
						PoistaKirja();
						populateTable();
					}
				}
		}
		private void getNewCarFromUser(){
			JTextField nimiField = new JTextField(10);
		    JTextField tekijaField = new JTextField(10);
		    JTextField julkaisvuosiField = new JTextField(10); // Code added here
	 
		    JPanel myPanel = new JPanel();
		    
		    myPanel.add(new JLabel("Kirjan nimi:"));
		    myPanel.add(nimiField);
		    
		    myPanel.add(new JLabel("Tekij‰:"));
		    myPanel.add(tekijaField);
		    
		    myPanel.add(new JLabel("Julkaisuvuosi:")); 
		    myPanel.add(julkaisvuosiField); 
		    
		    int result = JOptionPane.showConfirmDialog(null, myPanel, "Syˆt‰ uuden kirjan tiedot", JOptionPane.OK_CANCEL_OPTION);
		    
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	// Luodaan tietokantayhteys
		    	try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7268748", "sql7268748", "LeFCnV8taX");
	 
	            // Luodaan Statement-olio, joka keskustelee tietokannan kanssa
	            Statement stmt = con.createStatement();
	           
	            // Luodaan tulosjoukko, johon sijoitetaan kyselyn tulos
	            ResultSet rs = stmt.executeQuery("SELECT * FROM Kirja");
	         
	            PreparedStatement ps = con.prepareStatement("INSERT INTO Kirja (nimi, tekija, julkaisuvuosi) VALUES (?, ?, ?)");
	            ps.setString(1, nimiField.getText());
	            ps.setString(2, tekijaField.getText());
	            ps.setString(3, julkaisvuosiField.getText());
	            ps.executeUpdate();
	            
		    	con.close();
		    	
		    	} catch (Exception e) {
		            System.out.println(e);
		        }
		    		
		    }}
		
		    private void PoistaKirja () {
 
		        int SelectedRow = taulukkoKirja.getSelectedRow();
	            String dnimi = (String) taulukkoKirja.getValueAt(SelectedRow, 0);
	            String SQL = "DELETE FROM Kirja WHERE nimi = '" + dnimi +"'";
	            
	            Object[] options = { "Poista", "Peruuta" };
	            
	            int choice = JOptionPane.showOptionDialog(null, 
	            "Haluatko varmasti poistaa kirjan nimelt‰? " + dnimi, 
	                "Poista", 
	                JOptionPane.YES_NO_OPTION, 
	                JOptionPane.QUESTION_MESSAGE, 
	                null, 
	                options, 
	                options[0]);
	           
	            // interpret the user's choice
	            if (choice == JOptionPane.YES_OPTION){
	
	            try {
	            	// Luodaan tietokantayhteys
		        Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7268748", "sql7268748", "LeFCnV8taX");
		        // Luodaan Statement-olio, joka keskustelee tietokannan kanssa
		        Statement stmt = con.createStatement();
				stmt.executeUpdate(SQL);
				con.close();
				dbItems--;
	            }
				// Varaudutaan virheisiin
		        catch (Exception e) {
		            System.out.println(e);
		        }
			}}
		
		
		public static void main(String[] args) {
			KirjaValikko frame = new KirjaValikko();
			frame.setVisible(true);
			
		}
	}

