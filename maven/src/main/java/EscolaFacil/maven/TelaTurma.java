package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Cliente;
import dao.DAO;

public class TelaTurma extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DAO dao;
    private Cliente cliente;
    private String horario;
    private String diaDaSemana;

    /**
     * Create the frame.
     * @param cliente 
     * @param horario
     * @param diaDaSemana
     */
    public TelaTurma(Cliente cliente, String horario, String diaDaSemana) {
        this.cliente = cliente;
        this.horario = horario;
        this.diaDaSemana = diaDaSemana;

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
        
        JLabel lblNewLabel = new JLabel("Organize a turma");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(423, 11, 186, 75);
        panel.add(lblNewLabel);

        DefaultTableModel model = criarModeloTabela();
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

                // Verifica se a célula é válida
                if (row >= 0 && column >= 0) {
                    // Obtém o valor de 'horario' e 'diaDaSemana' baseando-se na linha e coluna clicadas
                    String horarioClicado = (String) table.getValueAt(row, 0); // Ajuste conforme o conteúdo da tabela
                    String diaDaSemanaClicado = table.getColumnName(column); // Ajuste conforme o nome da coluna
                    
                    // Exibe uma mensagem com os detalhes da célula (opcional para depuração)
                    String message = String.format("Linha: %d, Coluna: %d\nHorário: %s\nDia da Semana: %s", row, column, horarioClicado, diaDaSemanaClicado);
                    JOptionPane.showMessageDialog(table, message, "Informação da Célula", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Abre TelaEdicaoAula com os dados da célula e o cliente
                    TelaEdicaoAula telaEdicaoAula = new TelaEdicaoAula(cliente, horarioClicado, diaDaSemanaClicado);
                    telaEdicaoAula.setVisible(true);
                }
            }
        });
    }

    private DefaultTableModel criarModeloTabela() {
        // Exemplo simples: criar um modelo com horários e dias da semana fictícios
        String[] columnNames = {"Horário", "Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        Object[][] data = {
            {"08:00 - 09:00", "Matemática", "História", "Geografia", "Biologia", "Física"},
            {"09:00 - 10:00", "Português", "Matemática", "História", "Física", "Química"},
            // Adicione mais linhas conforme necessário
        };
        
        return new DefaultTableModel(data, columnNames);
    }
}
