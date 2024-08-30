package model;

public class Visualizador {
    private String usuario;
    private String senhaCriptografada;
    private String idCliente;

    // Construtor
    public Visualizador(String usuario, String senhaCriptografada) {
        this.usuario = usuario;
        this.senhaCriptografada = senhaCriptografada;
    }



	// Getters e Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }


	public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }
	
	public String getIdCliente() {
		return idCliente;
	}
	
	
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
}