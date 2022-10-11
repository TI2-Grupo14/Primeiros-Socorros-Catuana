package model;

public class Noticia {
    private int id;
	private String titulo;
    private String data;
	private String jornal;
	private String corpo;
	private String link;
	private String usuario;
	private boolean aprovacao;
	
    /**
     * Construtor padrao da classe Noticia.
     */
	public Noticia() {
        id = 0;
        titulo = "";
        data = "";
        jornal = "";
        corpo = "";
        link = "";
        usuario = "";
        aprovacao = false;
	}

    /**
     * Construtor com passagem de parametros da classe Noticia.
     */
	public Noticia(int id, String titulo, String data, String jornal,
                   String corpo, String link, String usuario, 
                   boolean aprovacao) {
		setId(id);
        setTitulo(titulo);
        setData(data);
        setJornal(jornal);
        setCorpo(corpo);
        setLink(link);
        setUsuario(usuario);
        setAprovacao(aprovacao);
	}

    /* ----------------- Metodos getters da classe Noticia ----------------- */

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getData() {
		return data;
	}
	public String getJornal() {
		return jornal;
	}

	public String getCorpo() {
		return corpo;
	}

	public String getLink() {
		return link;
	}

	public String getUsuario() {
		return usuario;
	}

	public boolean getAprovacao() {
		return aprovacao;
	}

    /* ----------------- Metodos setters da classe Noticia ----------------- */

	public void setId(int id) {
		this.id = id;
	}
    
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
    
	public void setData(String data) {
		this.data = data;
	}
    
	public void setJornal(String jornal) {
		this.jornal = jornal;
	}
    
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
    
	public void setLink(String link) {
		this.link = link;
	}
    
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
    
	public void setAprovacao(boolean aprovacao) {
		this.aprovacao = aprovacao;
	}

    /**
     * Metodo para criar uma string com o status de aprovacao da noticia.
     * @param aprovacao - atributo booleado para aprovacao da noticia.
     * @return resp - string "Aprovada", se aprovada; "Não aprovada", 
     * caso contrario.
     */
    public String getStatus(boolean aprovacao) {
        String resp;

        if (aprovacao){
            resp = "Aprovada ";
        } else {
            resp = "Não Aprovada";
        }

        return resp;
    }

	/**
	 * Metodo sobreposto da classe Object.
     * E executado quando um objeto precisa ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Id: " + id + "\tTítulo: " + titulo + "\tData: " + data 
             + "\tJornal: " + jornal + "\tCorpo: " + corpo + "\tLink: " + link
             + "\tUsuario: " + usuario + getStatus();
	}
	
    /**
     * Metodo sobreposto da classe Object.
     * E executado para comparar dois objetos do tipo Noticia, utilizando o
     * atributo ID.
     */
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Noticia) obj).getId());
	}	
}