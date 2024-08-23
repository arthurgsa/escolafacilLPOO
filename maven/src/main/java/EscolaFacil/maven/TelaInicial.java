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
				String senhaCripto = new String(passwordField.getPassword());
				Criptografia criptografia = new Criptografia(senhaCripto, Criptografia.MD5);
				System.out.println(criptografia.criptografar());
				if(!textFieldUsuario.getText().isEmpty() && passwordField.getPassword().length > 0) {
					if(criptografia.criptografar().equals("E10ADC3949BA59ABBE56E057F20F883E")) {
					frame.dispose();
					ListaClientes telaPrincipal = new ListaClientes();
					telaPrincipal.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "AVISO", JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "AVISO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBackground(new Color(48,105,41));
		btnNewButton.setBounds(427, 388, 201, 43);
		panel.add(btnNewButton);
		
		
		
		JLabel lblS = new JLabel("");
		lblS.setBounds(389, 29, 276, 118);
		panel.add(lblS);
		lblS.setIcon(new ImageIcon(TelaInicial.class.getResource("/images/escolafacil.jpg")));
		lblS.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 189, 1100, 14);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAlunoPrimeiro = new JButton("Aluno - Primeiro acesso");
		btnAlunoPrimeiro.setForeground(Color.WHITE);
		btnAlunoPrimeiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAlunoPrimeiro.setBackground(new Color(106, 181, 111));
		btnAlunoPrimeiro.setBounds(441, 442, 173, 23);
		panel.add(btnAlunoPrimeiro);
		
		JButton btnProfPrimeiro = new JButton("Profº - Primeiro acesso");
		btnProfPrimeiro.setForeground(Color.WHITE);
		btnProfPrimeiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfPrimeiro.setBackground(new Color(106, 181, 111));
		btnProfPrimeiro.setBounds(441, 476, 173, 23);
		panel.add(btnProfPrimeiro);
	}
}
