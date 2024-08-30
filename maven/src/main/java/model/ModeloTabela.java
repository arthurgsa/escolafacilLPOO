package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {

    // Atualizado para incluir novas colunas
    private static final String[] colunas = {
        "ID", "CPF/CNPJ", "Nome", "Email", "Telefone", "Chave", "Senha", "Escola"
    };

    private ArrayList<Cliente> clientes;

    public ModeloTabela(ArrayList<Cliente> clientes) {
        super();
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getCpfCnpj();
            case 2:
                return cliente.getNome();
            case 3:
                return cliente.getEmail();
            case 4:
                return cliente.getTelefone();
            case 5:
                return cliente.getChave();  // Certifique-se de que Cliente tem o método getChave()
            case 6:
                return cliente.getSenha();  // Certifique-se de que Cliente tem o método getSenha()
            case 7:
                return cliente.getEscola(); // Certifique-se de que Cliente tem o método getEscola()
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
