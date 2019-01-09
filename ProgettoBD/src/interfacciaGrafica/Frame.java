package interfacciaGrafica;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import database.Database;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final String TUTTIIDIPENDENTI = "SELECT * FROM DIPENDENTE";
	private final String TUTTIICLIENTI = "SELECT * FROM CLIENTE";
	private final String TUTTIIVEICOLI = "SELECT * FROM VEICOLO";
	private final String TUTTELEAUTOVETTURE = "SELECT * FROM AUTOVETTURA";
	private final String TUTTIIMOTOCICLI = "SELECT * FROM MOTOCICLO";
	private final String TUTTELEDITTE = "SELECT * FROM DITTA_MANUTENZIONE";
	private final String TUTTIGLIABBONATI = "SELECT * FROM ABBONATO";
	private final String TUTTIGLIOCCASONALI = "SELECT * FROM OCCASIONALE";
	private final String TUTTIIPAGAMENTI = "SELECT * FROM PAGAMENTO";
	private final String TUTTIIVIAGGI = "SELECT * FROM VIAGGIO;";
	private final String TUTTELEASSICURAZIONI = "SELECT * FROM ASSICURAZIONE";
	private final String TUTTIIRECAPITI = "SELECT * FROM RECAPITO";
	private Database database;
	
	public Frame(Database db) {
		super("takeyourcar database");
		database = db;
		setSize(800, 400);
		setLocation(350, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setResizable(false);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JTabbedPane pannello = new JTabbedPane();
		pannello.add("Dipendenti", creaTabella(TUTTIIDIPENDENTI));
		pannello.add("Clienti", creaTabella(TUTTIICLIENTI));
		pannello.add("Veicoli", creaTabella(TUTTIIVEICOLI));
		pannello.add("Autovetture", creaTabella(TUTTELEAUTOVETTURE));
		pannello.add("Motocicli", creaTabella(TUTTIIMOTOCICLI));
		pannello.add("Ditte manutenzione", creaTabella(TUTTELEDITTE));
		pannello.add("Clienti", creaTabella(TUTTIICLIENTI));
		pannello.add("Abbonati", creaTabella(TUTTIGLIABBONATI));
		pannello.add("Occasionali", creaTabella(TUTTIGLIOCCASONALI));
		pannello.add("Pagamenti", creaTabella(TUTTIIPAGAMENTI));
		pannello.add("Viaggi", creaTabella(TUTTIIVIAGGI));
		pannello.add("Assicurazioni", creaTabella(TUTTELEASSICURAZIONI));
		pannello.add("Recapiti", creaTabella(TUTTIIRECAPITI));
		
		body.add(pannello, BorderLayout.CENTER);
		return body;
	}
	
	public JPanel creaTabella(String query) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		ResultSet rs = null;
		try {
			rs = database.eseguiQuery(query);
			ResultSetMetaData rsm = rs.getMetaData();
			int colonne = rsm.getColumnCount();
			rs.last();
			int righe = rs.getRow();
			rs.beforeFirst();
			
			String[] intestazione = new String[colonne];			
			String[][] data =  new String[righe][intestazione.length];
			
			for (int i = 0; i < colonne; i++)
				intestazione[i] = rsm.getColumnName(i+1);
			
			int i = 0;
			while (rs.next())
			{
				for (int j = 0; j < colonne; j++)
				{
					try {
						data[i][j] = rs.getObject(j+1).toString();
					} catch (SQLException ex) {
						data[i][j] = "0000-00-00";
					} catch (NullPointerException ex) {
						data[i][j] = "--";
					}
				}
				
				i++;
			}
			
			JTable tabella = new JTable(data, intestazione);
			JScrollPane scroll = new JScrollPane(tabella);
			panel.add(scroll, BorderLayout.NORTH);
			rs.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRORE ESECUZIONE QUERY", JOptionPane.ERROR_MESSAGE);
		}
		
		return panel;
	}
	
}
