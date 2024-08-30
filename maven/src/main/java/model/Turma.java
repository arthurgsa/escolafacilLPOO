package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private int id;
    private String nome;
    private List<Materia> materiasEmAndamento;

    // Construtor
    public Turma(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.materiasEmAndamento = new ArrayList<>();
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

    public List<Materia> getMateriasEmAndamento() {
        return materiasEmAndamento;
    }

    public void setMateriasEmAndamento(List<Materia> materiasEmAndamento) {
        this.materiasEmAndamento = materiasEmAndamento;
    }

    // Método para adicionar uma matéria em andamento à turma
    public void adicionarMateriaEmAndamento(Materia materia) {
        materiasEmAndamento.add(materia);
    }

    // Método para remover uma matéria em andamento da turma
    public void removerMateriaEmAndamento(Materia materia) {
        materiasEmAndamento.remove(materia);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", materiasEmAndamento=" + materiasEmAndamento +
                '}';
    }
}
