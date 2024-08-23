package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
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
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(319, 67, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(319, 83, 434, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPrimeiroAcessoAluno = new JLabel("PRIMEIRO ACESSO");
		lblPrimeiroAcessoAluno.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrimeiroAcessoAluno.setBounds(443, 22, 195, 35);
		panel.add(lblPrimeiroAcessoAluno);
		
		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ");
		lblCpfcnpj.setBounds(319, 114, 54, 14);
		panel.add(lblCpfcnpj);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(319, 130, 434, 20);
		panel.add(textField_1);
		
		JLabel lblEmail = new JLabel("Telefone");
		lblEmail.setBounds(319, 170, 54, 14);
		panel.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(319, 186, 434, 20);
		panel.add(textField_2);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(319, 226, 54, 14);
		panel.add(lblEmail_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(319, 242, 434, 20);
		panel.add(textField_3);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(319, 320, 54, 14);
		panel.add(lblEndereo);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(171, 173, 179)));
		textArea.setBounds(319, 336, 434, 52);
		panel.add(textArea);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBackground(new Color(106, 181, 111));
		btnCadastrar.setBounds(450, 477, 173, 23);
		panel.add(btnCadastrar);
		
		JLabel lblEmail_1_1 = new JLabel("Aluno");
		lblEmail_1_1.setBounds(344, 420, 54, 14);
		panel.add(lblEmail_1_1);
		
		JLabel lblEmail_1_1_1 = new JLabel("Professor");
		lblEmail_1_1_1.setBounds(344, 441, 54, 14);
		panel.add(lblEmail_1_1_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setBounds(319, 420, 21, 14);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setBounds(319, 441, 21, 14);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblEmail_1_1_2 = new JLabel("Você é");
		lblEmail_1_1_2.setBounds(319, 399, 54, 14);
		panel.add(lblEmail_1_1_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(319, 289, 434, 20);
		panel.add(textField_4);
		
		JLabel lblEmail_1_2 = new JLabel("Chave de Vínculo Escolar");
		lblEmail_1_2.setBounds(319, 273, 181, 14);
		panel.add(lblEmail_1_2);
	}
}
