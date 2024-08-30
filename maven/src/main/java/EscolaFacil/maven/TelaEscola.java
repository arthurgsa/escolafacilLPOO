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
import javax.swing.border.EmptyBorder;

import model.Cliente;

public class TelaEscola extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaEscola frame = new TelaEscola(null);
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
    public TelaEscola(Cliente cliente) {
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

        // Adiciona o label de título com o nome do cliente
        JLabel lblTitulo = new JLabel("Olá, " + (cliente != null ? cliente.getNome() : "Cliente"));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitulo.setBounds(85, 10, 913, 30);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(lblTitulo);
        
      

        // Botão "Criar Matéria"
        JButton btnCriarMateria = new JButton("Criar Matéria");
        btnCriarMateria.setBounds((panel.getWidth() - 250) / 2, 190, 250, 50);
        panel.add(btnCriarMateria);

        // Adiciona o botão "Voltar"
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela atual e abre a tela de login
                dispose();
                TelaInicial telaLogin = new TelaInicial();
                telaLogin.setVisible(true);
            }
        });
        btnVoltar.setBounds(10, 12, 65, 31); // Ajuste a posição conforme necessário
        panel.add(btnVoltar);

        // Botão "Cadastrar Turma/Professor"
        JButton btnCadastrarTurmaProfessor = new JButton("Cadastrar Turma/Professor");
        btnCadastrarTurmaProfessor.setBounds((panel.getWidth() - 250) / 2, 50, 250, 50);
        panel.add(btnCadastrarTurmaProfessor);
        
        // Adiciona ActionListener ao botão "Cadastrar Turma/Professor"
        btnCadastrarTurmaProfessor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para abrir a tela de cadastro de turma/professor
                TelaCadastro telaCadastro = new TelaCadastro(null, null, false, cliente);
                telaCadastro.setVisible(true);
            }
        });

     // Botão "Organizar Turmas"
        JButton btnOrganizarTurmas = new JButton("Organizar Turmas");
        btnOrganizarTurmas.setBounds((panel.getWidth() - 250) / 2, 120, 250, 50);
        panel.add(btnOrganizarTurmas);
        
        // Adiciona ActionListener ao botão "Organizar Turmas"
        btnOrganizarTurmas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para abrir a tela de organização de turmas
                dispose();
                TelaOrganizarTurmas telaOrganizar = new TelaOrganizarTurmas(cliente);
                telaOrganizar.setVisible(true);
            }
        });

        // Adiciona ActionListener ao botão "Criar Matéria"
        btnCriarMateria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para abrir a tela de criação de matéria
                TelaCriarMateria telaCriarMateria = new TelaCriarMateria(cliente);
                telaCriarMateria.setVisible(true);
            }
        });

        // Adiciona ActionListener ao botão "Voltar"
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela atual e abre a tela de login
                dispose();
                TelaInicial telaLogin = new TelaInicial();
                telaLogin.setVisible(true);
            }
        });
    }
}
