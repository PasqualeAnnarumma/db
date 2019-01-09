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
	private JTextField Cliente;
	private Database database;
	
	public FramePagamento(Database db) {
		super("Aggiungi Pagamento");
		Importo = new JTextField(20);
		Data = new JTextField(20);
		MetodoPagamento = new JTextField(20);
		database = db;
		Cliente = new JTextField(20);
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
		JPanel tetxImporto = new JPanel();
		tetxImporto.add(Importo);
		JPanel textData = new JPanel();
		textData.add(Data);
		JPanel textPagamento = new JPanel();
		textPagamento.add(MetodoPagamento);
		
		JPanel labelImporto = new JPanel();
		labelImporto.add(new JLabel("Importo: "));
		JPanel labelData = new JPanel();
		labelData.add(new JLabel("Data: "));
		JPanel labelPagamento = new JPanel();
		labelPagamento.add(new JLabel("Metodo di pagamento: "));
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Inserisci");
		JPanel pb = new JPanel();
		pb.add(Cliente);
		JPanel label = new JPanel();
		label.add(new JLabel("Cliente: "));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Importo.getText().equals("") || Data.getText().equals("") || MetodoPagamento.getText().equals("") || Cliente.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire tutti i campi", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						database.aggiungi("PAGAMENTO", "Data_P", "Importo", "MetodoDiPagamento", "Cliente", Data.getText(), Importo.getText(), MetodoPagamento.getText(), Cliente.getText());
						JOptionPane.showMessageDialog(null, "Pagamento aggiunto con successo", "SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		buttonPanel.add(button);
		
		body.add(labelData);
		body.add(textData);
		body.add(labelImporto);
		body.add(tetxImporto);
		body.add(labelPagamento);
		body.add(textPagamento);
		body.add(label);
		body.add(pb);
		body.add(buttonPanel);
		return body;
	}
	
}
