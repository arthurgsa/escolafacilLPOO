package model;

public class Aluno extends Visualizador {
    private String turma;

    // Construtor
    public Aluno(String usuario, String senhaCriptografada, String turma) {
        super(usuario, senhaCriptografada);
        this.turma = turma;
    }

    // Getter e Setter
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

   
}
