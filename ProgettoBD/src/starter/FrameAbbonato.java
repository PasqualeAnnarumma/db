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

public class FrameAbbonato extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField NTessera;
	private JTextField CF;
	private Database database;
	
	public FrameAbbonato(Database db) {
		super("Aggiungi abbonato");
		NTessera = new JTextField(20);
		CF = new JTextField(20);
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
		p.add(NTessera);
		JPanel p2 = new JPanel();
		p2.add(CF);
		JPanel p3 = new JPanel();
		p3.add(new JLabel("Numero tessera: "));
		JPanel p4 = new JPanel();
		p4.add(new JLabel("Codice fiscale: "));
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NTessera.getText().equals("") || CF.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("ABBONATO", "N_Tessera", "CF", NTessera.getText(), CF.getText());
						JOptionPane.showMessageDialog(null, "Abbonato aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(p3);
		body.add(p);
		body.add(p4);
		body.add(p2);
		body.add(buttonPanel);
		return body;
	}
	
}
