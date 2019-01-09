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

public class FrameDipendente extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Database database;
	private JTextField CF;
	private JTextField Nome;
	private JTextField Cognome;
	private JTextField Via;
	private JTextField Numero;
	private JTextField DataNascita;
	private JTextField Gestisce;

	public FrameDipendente(Database db) {
		super("Aggiungi dipendente");
		database = db;
		CF = new JTextField(20);
		Nome = new JTextField(20);
		Cognome = new JTextField(20);
		Via = new JTextField(20);
		Numero = new JTextField(20);
		DataNascita = new JTextField(20);
		Gestisce = new JTextField(20);
		setLocation(500, 100);
		setSize(350, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(8, 2));
		JPanel textCF = new JPanel();
		textCF.add(CF);
		JPanel textNome = new JPanel();
		textNome.add(Nome);
		JPanel textCognome = new JPanel();
		textCognome.add(Cognome); 
		JPanel textVia = new JPanel();
		textVia.add(Via);
		JPanel textNumero = new JPanel();
		textNumero.add(Numero);
		JPanel textDataNascita = new JPanel();
		textDataNascita.add(DataNascita);
		JPanel textGestisce = new JPanel();
		textGestisce.add(Gestisce);

		JPanel panelCF = new JPanel();
		panelCF.add(new JLabel("CF: "));
		JPanel panelNome = new JPanel();
		panelNome.add(new JLabel("Nome: "));
		JPanel panelCognome = new JPanel();
		panelCognome.add(new JLabel("Cognome: "));
		JPanel panelVia = new JPanel();
		panelVia.add(new JLabel("Via : "));
		JPanel panelNumero = new JPanel();
		panelNumero.add(new JLabel("Numero : "));
		JPanel panelDataNascita = new JPanel();
		panelDataNascita.add(new JLabel("Data di nascita : "));
		JPanel panelGestisce = new JPanel();
		panelGestisce.add(new JLabel("Gestisce : "));
		
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CF.getText().equals("") || Nome.getText().equals("") || Cognome.getText().equals("") || Via.getText().equals("") || Gestisce.getText().equals("") || Numero.getText().equals("") || DataNascita.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("DIPENDENTE", "CF", "Nome", "Cognome", "Via", "Numero", "DataNascita", "Gestisce", CF.getText(), Nome.getText(), Cognome.getText(), Via.getText(), Numero.getText(), DataNascita.getText(), Gestisce.getText());
						JOptionPane.showMessageDialog(null, "Dipendente aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(panelCF);
		body.add(textCF);
		body.add(panelNome);
		body.add(textNome);
		body.add(panelCognome);
		body.add(textCognome);
		body.add(panelVia);
		body.add(textVia);
		body.add(panelNumero);
		body.add(textNumero);
		body.add(panelDataNascita);
		body.add(textDataNascita);
		body.add(panelGestisce);
		body.add(textGestisce);
		body.add(buttonPanel);
		return body;
	}
	
}
