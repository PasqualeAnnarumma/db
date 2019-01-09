package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import database.Database;
import query.Query;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<Query> combo;
	private Database database;
	
	public Frame(Database db) {
		super("takeyourcar database");
		database = db;
		setSize(900, 400);
		setLocation(350, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setResizable(false);
		inizializzaCombo();
		JPanel body = createBody();
		add(body);
	}
	
	public void inizializzaCombo() {
		combo = new JComboBox<Query>();
		ArrayList<Query> lista = database.getListaQuery();
		
		for (Query q : lista)
			combo.addItem(q);
		
		/*comboDipendenti.addItem(database.searchQuery("Tutti i dipendenti").getName());
		
		comboClienti.addItem(database.searchQuery("Tutti i clienti").getName());
		comboClienti.addItem(database.searchQuery("nome, cognome e CF dei clienti che hanno effettuato un pagamento di importo maggiore del più alto premio assicurativo").getName());
		
		comboVeicoli.addItem(database.searchQuery("Tutti i veicoli").getName());
		comboVeicoli.addItem(database.searchQuery("targa e nome modello dei veicoli che sono stati noleggiati dai clienti più giovani del più vecchio dipendente ceh gestisce il deposito con il maggior numero di veicoli").getName());
		
		comboAutovetture.addItem(database.searchQuery("Tutte le autovetture").getName());
		comboMotocicli.addItem(database.searchQuery("Tutti i motocicli").getName());
		comboDitte.addItem(database.searchQuery("Tutte le ditte").getName());
		comboAbbonati.addItem(database.searchQuery("Tutti gli abbonati").getName());
		comboOccasionali.addItem(database.searchQuery("Tutti gli occasionali").getName());
		comboPagamenti.addItem(database.searchQuery("Tutti i pagamenti").getName());
		
		comboViaggi.addItem(database.searchQuery("Tutti i viaggi").getName());
		comboViaggi.addItem(database.searchQuery("Viaggi del primo giorno del mese").getName());
		
		comboAssicurazioni.addItem(database.searchQuery("Tutte le assicurazioni").getName());
		comboRecapiti.addItem(database.searchQuery("Tutti i recapiti").getName());*/
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		JButton button = new JButton("Esegui");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.repaint();
				body.add(creaTabella(database.searchQuery(combo.getSelectedItem().toString()).getQuery()));
				body.revalidate();
				body.repaint();
			}
		});
		
		p.add(combo);
		p.add(button);
		body.add(p, BorderLayout.CENTER);
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
			JPanel p = new JPanel();
			JButton button = new JButton("Esegui");
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel.removeAll();
					panel.repaint();
					panel.add(creaTabella(database.searchQuery(combo.getSelectedItem().toString()).getQuery()));
					panel.revalidate();
					panel.repaint();
				}
			});
			
			p.add(combo);
			p.add(button);
			panel.add(p, BorderLayout.NORTH);
			panel.add(scroll, BorderLayout.CENTER);
			rs.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRORE ESECUZIONE QUERY", JOptionPane.ERROR_MESSAGE);
		}
		
		return panel;
	}
	
}
