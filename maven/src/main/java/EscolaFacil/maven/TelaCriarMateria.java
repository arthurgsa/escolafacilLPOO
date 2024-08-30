package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DAO;
import model.Cliente;
import model.Materia;

public class TelaCriarMateria extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldDescricao;
    private JTextField textFieldChaveEscola;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cliente cliente = new Cliente();
                    cliente.setChave("12345");
                    TelaCriarMateria frame = new TelaCriarMateria(cliente);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaCriarMateria(Cliente cliente) {
        setTitle("Cadastro de Matéria");
        getContentPane().setBackground(new Color(48, 105, 41));
        setBounds(100, 100, 588, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(40, 30, 488, 300);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Adicionar Matéria");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(180, 20, 150, 30);
        panel.add(lblTitulo);
        
        JLabel lblChaveEscola = new JLabel("Chave da Escola:");
        lblChaveEscola.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblChaveEscola.setBounds(30, 177, 120, 20);
        panel.add(lblChaveEscola);
        
        textFieldChaveEscola = new JTextField();
        textFieldChaveEscola.setBounds(140, 178, 300, 20);
        textFieldChaveEscola.setEditable(false);
        panel.add(textFieldChaveEscola);
        textFieldChaveEscola.setColumns(10);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNome.setBounds(30, 100, 100, 20);
        panel.add(lblNome);
        
        textFieldNome = new JTextField();
        textFieldNome.setBounds(140, 100, 300, 20);
        panel.add(textFieldNome);
        textFieldNome.setColumns(10);
        
        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescricao.setBounds(30, 140, 100, 20);
        panel.add(lblDescricao);
        
        textFieldDescricao = new JTextField();
        textFieldDescricao.setBounds(140, 140, 300, 20);
        panel.add(textFieldDescricao);
        textFieldDescricao.setColumns(10);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalvar.setBackground(new Color(106, 181, 111));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setBounds(180, 238, 120, 30);
        panel.add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String descricao = textFieldDescricao.getText();
                String chaveEscola = textFieldChaveEscola.getText();

                if (!nome.isEmpty() && !descricao.isEmpty() && !chaveEscola.isEmpty()) {
                    Materia novaMateria = new Materia(nome, descricao);

                    try {
                        DAO dao = new DAO();
                        dao.cadastrarMateria(novaMateria, chaveEscola);
                        System.out.println("Matéria cadastrada com sucesso!");
                        // Limpar campos após salvar
                        textFieldNome.setText("");
                        textFieldDescricao.setText("");
                    } catch (Exception ex) {
                        System.out.println("Erro ao cadastrar matéria: " + ex.getMessage());
                    }
                } else {
                    System.out.println("Por favor, preencha todos os campos.");
                }
            }
        });



        
        if (cliente != null) {
            preencherCampoChave(cliente.getChave());
        }
    }

    public void preencherCampoChave(String chave) {
        this.textFieldChaveEscola.setText(chave);
    }
}
