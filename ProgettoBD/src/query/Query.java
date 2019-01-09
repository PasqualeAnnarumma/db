package query;

public class Query {
	
	String nome;
	String query;
	
	public Query(String name, String query) {
		nome = name;
		this.query = query;
	}
	
	public String getName() {
		return nome;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String toString() {
		return nome;
	}
}
