package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Font;

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
        
        Object[][] data = new Object[49][9]; // 48 números + 1 linha de cabeçalho
        
        // Preenche a primeira linha com os dias da semana e as demais com números
        data[0] = columnNames; // Primeira linha com os nomes das colunas

        // Preenche a primeira e a segunda coluna com números de 1 a 48
        for (int i = 1; i <= 24; i++) {
        	String zeroMenorQue10 = "";
        	String zeroMenorQue102 = "";
        	if (i < 10) {
        		zeroMenorQue10 = "0";
        		if (i < 9) {
        			zeroMenorQue102 = "0";
        		}
        	}
            data[i][0] = zeroMenorQue10+i+":00 às " + zeroMenorQue102 + (i+1)+":00" ; // Número
            
            
        }
        
        // Cria o modelo da tabela com os dados e colunas
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        
        // Ajusta a largura das colunas para melhor visualização
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
        
        // Configura o layout da tabela
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBounds(50, 100, 1000, 400);
        panel.add(table);
    }
}
