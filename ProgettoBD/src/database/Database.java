package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import query.Query;

public class Database {
	
	Connection connessione;
	PreparedStatement prepared;
	Statement statement;
	ResultSet result;
	String stringaConnessione;
	ArrayList<Query> listaQuery;
	
	public Database(String stringConnection) {
		stringaConnessione = stringConnection;
		listaQuery = new ArrayList<Query>();
	}
	
	public void connetti() throws SQLException{
		connessione = DriverManager.getConnection(stringaConnessione);
		statement = connessione.createStatement();
	}
	
	public ResultSet eseguiQuery(String str) throws SQLException {
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
	
	public void chiudiConnessione() throws SQLException {
		result.close();
		statement.close();
		connessione.close();
	}
	
	public void addQuery(Query query) {
		listaQuery.add(query);
	}
	
	public Query searchQuery(String nome) {
		for (Query q : listaQuery)
			if (q.getName().equals(nome)) return q;
		
		return null;
	}
	
	public ArrayList<Query> getListaQuery() {
		return listaQuery;
	}
}
