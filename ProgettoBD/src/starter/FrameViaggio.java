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

public class FrameViaggio extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField InizioNoleggio;
	private JTextField FineNoleggio;
	private JTextField Km;
	private JTextField Targa;
	private Database database;
	
	public FrameViaggio(Database db) {
		super("Aggiungi Viaggio");
		InizioNoleggio = new JTextField(20);
		FineNoleggio = new JTextField(20);
		Targa = new JTextField(20);
		Km = new JTextField(20);
		database = db;
		setLocation(500, 100);
		setSize(350, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(5, 2));
		JPanel textInizio = new JPanel();
		textInizio.add(InizioNoleggio);
		JPanel textFine = new JPanel();
		textFine.add(FineNoleggio);
		JPanel textKm = new JPanel();
		textKm.add(Km);
		
		JPanel labelInizio = new JPanel();
		labelInizio.add(new JLabel("Inizio noleggio: "));
		JPanel labelFine = new JPanel();
		labelFine.add(new JLabel("Fine noleggio: "));
		JPanel labelKm = new JPanel();
		labelKm.add(new JLabel("Km percorsi: "));
		JPanel panelInserisci = new JPanel();
		JButton button = new JButton("Inserisci");
		JPanel labelTarga = new JPanel();
		labelTarga.add(new JLabel("Targa"));
		JPanel textTarga = new JPanel();
		textTarga.add(Targa);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (InizioNoleggio.getText().equals("") || FineNoleggio.getText().equals("") || Km.getText().equals("") || Targa.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("VIAGGIO", "InizioNoleggio", "FineNoleggio", "KMpercorsi", "Targa", InizioNoleggio.getText(), FineNoleggio.getText(), Km.getText(), Targa.getText());
						JOptionPane.showMessageDialog(null, "Viaggio aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		panelInserisci.add(button);
		
		body.add(labelInizio);
		body.add(textInizio);
		body.add(labelFine);
		body.add(textFine);
		body.add(labelKm);
		body.add(textKm);
		body.add(labelTarga);
		body.add(textTarga);
		body.add(panelInserisci);
		return body;
	}
	
}
