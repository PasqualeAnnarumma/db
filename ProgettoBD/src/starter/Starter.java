package starter;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.Database;

public class Starter {

	public static void main(String[] args)
	{
		String pass = "";
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
		
		Database db = new Database("jdbc:mysql://localhost:3306/takeyourcar?user=root&" + pass + "=n4pl3s1997&autoReconnect=true&useSSL=false");
		ResultSet res;
		try {
			db.connetti();
			
			res = db.tuttiIDespositi();
			System.out.println("Tutti i Depositi");
			System.out.println("--- Inizio ---");
			while (res.next())
				System.out.println(res.getString("Codice") + ", " + res.getString("Comune") + ", " + res.getString("Regione") + " , "
								 + res.getString("Indirizzo") + ", " + res.getInt("Capienza") + ", " + res.getInt("numeroVeicoli"));
			System.out.println("--- Fine ---\n");
			
			res = db.tuttiIClienti();
			System.out.println("Tutti i Clienti");
			System.out.println("--- Inizio ---");
			while (res.next())
				System.out.println(res.getString("CF") + ", " + res.getString("Nome") + ", " + res.getString("Cognome") + " , "
								 + res.getString("LuogoNascita") + ", " + res.getInt("Telefono") + ", " + res.getInt("DataNascita")
								 + res.getString("N_CartadiCredito"));
			System.out.println("--- Fine ---\n");
			
			db.chiudiConnessione();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		System.exit(0);
	}

}
