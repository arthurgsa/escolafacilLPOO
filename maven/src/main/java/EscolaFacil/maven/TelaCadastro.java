package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dao.DAO;
import model.Cliente;
import controladores.Criptografia;

public class TelaCadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldCpf;
    private JTextField textFieldTelefone;
    private JTextField textFieldEmail;
    private JTextField textFieldChave;
    private JTextField textFieldSenha;
    private JRadioButton rdbtnNewRadioButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastro frame = new TelaCadastro(null, null, false, null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaCadastro(Cliente clienteSelecionado, ListaClientes listaCliente, boolean mostrarBotaoEscola, Cliente cliente2) {
        DAO dao = new DAO();
        
        getContentPane().setBackground(new Color(48, 105, 41));
        setBounds(100, 100, 588, 535);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Alterado para não fechar toda a aplicação
        setResizable(false);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(40, 30, 488, 426);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(26, 71, 46, 14);
        panel.add(lblNome);
        
        textFieldNome = new JTextField();
        textFieldNome.setBounds(26, 87, 434, 20);
        panel.add(textFieldNome);
        textFieldNome.setColumns(10);
        
        JLabel lblPrimeiroAcessoAluno = new JLabel("Cadastro");
        lblPrimeiroAcessoAluno.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblPrimeiroAcessoAluno.setBounds(190, 11, 128, 35);
        panel.add(lblPrimeiroAcessoAluno);
        
        JLabel lblCpf = new JLabel("CPF/CNPJ");
        lblCpf.setBounds(26, 118, 54, 14);
        panel.add(lblCpf);
        
        textFieldCpf = new JTextField();
        textFieldCpf.setColumns(10);
        textFieldCpf.setBounds(26, 134, 434, 20);
        panel.add(textFieldCpf);
        
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(26, 165, 54, 14);
        panel.add(lblTelefone);
        
        textFieldTelefone = new JTextField();
        textFieldTelefone.setColumns(10);
        textFieldTelefone.setBounds(26, 181, 434, 20);
        panel.add(textFieldTelefone);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(26, 212, 54, 14);
        panel.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(26, 228, 434, 20);
        panel.add(textFieldEmail);
        
        rdbtnNewRadioButton = new JRadioButton("Escola?");
        rdbtnNewRadioButton.setBounds(26, 349, 109, 23);
        panel.add(rdbtnNewRadioButton);
        
        if (mostrarBotaoEscola) {
            rdbtnNewRadioButton.setVisible(true);
        } else {
            rdbtnNewRadioButton.setVisible(false);
        }
        
        JButton btnCadastrar = new JButton(clienteSelecionado == null ? "Incluir" : "Alterar");
        btnCadastrar.addActionListener(e -> {
            String buttonRadio = "0";
            if (rdbtnNewRadioButton.isSelected()) {
                buttonRadio = "1";
            }
            
            Cliente cliente = new Cliente(null, textFieldNome.getText().toLowerCase(), textFieldCpf.getText(), textFieldEmail.getText(), textFieldTelefone.getText(), textFieldChave.getText(), textFieldSenha.getText(), buttonRadio);
            if (clienteSelecionado == null) {
                if (!"".equalsIgnoreCase(textFieldNome.getText()) &&
                    !"".equalsIgnoreCase(textFieldChave.getText()) &&
                    !"".equalsIgnoreCase(textFieldSenha.getText())) {
                    
                    String senhaInserida = new String(textFieldSenha.getText());
                    Criptografia criptografia = new Criptografia(senhaInserida, Criptografia.MD5);
                    String senhaCriptografada = criptografia.criptografar();
                    cliente.setSenha(senhaCriptografada);
                    
                    dao.cadastrarCliente(cliente);
                    if (mostrarBotaoEscola) {
                        abrirListaClientes(listaCliente);
                    } else {
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
                }
            } else {
                if (!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpf.getText())) {
                    dao.alterarCliente(clienteSelecionado.getId(), cliente);
                    abrirListaClientes(listaCliente);
                } else {
                    JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
                }
            }
        });
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrar.setBackground(new Color(106, 181, 111));
        btnCadastrar.setBounds(156, 382, 173, 23);
        panel.add(btnCadastrar);
        
        textFieldChave = new JTextField();
        textFieldChave.setColumns(10);
        textFieldChave.setBounds(26, 275, 334, 20);
        panel.add(textFieldChave);
        
        JLabel lblChave = new JLabel("Chave da Escola");
        lblChave.setBounds(26, 259, 181, 14);
        panel.add(lblChave);
        
        JLabel lblTenhaEmMos = new JLabel("Tenha em mãos a chave fornecida pela sua escola.");
        lblTenhaEmMos.setForeground(new Color(128, 128, 128));
        lblTenhaEmMos.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblTenhaEmMos.setBounds(99, 41, 253, 35);
        panel.add(lblTenhaEmMos);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(26, 306, 46, 14);
        panel.add(lblSenha);
        
        textFieldSenha = new JTextField();
        textFieldSenha.setColumns(10);
        textFieldSenha.setBounds(26, 322, 434, 20);
        panel.add(textFieldSenha);
        
        if (clienteSelecionado != null) {
            preencherCampos(clienteSelecionado);
            JButton btnNewButton = new JButton("Apagar");
            btnNewButton.addActionListener(e -> {
                dao.excluirCliente(clienteSelecionado.getId());
                abrirListaClientes(listaCliente);
            });
            btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
            btnNewButton.setForeground(new Color(255, 255, 255));
            btnNewButton.setBackground(new Color(128, 0, 0));
            btnNewButton.setBounds(367, 384, 89, 23);
            panel.add(btnNewButton);
        }
        
        if (cliente2 != null) {
            textFieldChave.setText(cliente2.getChave());
            textFieldChave.setEditable(false); 
        }
    }
    
    private String gerarChaveAleatoria() {
        // Método para gerar uma chave aleatória (exemplo simples)
        // Você pode substituir isso por um método mais sofisticado, se desejar
        return Integer.toHexString((int)(Math.random() * 0x10000));
    }
    
    private void preencherCampos(Cliente clienteSelecionado) {
        textFieldNome.setText(clienteSelecionado.getNome());
        textFieldCpf.setText(clienteSelecionado.getCpfCnpj());
        textFieldEmail.setText(clienteSelecionado.getEmail());
        textFieldTelefone.setText(clienteSelecionado.getTelefone());
        textFieldChave.setText(clienteSelecionado.getChave());
        textFieldSenha.setText(clienteSelecionado.getSenha());
        if ("1".equals(clienteSelecionado.getEscola())) {
            rdbtnNewRadioButton.setSelected(true);
        } else {
            rdbtnNewRadioButton.setSelected(false);
        }
    }
    
    private void abrirListaClientes(ListaClientes listaCliente) {
        if (listaCliente != null) {
        	ListaClientes listaClienteNova = new ListaClientes();
            listaClienteNova.setLocationRelativeTo(null);
            listaClienteNova.setVisible(true);  
            listaCliente.dispose();// Mantém a TelaEscola aberta
        }
        dispose();  // Fecha apenas a TelaCadastro
    }
}
