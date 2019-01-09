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

public class FramePagamento extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField Importo;
	private JTextField Data;
	private JTextField MetodoPagamento;
	private Database database;
	
	public FramePagamento(Database db) {
		super("Aggiungi Pagamento");
		Importo = new JTextField(20);
		Data = new JTextField(20);
		MetodoPagamento = new JTextField(20);
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
		p.add(Importo);
		JPanel p2 = new JPanel();
		p2.add(Data);
		JPanel p3 = new JPanel();
		p2.add(MetodoPagamento);
		JPanel p4 = new JPanel();
		p3.add(new JLabel("Importo: "));
		JPanel p5 = new JPanel();
		p4.add(new JLabel("Data: "));
		JPanel p6 = new JPanel();
		p4.add(new JLabel("Metodo di pagamento: "));
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Importo.getText().equals("") || Data.getText().equals("") || MetodoPagamento.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("PAGAMENTO", "Importo", "Data", "Metodo di pagamento", Importo.getText(), Data.getText(), MetodoPagamento.getText() );
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
