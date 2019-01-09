package starter;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import database.Database;
import interfacciaGrafica.Frame;

public class Starter {

	public static void main(String[] args)
	{
		String nomedb = "takeyourcar";
		String user = "root";
		String pass = "";
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
		
		Database db = new Database("jdbc:mysql://localhost:3306/" + nomedb + "?user=" + user + "&password=" + pass + "&autoReconnect=true&useSSL=false");
		try {
			db.connetti();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRORE CONNESSIONE", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		Frame frame = new Frame(db);
		frame.setVisible(true);
		/*try {
			db.connetti();
			
			res = db.eseguiQuery("SELECT V.Targa, V.Modello, timestampdiff(year, C.DataNascita, curdate()) AS Età\r\n" + 
					"	FROM VEICOLO AS V NATURAL JOIN NOLEGGIATO_DA AS N NATURAL JOIN CLIENTE AS C\r\n" + 
					"    WHERE timestampdiff(year, C.DataNascita, curdate()) < (SELECT MAX(timestampdiff(year, D.DataNascita, curdate()))\r\n" + 
					"															   FROM DIPENDENTE AS D NATURAL JOIN DEPOSITO AS DEP\r\n" + 
					"															   WHERE D.Gestisce = DEP.Codice && DEP.NumeroVeicoli = (SELECT MAX(D.NumeroVeicoli)\r\n" + 
					"																														 FROM DEPOSITO AS D));");
			System.out.println("Tutti i Depositi");
			System.out.println("--- Inizio ---");
			while (res.next())
				System.out.println(res.getString("Targa") + ", " + res.getString("Modello") + ", " + res.getString("Età"));
			System.out.println("--- Fine ---\n");
			
			res = db.eseguiQuery("SELECT * FROM CLIENTE");
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
		}*/
	}

}
