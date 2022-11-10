package model;

import org.json.JSONObject;

public class Usuario {
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	private String senha;
	private boolean adm;
	private boolean medico;
	
    /**
     * Construtor padrao da classe Usuario.
     */
	public Usuario() {
        nome = "";
        cpf = "";
        email = "";
        celular = "";
        senha = "";
        adm = false;
        medico = false;
	}

    /**
     * Construtor com passagem de parametros da classe Usuario.
     */
	public Usuario(String nome, String cpf, String email, String celular,
                   String senha, boolean adm, boolean medico) {
		setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setCelular(celular);
        setSenha(senha);
        setAdm(adm);
        setMedico(medico);
	}

    /* ----------------- Metodos getters da classe Usuario ----------------- */

    public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getCelular() {
		return celular;
	}

	public String getSenha() {
		return senha;
	}

	public boolean getAdm() {
		return adm;
	}

	public boolean getMedico() {
		return medico;
	}

    /* ----------------- Metodos setters da classe Usuario ----------------- */

	public void setNome(String nome) {
		this.nome = nome;
	}
    
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    
	public void setEmail(String email) {
		this.email = email;
	}
    
	public void setCelular(String celular) {
		this.celular = celular;
	}
    
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public void setMedico(boolean medico) {
		this.medico = medico;
	}

    /**
     * Metodo para criar uma string com as possiveis permissoes do usuario: 
     * administrador e medico. Caso nao possua nenhuma, retorna string vazia.
     * @param adm - atributo booleado para permissao de administrador.
     * @param medico - atributo booleado para permissao de medico.
     * @return resp - string concatenada com as permissoes caso existam.
     */
    public String is(boolean adm, boolean medico) {
        String resp = "[ ";

        if (adm){
            resp += "Administrador ";
        }

        if (medico){
            resp += "Médico ";
        }

        if (resp.equals("[ ")){
            resp = "";
        } else {
            resp += "]";
        }

        return resp;
    }

	/**
	 * Metodo sobreposto da classe Object.
     * E executado quando um objeto precisa ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Nome: " + nome + "\tCPF: " + cpf + "\tEmail: " + email 
             + "\tCelular: " + celular + "\tSenha: " + senha + " "
			 + is(adm, medico);
	}
	
    /**
     * Metodo sobreposto da classe Object.
     * E executado para comparar dois objetos do tipo Usuario, utilizando o
     * atributo CPF.
     */
	@Override
	public boolean equals(Object obj) {
		return (this.getCpf() == ((Usuario) obj).getCpf());
	}	

	/**
	 * Metodo para gerar um JSON com os atributos da classe.
	 * @return obj json.
	 */
	public JSONObject toJson() {
		
        JSONObject obj = new JSONObject();
        
        obj.put("nome", this.nome);
        obj.put("cpf", this.cpf);
        obj.put("email", this.email);
        obj.put("celular", this.celular);
        obj.put("senha", this.senha);
        obj.put("adm", this.adm);
        obj.put("medico", this.medico);
        
        return obj;
    }
}
