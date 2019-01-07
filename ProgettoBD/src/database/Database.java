package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	Connection connessione;
	PreparedStatement prepared;
	Statement statement;
	ResultSet result;
	String stringaConnessione;
	
	public Database(String stringConnection) {
		stringaConnessione = stringConnection;	
	}
	
	public void connetti() throws SQLException{
		connessione = DriverManager.getConnection(stringaConnessione);
		statement = connessione.createStatement();
	}
	
	private ResultSet eseguiQuery(String str) throws SQLException {
		result = statement.executeQuery(str);
		return result;
	}
	
	/*public void aggiungiTifoso(String cognome, String nome, String telefono, String via, String città) throws SQLException {
		prepared = connessione.prepareStatement("INSERT INTO Tifoso (IDTif, cognome, nome, telefono, via, città) values (?, ?, ?, ?, ?, ?)");
		prepared.setInt(1, 15);
		prepared.setString(2, cognome);
		prepared.setString(3, nome);
		prepared.setString(4, telefono);
		prepared.setString(5, via);
		prepared.setString(6, città);
		prepared.executeUpdate();
	}*/
	
	public ResultSet tuttiIDespositi() throws SQLException {
		return eseguiQuery("SELECT * FROM DEPOSITO");
	}
	
	public ResultSet tuttiIClienti() throws SQLException {
		return eseguiQuery("SELECT * FROM CLIENTE");
	}
	
	public void chiudiConnessione() throws SQLException {
		connessione.close();
	}
	
}
