package model;

import java.util.Arrays;

public class Turma {
    private int id;
    private String nome;
    private Materia[][] materias; // Array duplo para armazenar as matérias

    // Constantes para o tamanho fixo do array
    private static final int NUM_LINHAS = 12;
    private static final int NUM_COLUNAS = 7;

    // Construtor
    public Turma(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.materias = new Materia[NUM_LINHAS][NUM_COLUNAS];
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

    public Materia[][] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[][] materias) {
        if (materias.length == NUM_LINHAS && materias[0].length == NUM_COLUNAS) {
            this.materias = materias;
        } else {
            throw new IllegalArgumentException("O array de matérias deve ter 12 linhas e 7 colunas.");
        }
    }

    // Método para adicionar uma matéria em uma posição específica
    public void adicionarMateria(int linha, int coluna, Materia materia) {
        if (linha >= 0 && linha < NUM_LINHAS && coluna >= 0 && coluna < NUM_COLUNAS) {
            materias[linha][coluna] = materia;
        } else {
            throw new IndexOutOfBoundsException("Posição fora dos limites do array.");
        }
    }

    // Método para remover uma matéria de uma posição específica
    public void removerMateria(int linha, int coluna) {
        if (linha >= 0 && linha < NUM_LINHAS && coluna >= 0 && coluna < NUM_COLUNAS) {
            materias[linha][coluna] = null;
        } else {
            throw new IndexOutOfBoundsException("Posição fora dos limites do array.");
        }
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", materias=" + Arrays.deepToString(materias) +
                '}';
    }
}
