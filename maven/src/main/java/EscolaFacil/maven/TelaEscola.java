package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
                    TelaEscola frame = new TelaEscola();
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
    public TelaEscola() {
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

        // Botão "Cadastrar Turma/Professor"
        JButton btnCadastrarTurmaProfessor = new JButton("Cadastrar Turma/Professor");
        btnCadastrarTurmaProfessor.setBounds(50, 50, 250, 50);
        panel.add(btnCadastrarTurmaProfessor);
        
        // Botão "Organizar Turmas"
        JButton btnOrganizarTurmas = new JButton("Organizar Turmas");
        btnOrganizarTurmas.setBounds(50, 120, 250, 50);
        panel.add(btnOrganizarTurmas);

        // Adiciona ActionListener ao botão "Cadastrar Turma/Professor"
        btnCadastrarTurmaProfessor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para abrir a tela de cadastro de turma/professor
                TelaCadastro telaCadastro = new TelaCadastro(null,null,false);
                telaCadastro.setVisible(true);
            }
        });

        // Adiciona ActionListener ao botão "Organizar Turmas"
        btnOrganizarTurmas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para abrir a tela de organização de turmas
            	dispose();
                TelaOrganizarTurmas telaOrganizar = new TelaOrganizarTurmas();
                telaOrganizar.setVisible(true);
                
            }
        });
    }
}
