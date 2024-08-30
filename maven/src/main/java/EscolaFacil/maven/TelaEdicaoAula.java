package EscolaFacil.maven;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import dao.DAO;
import model.Cliente;

public class TelaEdicaoAula extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldHorario;
    private JTextField textFieldDia;
    private JComboBox<String> comboBoxMaterias;
    private DAO dao;
    private Cliente cliente;

    /**
     * Create the frame.
     * @param cliente 
     * @param horario 
     * @param diaDaSemana 
     */
    public TelaEdicaoAula(Cliente cliente, String horario, String diaDaSemana) {
        this.cliente = cliente;
        dao = new DAO();
        setTitle("Edição de Aula");
        setBounds(100, 100, 400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 10, 364, 242);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblHorario = new JLabel("Horário:");
        lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblHorario.setBounds(10, 30, 100, 20);
        panel.add(lblHorario);

        textFieldHorario = new JTextField(horario);
        textFieldHorario.setEditable(false);
        textFieldHorario.setBounds(120, 30, 200, 20);
        panel.add(textFieldHorario);
        
        JLabel lblDia = new JLabel("Dia da Semana:");
        lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDia.setBounds(10, 70, 100, 20);
        panel.add(lblDia);

        textFieldDia = new JTextField(diaDaSemana);
        textFieldDia.setEditable(false);
        textFieldDia.setBounds(120, 70, 200, 20);
        panel.add(textFieldDia);

        JLabel lblMateria = new JLabel("Matéria:");
        lblMateria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMateria.setBounds(10, 110, 100, 20);
        panel.add(lblMateria);

        // Carrega as matérias da tabela MATERIAS com a chave da escola do cliente
        List<String> materias = dao.buscarMaterias(cliente.getChave());
        comboBoxMaterias = new JComboBox<>(materias.toArray(new String[0]));
        comboBoxMaterias.setBounds(120, 110, 200, 20);
        panel.add(comboBoxMaterias);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 150, 100, 30);
        panel.add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String materiaSelecionada = (String) comboBoxMaterias.getSelectedItem();
                if (materiaSelecionada != null) {
                    // Armazena a aula utilizando o método do DAO
                    boolean sucesso = dao.armazenarAula(cliente.getChave(), textFieldHorario.getText(), textFieldDia.getText(), materiaSelecionada);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(TelaEdicaoAula.this, "Aula armazenada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Fecha a tela
                    } else {
                        JOptionPane.showMessageDialog(TelaEdicaoAula.this, "Falha ao armazenar a aula.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
