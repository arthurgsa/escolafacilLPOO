package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controladores.Conexao;
import model.Cliente;
import model.Materia;
import model.Usuario;

public class DAO {
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
    private static String CADASTRAR_CLIENTE = "INSERT INTO CLIENTE "
            + "(ID, NOME, CPFCNPJ, EMAIL, TELEFONE, CHAVE, SENHA, ESCOLA) "
            + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
    
    private static String CONSULTAR_CLIENTE = "SELECT * FROM CLIENTE "
            + "WHERE ID = ?";
    
    private static String ALTERAR_CLIENTE = "UPDATE CLIENTE "
            + "SET NOME = ?, CPFCNPJ = ?, EMAIL = ?, TELEFONE = ?, CHAVE = ?, SENHA = ?, ESCOLA = ? "
            + "WHERE ID = ?";
    
    private static String EXCLUIR_CLIENTE = "DELETE FROM CLIENTE "
            + "WHERE ID = ?";
    
    private static String LISTAR_CLIENTES = "SELECT * FROM CLIENTE "
            + "WHERE 1 = 1";
    
    private static String CONSULTAR_USUARIO = "SELECT USUARIO, SENHA "
            + "FROM USUARIO "
            + "WHERE USUARIO = ? "
            + "AND SENHA = ?";
    
    public DAO() {
        
    }
    
    // Cadastrar cliente
    public void cadastrarCliente(Cliente cliente) {
        Connection connection = Conexao.getInstancia().abrirConexao();
        String query = CADASTRAR_CLIENTE;
        
        try {
            preparedStatement = connection.prepareStatement(query);
            
            int i = 1;
            preparedStatement.setString(i++, cliente.getNome().toLowerCase());
            preparedStatement.setString(i++, cliente.getCpfCnpj());
            preparedStatement.setString(i++, cliente.getEmail());
            preparedStatement.setString(i++, cliente.getTelefone());
            preparedStatement.setString(i++, cliente.getChave());
            preparedStatement.setString(i++, cliente.getSenha());
            preparedStatement.setString(i++, cliente.getEscola());
            
            preparedStatement.execute();
            connection.commit();
            
            JOptionPane.showMessageDialog(null, "CLIENTE INCLUÍDO COM SUCESSO");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
    }
    
    // Consultar cliente
    public Cliente consultarCliente(String id) throws Exception {
        Connection connection = Conexao.getInstancia().abrirConexao();
        Cliente cliente = null;
        String query = CONSULTAR_CLIENTE;

        try {
            preparedStatement = connection.prepareStatement(query);
            
            int i = 1;
            preparedStatement.setString(i++, id);
            
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                cliente = new Cliente(
                    resultSet.getString("ID"),
                    resultSet.getString("NOME").toLowerCase(),
                    resultSet.getString("CPFCNPJ"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("TELEFONE"),
                    resultSet.getString("CHAVE"),
                    resultSet.getString("SENHA"),
                    resultSet.getString("ESCOLA")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível localizar o cliente selecionado", "", JOptionPane.WARNING_MESSAGE);
            throw new Exception("Não foi possível localizar o cliente selecionado");
        }
        
        return cliente;
    }

    // Alterar cliente
    public void alterarCliente(String id, Cliente cliente) {
        Connection connection = Conexao.getInstancia().abrirConexao();
        String query = ALTERAR_CLIENTE;
        
        try {
            preparedStatement = connection.prepareStatement(query);
            
            int i = 1;
            preparedStatement.setString(i++, cliente.getNome().toLowerCase());
            preparedStatement.setString(i++, cliente.getCpfCnpj());
            preparedStatement.setString(i++, cliente.getEmail());
            preparedStatement.setString(i++, cliente.getTelefone());
            preparedStatement.setString(i++, cliente.getChave());
            preparedStatement.setString(i++, cliente.getSenha());
            preparedStatement.setString(i++, cliente.getEscola());
            preparedStatement.setString(i++, id);
            
            preparedStatement.execute();
            connection.commit();
            
            JOptionPane.showMessageDialog(null, "CLIENTE ALTERADO COM SUCESSO");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
    }
    
    // Excluir cliente
    public void excluirCliente(String id) {
        Connection connection = Conexao.getInstancia().abrirConexao();
        String query = EXCLUIR_CLIENTE;
        
        try {
            preparedStatement = connection.prepareStatement(query);
            
            int i = 1;
            preparedStatement.setString(i++, id);
            
            preparedStatement.execute();
            connection.commit();
            
            JOptionPane.showMessageDialog(null, "CLIENTE EXCLUÍDO COM SUCESSO");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
    }
    
    // Listar clientes
    public ArrayList<Cliente> listarClientes() throws Exception {
        Connection connection = Conexao.getInstancia().abrirConexao();
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = LISTAR_CLIENTES;

        try {
            preparedStatement = connection.prepareStatement(query);
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                clientes.add(new Cliente(
                    resultSet.getString("ID"),
                    resultSet.getString("NOME"),
                    resultSet.getString("CPFCNPJ"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("TELEFONE"),
                    resultSet.getString("CHAVE"),
                    resultSet.getString("SENHA"),
                    resultSet.getString("ESCOLA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        if (clientes.size() < 1) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "", JOptionPane.WARNING_MESSAGE);
            throw new Exception("Não há clientes cadastrados");
        }
        
        return clientes;
    }
    
    // Consultar usuário
    public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {
        Connection connection = Conexao.getInstancia().abrirConexao();
        Usuario usuario = null;
        String query = "SELECT * FROM USUARIO WHERE USUARIO = ? AND SENHA = ?"; // Atualize a consulta se necessário

        try {
            preparedStatement = connection.prepareStatement(query);
            
            // Defina os parâmetros da consulta
            preparedStatement.setString(1, nomeUsuario);
            preparedStatement.setString(2, senhaCriptografada);
            
            // Execute a consulta
            resultSet = preparedStatement.executeQuery();
            
            // Preencha o objeto Usuario com os dados retornados
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("USUARIO");
                String senha = resultSet.getString("SENHA");
               
                
                usuario = new Usuario(id, nome, senha); // Cria o objeto Usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível localizar o usuário", "", JOptionPane.WARNING_MESSAGE);
            throw new Exception("Não foi possível localizar o usuário");
        }
        
        return usuario;
    }

    
    public String consultarIdPorNomeUsuario(String nomeUsuario) throws Exception {
        Connection connection = Conexao.getInstancia().abrirConexao();
        String id = null;
        String query = "SELECT ID FROM CLIENTE WHERE NOME = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomeUsuario);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getString("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        if (id == null) {
            throw new Exception("Cliente não encontrado com o nome de usuário fornecido");
        }

        return id;
    }
    
    // Fechar conexão
    private void fecharConexao() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            Conexao.getInstancia().fecharConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarChaveExistente(String chave) {
        boolean chaveExiste = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtém a conexão com o banco de dados
            connection = Conexao.getInstancia().abrirConexao();

            // Define a consulta SQL para verificar a existência da chave
            String sql = "SELECT COUNT(*) FROM cliente WHERE chave = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, chave);

            // Executa a consulta
            rs = pstmt.executeQuery();

            // Verifica se a chave existe
            if (rs.next() && rs.getInt(1) > 0) {
                chaveExiste = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return chaveExiste;
    }

    public void cadastrarMateria(Materia materia, String chaveEscola) {
        Connection connection = Conexao.getInstancia().abrirConexao();
        PreparedStatement preparedStatement = null;
        
        try {
            String query = "INSERT INTO MATERIAS (nome, descricao, keyescola) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            
            int i = 1;
            preparedStatement.setString(i++, materia.getNome());
            preparedStatement.setString(i++, materia.getDescricao());
            preparedStatement.setString(i++, chaveEscola);
            
            preparedStatement.execute();
            connection.commit();
            
            JOptionPane.showMessageDialog(null, "MATÉRIA INCLUÍDA COM SUCESSO");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar matéria: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cliente> retornarArrayDeClientes(String nomeEscola) {
        List<Cliente> clientes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtém a conexão com o banco de dados
            connection = Conexao.getInstancia().abrirConexao();

            // Consulta SQL para obter todos os clientes
            String sql = "SELECT * FROM CLIENTE";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Processa os resultados da consulta
            while (resultSet.next()) {
                String nomeCliente = resultSet.getString("nome"); // Ajuste o nome da coluna conforme o seu banco de dados
                if (!nomeCliente.equals(nomeEscola)) {
                    Cliente cliente = new Cliente();
                    cliente.setNome(nomeCliente); // Ajuste o nome da coluna conforme o seu banco de dados
                    cliente.setChave(resultSet.getString("chave")); // Ajuste o nome da coluna conforme o seu banco de dados
                    // Adicione outros atributos do Cliente conforme necessário

                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Lide com exceções de forma adequada
        } finally {
            // Feche os recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }

    public List<String> buscarMaterias(String chave) {
        List<String> materias = new ArrayList<>();
        String sql = "SELECT NOME FROM MATERIAS WHERE KEYESCOLA = ?";
        
        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, chave);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    materias.add(rs.getString("NOME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return materias;
    }

    public boolean armazenarAula(String chave, String horario, String dia, String materiaSelecionada) {
        String sql = "INSERT INTO AULAS (KEYESCOLA, HORARIO, DIA, MATERIA) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, chave);
            stmt.setString(2, horario);
            stmt.setString(3, dia);
            stmt.setString(4, materiaSelecionada);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
