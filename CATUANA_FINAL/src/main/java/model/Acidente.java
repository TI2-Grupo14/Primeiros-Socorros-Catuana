package model;

import org.json.JSONObject;

public class Acidente {
	private int codigo;
	private String nome;
	private String descricao;
	
    /**
     * Construtor padrao da classe Acidente.
     */
	public Acidente() {
        codigo = 0;
        nome = "";
        descricao = "";
	}

    /**
     * Construtor com passagem de parametros da classe Acidente.
     */
	public Acidente(int codigo, String nome, String descricao) {
		setCodigo(codigo);
        setNome(nome);
        setDescricao(descricao);
	}

    /* ----------------- Metodos getters da classe Acidente ----------------- */

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

    /* ----------------- Metodos setters da classe Acidente ----------------- */

    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
    
	public void setNome(String nome) {
		this.nome = nome;
	}
    
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Metodo sobreposto da classe Object.
     * E executado quando um objeto precisa ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Código: " + codigo + "\tNome: " + nome + "\tDescrição: " 
             + descricao;
	}
	
    /**
     * Metodo sobreposto da classe Object.
     * E executado para comparar dois objetos do tipo Acidente, utilizando o
     * atributo codigo.
     */
	@Override
	public boolean equals(Object obj) {
		return (this.getCodigo() == ((Acidente) obj).getCodigo());
	}
	
	/**
	 * Metodo para gerar um JSON com os atributos da classe.
	 * @return obj json.
	 */
	public JSONObject toJson() {
		
        JSONObject obj = new JSONObject();
        
        obj.put("codigo", this.codigo);
        obj.put("nome", this.nome);
        obj.put("descricao", this.descricao);
        
        return obj;
    }
}
