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

public class FrameDitta extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public Database database;
	private JTextField P_IVA;
	private JTextField Nome;
	private JTextField Indirizzo;
	private JTextField Telefono;
	
	public FrameDitta(Database db) {
		super("Aggiungi ditta di manutenzione");
		database = db;
		P_IVA = new JTextField(20);
		Nome = new JTextField(20);
		Indirizzo = new JTextField(20);
		Telefono = new JTextField(20);
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
		JPanel textPIVa = new JPanel();
		textPIVa.add(P_IVA);
		JPanel textNome = new JPanel();
		textNome.add(Nome);
		JPanel texIndirizzo = new JPanel();
		texIndirizzo.add(Indirizzo); 
		JPanel textTelefono = new JPanel();
		textTelefono.add(Telefono);
		
		JPanel panelPIVA = new JPanel();
		panelPIVA.add(new JLabel("P IVA: "));
		JPanel panelNome = new JPanel();
		panelNome.add(new JLabel("Nome: "));
		JPanel panelIndirizzo = new JPanel();
		panelIndirizzo.add(new JLabel("Indirizzo: "));
		JPanel panelTelefono = new JPanel();
		panelTelefono.add(new JLabel("Telefono : "));
		
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (P_IVA.getText().equals("") || Nome.getText().equals("") || Indirizzo.getText().equals("") || Telefono.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("DITTA_MANUTENZIONE", "P_IVA", "Nome", "Indirizzo", "Telefono", P_IVA.getText(), Nome.getText(), Indirizzo.getText(), Telefono.getText());
						JOptionPane.showMessageDialog(null, "Ditta aggiunta con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(panelPIVA);
		body.add(textPIVa);
		body.add(panelNome);
		body.add(textNome);
		body.add(panelIndirizzo);
		body.add(texIndirizzo);
		body.add(panelTelefono);
		body.add(textTelefono);
		body.add(buttonPanel);
		return body;
	}
}
