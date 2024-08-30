package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;

public class TelaVisualizacaoUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaVisualizacaoUsuario frame = new TelaVisualizacaoUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaVisualizacaoUsuario() {
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
        
        JLabel lblNewLabel = new JLabel("Bem vindo, fulano!");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(423, 11, 186, 75);
        panel.add(lblNewLabel);
        
        // Define os dados e colunas para a tabela
        String[] columnNames = {
            "HORÁRIO", "Segunda-feira", "Terça-feira", "Quarta-feira",
            "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"
        };
        
        Object[][] data = new Object[13][8]; // 24 horários + 1 linha de cabeçalho
        
        // Preenche a primeira linha com os dias da semana e as demais com horários
        data[0] = columnNames; // Primeira linha com os nomes das colunas

        // Preenche a primeira coluna com horários de 00:00 a 23:00
        for (int i = 6; i < 18; i++) {
            int hour = i - 1;
            data[i-5][0] = String.format("%02d:00", hour) + " às " + String.format("%02d:00", (hour+1) % 24); // Horário
        }
        
        // Cria o modelo da tabela com os dados e colunas
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        
        // Personaliza a renderização das células
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(true); // Torna o fundo da célula opaco
                setBackground(Color.WHITE); // Define a cor de fundo como branco
            }
            
            @Override
            public void setValue(Object value) {
                super.setValue(value);
                setBackground(Color.WHITE); // Define a cor de fundo como branco
            }
        });
        
        // Ajusta a largura das colunas para melhor visualização
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
        
        // Configura o layout da tabela
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(30); // Define a altura das linhas para 50 pixels
        table.setBounds(50, 100, 1000, 400);
        panel.add(table);

        // Remove a seleção de linha ao clicar em uma célula
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);

        // Adiciona o MouseListener para exibir a linha e a coluna da célula ao clicar
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                
                // Verifica se a célula é válida
                if (row > 0 && column > 0) {
                	System.out.print(row);
                	System.out.print(column);
                    String message = String.format("Linha: %d, Coluna: %d", row, column);
                    JOptionPane.showMessageDialog(table, message, "Informação da Célula", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
