package model;

public class Professor extends Visualizador {
    private String idProfessor;

    // Construtor
    public Professor(String usuario, String senhaCriptografada, String idProfessor) {
        super(usuario, senhaCriptografada);
        this.idProfessor = idProfessor;
    }

    // Getter e Setter
    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    
   
}


