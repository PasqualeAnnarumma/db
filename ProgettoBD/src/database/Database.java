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
	
	public void aggiungi(String table, Object ... lista) throws SQLException{
		String conn = "INSERT INTO " + table + "(";
		String val = "(";
		int i;
		for (i = 0; i < lista.length/2; i++)
		{
			conn += lista[i] + ", ";
			val += "?, ";
		}
		
		conn = conn.substring(0, conn.length()-2);
		conn += ") values " + val.substring(0, val.length()-2) + ")";
		
		Object[] parametri = new Object[lista.length/2];
		for (int j = 0; i < lista.length; i++, j++)
			parametri[j] = lista[i];
			
		prepared = connessione.prepareStatement(conn);
		set(parametri);
	}
	
	public void set(Object ... lista) throws SQLException {
		int i = 1;
		for (Object obj : lista)
			prepared.setObject(i++, obj);
			
		prepared.executeUpdate();
	}
	
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
