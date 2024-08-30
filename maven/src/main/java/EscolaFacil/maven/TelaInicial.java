package EscolaFacil.maven;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.Criptografia;
import dao.DAO;
import model.Cliente;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;

public class TelaInicial {

	private JFrame frame;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					
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
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLocation(-21, 0);
		
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
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(495, 246, 64, 14);
		panel.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(427, 271, 201, 23);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(499, 318, 56, 14);
		panel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(427, 342, 201, 20);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obter a senha inserida e criptografá-la
		    	String escola = "0";
		        String senhaInserida = new String(passwordField.getPassword());
		        Criptografia criptografia = new Criptografia(senhaInserida, Criptografia.MD5);
		        String senhaCriptografada = criptografia.criptografar();
		       

		        // Verificar se os campos não estão vazios
		        if (!textFieldUsuario.getText().isEmpty() && passwordField.getPassword().length > 0) {
		            try {
		                // Recuperar o cliente pelo nome de usuário
		                DAO dao = new DAO();
		                Cliente cliente = dao.consultarCliente(dao.consultarIdPorNomeUsuario(textFieldUsuario.getText().toLowerCase())); // Método para buscar cliente por nome
		                
		                // Comparar a senha criptografada inserida com a senha do cliente
		                if (cliente != null && senhaCriptografada.equals(cliente.getSenha())) {
		                	if ("0".equals(cliente.getEscola())) {
		                        TelaVisualizacaoUsuario telaVisualizacao = new TelaVisualizacaoUsuario(cliente);
		                        telaVisualizacao.setVisible(true);
		                    } else if ("1".equals(cliente.getEscola())) {
		                        TelaEscola telaEscola = new TelaEscola(cliente);
		                        telaEscola.setVisible(true);
		                    }
		                    frame.dispose();
		                } else {
		                    JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "ERRO", JOptionPane.WARNING_MESSAGE);
		                }
		                
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "ERRO", JOptionPane.WARNING_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(btnNewButton, "Preencha todos os campos", "AVISO", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBackground(new Color(48, 105, 41));
		btnNewButton.setBounds(427, 388, 201, 43);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		JButton btnEntrarComoUsuario = new JButton("Entrar como ADMIN");
		btnEntrarComoUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obter a senha inserida e criptografá-la
		        String senhaInserida = new String(passwordField.getPassword());
		        Criptografia criptografia = new Criptografia(senhaInserida, Criptografia.MD5);
		        String senhaCriptografada = criptografia.criptografar();
		        String nomeUsuario = textFieldUsuario.getText().toUpperCase();

		        // Verificar se os campos não estão vazios
		        if (!nomeUsuario.isEmpty() && !senhaInserida.isEmpty()) {
		            try {
		                DAO dao = new DAO();
		                // Consultar o usuário
		                Usuario usuario = dao.consultarUsuario(nomeUsuario, senhaCriptografada);
		                
		                if (usuario != null) {
		                    // Se um usuário válido for retornado, abrir a tela de administração
		                    ListaClientes telaAdmin = new ListaClientes();
		                    telaAdmin.setVisible(true);
		                    frame.dispose();
		                } else {
		                    JOptionPane.showMessageDialog(btnEntrarComoUsuario, "Verifique as informações", "ERRO", JOptionPane.WARNING_MESSAGE);
		                }
		                
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(btnEntrarComoUsuario, "Erro ao verificar as informações", "ERRO", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(btnEntrarComoUsuario, "Preencha todos os campos", "AVISO", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnEntrarComoUsuario.setForeground(new Color(255, 255, 255));
		btnEntrarComoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEntrarComoUsuario.setBackground(new Color(128, 128, 128));
		btnEntrarComoUsuario.setBounds(449, 469, 157, 23); // Ajuste a posição conforme necessário
		btnEntrarComoUsuario.setBorder(null);
		panel.add(btnEntrarComoUsuario);

		
		
		
		JLabel lblS = new JLabel("");
		lblS.setBounds(389, 29, 276, 118);
		panel.add(lblS);
		lblS.setIcon(new ImageIcon(TelaInicial.class.getResource("/images/escolafacil.jpg")));
		lblS.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 189, 1100, 14);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
//	   	JButton btnAlunoPrimeiro = new JButton("Primeiro acesso");
//		btnAlunoPrimeiro.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				TelaCadastro telaCadastro = new TelaCadastro(null,null, false);
//				telaCadastro.setLocationRelativeTo(telaCadastro);
//				telaCadastro.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
//				telaCadastro.setVisible(true);
//			}
//		}); 
//		btnAlunoPrimeiro.setForeground(Color.WHITE);
//		btnAlunoPrimeiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		btnAlunoPrimeiro.setBackground(new Color(106, 181, 111));
//		btnAlunoPrimeiro.setBounds(453, 506, 150, 23);
//		panel.add(btnAlunoPrimeiro);
		
		// Adicionar ActionListener para o Enter
        ActionListener enterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNewButton.doClick(); // Simula o clique no botão "Entrar"
            }
        };
        
		textFieldUsuario.addActionListener(enterListener);
        passwordField.addActionListener(enterListener);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
		// TODO Auto-generated method stub
		
	}
}
