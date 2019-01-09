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

public class FrameAutovettura extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Database database;
	private JTextField Targa;
	private JTextField Carburante;
	private JTextField KM;
	private JTextField NumeroPosti;
	
	public FrameAutovettura(Database db) {
		super("Aggiungi autovettura");
		database = db;
		Targa = new JTextField(20);
		Carburante  = new JTextField(20);;
		KM  = new JTextField(20);
		NumeroPosti  = new JTextField(20);
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
		JPanel textCarburante = new JPanel();
		textCarburante.add(Carburante);
		JPanel textKM = new JPanel();
		textKM.add(KM); 
		JPanel textNumeroPosti = new JPanel();
		textNumeroPosti.add(NumeroPosti);
		
		JPanel panelKM = new JPanel();
		panelKM.add(new JLabel("KM: "));
		JPanel panelTarga = new JPanel();
		panelTarga.add(new JLabel("Targa: "));
		JPanel panelCarburante = new JPanel();
		panelCarburante.add(new JLabel("Veicolo carburante: "));
		JPanel panelNumeroPosti = new JPanel();
		panelNumeroPosti.add(new JLabel("NumeroPosti : "));
		
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Targa.getText().equals("") || KM.getText().equals("") || Carburante.getText().equals("") || NumeroPosti.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("AUTOVETTURA", "Targa", "Carburante", "KM", "NumeroPosti", Targa.getText(), Carburante.getText(), KM.getText(), NumeroPosti.getText());
						JOptionPane.showMessageDialog(null, "Autovettura aggiunta con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(panelTarga);
		body.add(textTarga);
		body.add(panelCarburante);
		body.add(textCarburante);
		body.add(panelKM);
		body.add(textKM);
		body.add(panelNumeroPosti);
		body.add(textNumeroPosti);
		body.add(buttonPanel);
		return body;
	}
	
}
