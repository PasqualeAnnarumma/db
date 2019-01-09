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

public class FrameRecapito extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField NumeroTelefonico;
	private JTextField NumeroPratica;
	private Database database;
	
	public FrameRecapito(Database db) {
		super("Aggiungi Recapito");
		NumeroTelefonico = new JTextField(20);
		NumeroPratica = new JTextField(20);
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
		p.add(NumeroTelefonico);
		JPanel p2 = new JPanel();
		p2.add(new JLabel("Numero: "));
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		JPanel panelPratica = new JPanel();
		panelPratica.add(new JLabel("Numero pratica: "));
		JPanel textPratica = new JPanel();
		textPratica.add(NumeroPratica);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NumeroTelefonico.getText().equals("") || NumeroPratica.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("RECAPITO", "NumeroTelefonico", "NumeroPratica", NumeroTelefonico.getText(), NumeroPratica.getText());
						JOptionPane.showMessageDialog(null, "Recapito aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(p2);
		body.add(p);
		body.add(panelPratica);
		body.add(textPratica);
		body.add(buttonPanel);
		return body;
	}
	
}
