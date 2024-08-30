package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Cliente;
import dao.DAO;

public class TelaOrganizarTurmas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DAO dao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cliente cliente = new Cliente(); 
                    cliente.setChave("chave_exemplo"); 
                    TelaOrganizarTurmas frame = new TelaOrganizarTurmas(cliente);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param cliente 
     */
    public TelaOrganizarTurmas(Cliente cliente) {
        dao = new DAO();
        getContentPane().setBackground(new Color(48, 105, 41));
        setBounds(100, 100, 1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(80, 70, 1100, 540);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Organize uma turma");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(423, 11, 186, 75);
        panel.add(lblNewLabel);

        List<Cliente> clientes = dao.retornarArrayDeClientes(cliente.getNome());
        DefaultTableModel model = criarModeloTabela(clientes, cliente.getChave());

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(true);
                setBackground(Color.WHITE);
            }
            
            @Override
            public void setValue(Object value) {
                super.setValue(value);
                setBackground(Color.WHITE);
            }
        });
        
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(30);
        table.setBounds(50, 100, 1000, 400);
        panel.add(table);

        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());

                if (row >= 0 && column >= 0) {
                    // Obtém o valor de 'horario' e 'diaDaSemana' baseando-se na linha e coluna clicadas
                    String horario = (String) table.getValueAt(row, 0); // Ajuste conforme o conteúdo da tabela
                    String diaDaSemana = table.getColumnName(column); // Ajuste conforme o nome da coluna
                    
                    // Verifica se os valores são válidos
                    if (horario != null && diaDaSemana != null) {
                        // Abre TelaTurma passando o cliente, horário e dia da semana
                        TelaTurma telaTurma = new TelaTurma(cliente, horario, diaDaSemana);
                        telaTurma.setVisible(true);
                    }
                }
            }
        });
    }

    private DefaultTableModel criarModeloTabela(List<Cliente> clientes, String chave) {
        String[] columnNames = {"Nome"};
        Object[][] data = clientes.stream()
                .filter(cliente -> cliente.getChave().equals(chave))
                .map(cliente -> new Object[]{cliente.getNome()})
                .toArray(Object[][]::new);
        
        return new DefaultTableModel(data, columnNames);
    }
}
