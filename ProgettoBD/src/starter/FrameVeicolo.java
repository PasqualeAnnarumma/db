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

public class FrameVeicolo extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField Targa;
	private JTextField Modello;
	private JTextField Colore;
	private JTextField ContenutoIn;
	private Database database;
	
	public FrameVeicolo(Database db) {
		super("Aggiungi Veicolo");
		Targa = new JTextField(20);
		Modello = new JTextField(20);
		Colore = new JTextField(20);
		ContenutoIn= new JTextField(20);
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
		JPanel textTarga = new JPanel();
		textTarga.add(Targa);
		JPanel textModello = new JPanel();
		textModello.add(Modello);
		JPanel textColore = new JPanel();
		textColore.add(Colore);
		
		JPanel labelTarga = new JPanel();
		labelTarga.add(new JLabel("Targa: "));
		JPanel labelModello = new JPanel();
		labelModello.add(new JLabel("Modello: "));
		JPanel labelColore = new JPanel();
		labelColore.add(new JLabel("Colore: "));
		JPanel panelInserisci = new JPanel();
		JButton button = new JButton("Inserisci");
		JPanel panelContenuto = new JPanel();
		panelContenuto.add(new JLabel("Contenuto in: "));
		JPanel textContenuto = new JPanel();
		textContenuto.add(ContenutoIn);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Targa.getText().equals("") || Modello.getText().equals("") || Colore.getText().equals("") || ContenutoIn.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("VEICOLO", "Targa", "Modello", "Colore", "ContenutoIn", Targa.getText(), Modello.getText(), Colore.getText(), ContenutoIn.getText());
						JOptionPane.showMessageDialog(null, "Veicolo aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		panelInserisci.add(button);
		
		body.add(labelTarga);
		body.add(textTarga);
		body.add(labelModello);
		body.add(textModello);
		body.add(labelColore);
		body.add(textColore);
		body.add(panelContenuto);
		body.add(textContenuto);
		body.add(panelInserisci);
		return body;
	}
	
}
