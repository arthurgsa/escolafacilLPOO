package model;

public class Materia {

    private int id;
    private String nome;
    private String descricao;
    private int idProfessor;

    // Construtor
    public Materia(int id, String nome, String descricao, int idProfessor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idProfessor = idProfessor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idProfessor=" + idProfessor +
                '}';
    }
}
