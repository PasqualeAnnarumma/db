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
	private Database database;
	
	public FrameViaggio(Database db) {
		super("Aggiungi Viaggio");
		InizioNoleggio = new JTextField(20);
		FineNoleggio = new JTextField(20);
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
		body.setLayout(new GridLayout(3, 2));
		JPanel p = new JPanel();
		p.add(InizioNoleggio);
		JPanel p2 = new JPanel();
		p2.add(FineNoleggio);
		JPanel p3 = new JPanel();
		p2.add(Km);
		JPanel p4 = new JPanel();
		p3.add(new JLabel("Inizio noleggio: "));
		JPanel p5 = new JPanel();
		p4.add(new JLabel("Fine noleggio: "));
		JPanel p6 = new JPanel();
		p4.add(new JLabel("Km percorsi: "));
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (InizioNoleggio.getText().equals("") || FineNoleggio.getText().equals("") || Km.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("MOTOCICLO", "InizioNoleggio", "FineNoleggio", "Km", InizioNoleggio.getText(), FineNoleggio.getText(), Km.getText() );
						JOptionPane.showMessageDialog(null, "Relazione aggiunta con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(p4);
		body.add(p);
		body.add(p5);
		body.add(p2);
		body.add(p6);
		body.add(p3);
		body.add(buttonPanel);
		return body;
	}
	
}
