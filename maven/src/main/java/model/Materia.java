package model;

public class Materia {

    private String nome;
    private String descricao;
    
    // Construtor
    public Materia(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
