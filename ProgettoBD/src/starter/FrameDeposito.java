package starter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Database;

public class FrameDeposito extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Database database;
	private JTextField Codice;
	private JTextField Comune;
	private JTextField Regione;
	private JTextField Indirizzo;
	private JTextField Capienza;
	private JTextField NumeroVeicoli;
	
	public FrameDeposito(Database db) {
		super("Aggiungi deposito");
		database = db;
		Codice = new JTextField(20);
		Comune = new JTextField(20);
		Regione  = new JTextField(20);;
		Indirizzo  = new JTextField(20);
		Capienza  = new JTextField(20);
		NumeroVeicoli  = new JTextField(20);
		setLocation(500, 100);
		setSize(350, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(7, 2));
		JPanel textComune = new JPanel();
		textComune.add(Comune);
		JPanel textCodice = new JPanel();
		textCodice.add(Codice);
		JPanel textRegione = new JPanel();
		textRegione.add(Regione); 
		JPanel textIndirizzo = new JPanel();
		textIndirizzo.add(Indirizzo);
		JPanel textCapienza = new JPanel();
		textCapienza.add(Capienza);
		JPanel textNmeroVeicoli = new JPanel();
		textNmeroVeicoli.add(NumeroVeicoli);
		
		JPanel panelCodice = new JPanel();
		panelCodice.add(new JLabel("Codice: "));
		JPanel panelComune = new JPanel();
		panelComune.add(new JLabel("Comune: "));
		JPanel panelRegione = new JPanel();
		panelRegione.add(new JLabel("Regione: "));
		JPanel panelIndirizzo = new JPanel();
		panelIndirizzo.add(new JLabel("Indirizzo : "));
		JPanel panelCapienza = new JPanel();
		panelCapienza.add(new JLabel("Capienza : "));
		JPanel panelNumeroVeicoli = new JPanel();
		panelNumeroVeicoli.add(new JLabel("Numero Veicoli : "));
		
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Codice.getText().equals("") || Comune.getText().equals("") || Indirizzo.getText().equals("") || Regione.getText().equals("") || Capienza.getText().equals("") || NumeroVeicoli.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("DEPOSITO", "Codice", "Comune", "Indirizzo", "Regione", "Capienza", "NumeroVeicoli", Codice.getText(), Comune.getText(), Indirizzo.getText(), Regione.getText(), Capienza.getText(), NumeroVeicoli.getText());
						JOptionPane.showMessageDialog(null, "Deposito aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(panelCodice);
		body.add(textCodice);
		body.add(panelComune);
		body.add(textComune);
		body.add(panelRegione);
		body.add(textRegione);
		body.add(panelIndirizzo);
		body.add(textIndirizzo);
		body.add(panelCapienza);
		body.add(textCapienza);
		body.add(panelNumeroVeicoli);
		body.add(textNmeroVeicoli);
		body.add(buttonPanel);
		return body;
	}
	
}
