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

public class FrameCliente extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Database database;
	private JTextField CF;
	private JTextField Nome;
	private JTextField Cognome;
	private JTextField LuogoNascita;
	private JTextField Telefono;
	private JTextField DataNascita;
	private JTextField N_CartadiCredito;
	
	public FrameCliente(Database db) {
		super("Aggiungi cliente");
		database = db;
		CF = new JTextField(20);
		Nome = new JTextField(20);
		Cognome = new JTextField(20);
		LuogoNascita = new JTextField(20);
		Telefono = new JTextField(20);
		DataNascita = new JTextField(20);
		N_CartadiCredito = new JTextField(20);
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
		JPanel textLuogoNascita = new JPanel();
		textLuogoNascita.add(LuogoNascita);
		JPanel textTelefono = new JPanel();
		textTelefono.add(Telefono);
		JPanel textDataNascita = new JPanel();
		textDataNascita.add(DataNascita);
		JPanel textCarta = new JPanel();
		textCarta.add(N_CartadiCredito);
		
		JPanel panelCF = new JPanel();
		panelCF.add(new JLabel("CF: "));
		JPanel panelNome = new JPanel();
		panelNome.add(new JLabel("Nome: "));
		JPanel panelCognome = new JPanel();
		panelCognome.add(new JLabel("Cognome: "));
		JPanel panelLuogoNascita = new JPanel();
		panelLuogoNascita.add(new JLabel("Luogo di nascita : "));
		JPanel panelTelefono = new JPanel();
		panelTelefono.add(new JLabel("Telefono : "));
		JPanel panelDataNascita = new JPanel();
		panelDataNascita.add(new JLabel("Data di nascita : "));
		JPanel panelCarta = new JPanel();
		panelCarta.add(new JLabel("N° Carta di credito: "));
		
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CF.getText().equals("") || Nome.getText().equals("") || Cognome.getText().equals("") || LuogoNascita.getText().equals("") || Telefono.getText().equals("") || DataNascita.getText().equals("") || N_CartadiCredito.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("CLIENTE", "CF", "Nome", "Cognome", "LuogoNascita", "Telefono", "DataNascita", "N_CartadiCredito", CF.getText(), Nome.getText(), Cognome.getText(), LuogoNascita.getText(), Telefono.getText(), DataNascita.getText(), N_CartadiCredito.getText());
						JOptionPane.showMessageDialog(null, "Cliente aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
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
		body.add(panelLuogoNascita);
		body.add(textLuogoNascita);
		body.add(panelTelefono);
		body.add(textTelefono);
		body.add(panelDataNascita);
		body.add(textDataNascita);
		body.add(panelCarta);
		body.add(textCarta);
		body.add(buttonPanel);
		return body;
	}
	
}
