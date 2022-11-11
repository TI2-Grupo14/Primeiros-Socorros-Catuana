package model;

import org.json.JSONObject;

public class Postagem {
	private int id;
	private String medico;
	private int acidente;
	private String site;
	private String videoaula;
	private String descricao;
	
    /**
     * Construtor padrao da classe Postagem.
     */
	public Postagem() {
        id = 0;
        medico = "";
        acidente = 0;
        site = "";
        videoaula = "";
        descricao = "";
	}

    /**
     * Construtor com passagem de parametros da classe Postagem, sem id
     */
	public Postagem(String medico, int acidente, String site,
                    String videoaula, String descricao) {
        setMedico(medico);
        setAcidente(acidente);
        setSite(site);
        setVideoaula(videoaula);
        setDescricao(descricao);
	}

    /**
     * Construtor com passagem de parametros da classe Postagem.
     */
	public Postagem(int id, String medico, int acidente, String site,
                    String videoaula, String descricao) {
  		setId(id);     
	    setMedico(medico);
        setAcidente(acidente);
        setSite(site);
        setVideoaula(videoaula);
        setDescricao(descricao);
	}

    /* ----------------- Metodos getters da classe Postagem ----------------- */

	public int getId() {
		return id;
	}

	public String getMedico() {
		return medico;
	}

	public int getAcidente() {
		return acidente;
	}

	public String getSite() {
		return site;
	}

	public String getVideoaula() {
		return videoaula;
	}

	public String getDescricao() {
		return descricao;
	}

    /* ----------------- Metodos setters da classe Postagem ----------------- */

    public void setId(int id) {
		this.id = id;
	}
    
	public void setMedico(String medico) {
		this.medico = medico;
	}
    
	public void setAcidente(int acidente) {
		this.acidente = acidente;
	}
    
	public void setSite(String site) {
		this.site = site;
	}
    
	public void setVideoaula(String videoaula) {
		this.videoaula = videoaula;
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
		return "Id: " + id + "\tMédico: " + medico + "\tAcidente: " + acidente 
             + "\tSite: " + site + "\tVideoaula: " + videoaula 
             + "\tDescrição: " + descricao;
	}
	
    /**
     * Metodo sobreposto da classe Object.
     * E executado para comparar dois objetos do tipo Postagem, utilizando o
     * atributo ID.
     */
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Postagem) obj).getId());
	}

	/**
	 * Metodo para gerar um JSON com os atributos da classe.
	 * @return obj json.
	 */
	public JSONObject toJson() {
		
        JSONObject obj = new JSONObject();
        
        obj.put("id", this.id);
        obj.put("medico", this.medico);
        obj.put("acidente", this.acidente);
        obj.put("site", this.site);
        obj.put("videoaula", this.videoaula);
        obj.put("descricao", this.descricao);
        
        return obj;
    }
}
