package EscolaFacil.maven;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.DAO;
import model.Cliente;
import model.ModeloTabela;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaClientes {

	private JFrame frame;
	private JTable table;
	private ArrayList<Cliente> clientes;
	private ListaClientes listaCliente;
	private JTextField textFieldBusca;
	private TableRowSorter<ModeloTabela> rowSorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaClientes window = new ListaClientes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaClientes() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.listaCliente = this;
		DAO dao = new DAO();
		try {
			clientes = dao.listarClientes();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		
		frame.getContentPane().setBackground(new Color(48, 105, 41));
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(80, 70, 1100, 540);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 70, 964, 440);
		panel.add(scrollPane);
		
		ModeloTabela modeloTabela = new ModeloTabela(clientes);
		
		table = new JTable();
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					try {
						Cliente clienteSelecionado = dao.consultarCliente(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
						TelaCadastro telaCadastro = new TelaCadastro(clienteSelecionado, listaCliente, true);
						telaCadastro.setLocationRelativeTo(telaCadastro);
						telaCadastro.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
						telaCadastro.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter); /////////////
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cadastrar Cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadastro telaCadastro = new TelaCadastro(null, listaCliente, true);
					telaCadastro.setLocationRelativeTo(telaCadastro);
					telaCadastro.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					telaCadastro.setVisible(true);
				} catch (Exception e2){
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(85, 11, 175, 48);
		panel.add(btnNewButton);
		
		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}

			
		});
		textFieldBusca.setBounds(270, 12, 776, 20);
		panel.add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}

	public void dispose() {
		frame.dispose();
		
	}

	public void setLocationRelativeTo(Component c) {
		frame.setLocationRelativeTo(c);
		
	}
	
	private void filtrar() {
		String busca = textFieldBusca.getText().trim();
		
		if(busca.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + busca));
		}
		
	}
}
