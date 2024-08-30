package model;

import java.util.Arrays;

public class Professor {
    private String id;
    private String nome;
    private String chave;
    private String[][] arrayDuplo; // Array duplo para armazenar informações adicionais

    // Construtor
    public Professor(String id, String nome, String chave, String[][] arrayDuplo) {
        this.id = id;
        this.nome = nome;
        this.chave = chave;
        this.arrayDuplo = arrayDuplo;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String[][] getArrayDuplo() {
        return arrayDuplo;
    }

    public void setArrayDuplo(String[][] arrayDuplo) {
        this.arrayDuplo = arrayDuplo;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", chave='" + chave + '\'' +
                ", arrayDuplo=" + Arrays.deepToString(arrayDuplo) +
                '}';
    }
}
