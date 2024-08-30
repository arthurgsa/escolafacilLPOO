package model;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private boolean administrador; // Novo atributo para indicar se o usuário é administrador

    // Construtor
    public Usuario(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

  
}
