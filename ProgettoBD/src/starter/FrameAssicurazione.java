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

public class FrameAssicurazione extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Database database;
	private JTextField NumeroPratica;
	private JTextField P_Iva;
	private JTextField Nome;
	private JTextField VeicoloAssicurato;
	private JTextField scadenza;
	private JTextField premio;
	
	public FrameAssicurazione(Database db) {
		super("Aggiungi assicurazione");
		database = db;
		NumeroPratica = new JTextField(20);
		P_Iva = new JTextField(20);
		Nome = new JTextField(20);
		VeicoloAssicurato = new JTextField(20);
		scadenza = new JTextField(20);
		premio = new JTextField(20);
		setLocation(500, 100);
		setSize(350, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel body = createBody();
		add(body);
	}
	
	public JPanel createBody() {
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(9, 2));
		JPanel textPratica = new JPanel();
		textPratica.add(NumeroPratica);
		JPanel textPIva = new JPanel();
		textPIva.add(P_Iva);
		JPanel panelPratica = new JPanel();
		panelPratica.add(new JLabel("Numero Pratica: "));
		JPanel panelIva = new JPanel();
		panelIva.add(new JLabel("P iva: "));
		JPanel textNome = new JPanel();
		textNome.add(Nome);
		JPanel tectVeicolo = new JPanel();
		tectVeicolo.add(VeicoloAssicurato);
		JPanel textScadenza = new JPanel();
		textScadenza.add(scadenza);
		JPanel textPremio = new JPanel();
		textPremio.add(premio);
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		JPanel panelNome = new JPanel();
		panelNome.add(new JLabel("Nome: "));
		JPanel panelVeicolo = new JPanel();
		panelVeicolo.add(new JLabel("Veicolo assicurato: "));
		JPanel panelPremio = new JPanel();
		panelPremio.add(new JLabel("Premio: "));
		JPanel panelScadenza = new JPanel();
		panelScadenza.add(new JLabel("Scadenza: "));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NumeroPratica.getText().equals("") || P_Iva.getText().equals("") || Nome.getText().equals("") || VeicoloAssicurato.getText().equals("") || scadenza.getText().equals("")
													   || premio.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("ASSICURAZIONE", "NumeroPratica", "P_Iva", "Nome", "VeicoloAssicurato", "Scadenza", "Premio", NumeroPratica.getText(), P_Iva.getText(), Nome.getText(), VeicoloAssicurato.getText(), scadenza.getText(), premio.getText());
						JOptionPane.showMessageDialog(null, "Assicurazione aggiunta con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(panelPratica);
		body.add(textPratica);
		body.add(panelIva);
		body.add(textPIva);
		body.add(panelNome);
		body.add(textNome);
		body.add(panelVeicolo);
		body.add(tectVeicolo);
		body.add(panelScadenza);
		body.add(textScadenza);
		body.add(panelPremio);
		body.add(textPremio);
		body.add(buttonPanel);
		return body;
	}
	
}
