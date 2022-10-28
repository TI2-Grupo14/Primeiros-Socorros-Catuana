package dao;

import model.Postagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PostagemDAO extends DAO {
	
	/**
	 * Construtor da classe PostagemDAO, utilizando heranca da classe DAO.
	 */
	public PostagemDAO() {
		super();
		conectar();
	}
	
	/**
	 * Metodo para chamar o metodo close e encerrar a conexao com o banco de 
	 * dados.
	 */
	public void finalize() {
		close();
	}
	
	/**
	 * Metodo para inserir um postagem no banco de dados.
	 * @param postagem - objeto a ser inserido.
	 * @return status - true, se inserido com sucesso; false, caso contrario.
	 */
	public boolean insert(Postagem postagem) {
		boolean status = false;
		try {			
			String sql = "INSERT INTO postagem"
		               + "VALUES ( " + postagem.getID()        + " , '"
                                     + postagem.getMedico()    + "',  " 
	                                 + postagem.getAcidente()  + " , '" 
			                         + postagem.getSite()      + "', '" 
					                 + postagem.getVideoaula() + "', '" 
					                 + postagem.getDescricao() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo para atualizar um postagem no banco de dados.
	 * @param usuario - objeto a ser atualizado.
	 * @return status - true, se atualizado com sucesso; false, caso contrario.
	 */
	public boolean update(Postagem postagem) {
		boolean status = false;
		try {  			
			String sql = "UPDATE postagem SET "
					   + "medico = '"      + postagem.getMedico()    + "', "
					   + "acidente = "     + postagem.getAcidente()  + " , "
					   + "site = '"        + postagem.getSite()      + "', " 
	                   + "videoaula = '"   + postagem.getVideoaula() + "', "
					   + "descricao = '"   + postagem.getDescricao() + "', "
					   + "WHERE id = "     + postagem.getId()        + " ; ";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	/**
	 * Metodo para deletar um postagem no banco de dados.
	 * @param id - atributo chave do postagem a ser excluido.
	 * @return status - true, se deletado com sucesso; false, caso contrario.
	 */
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM postagem WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo para listar os postagens contidos no banco de dados.
	 * @return postagens[] - array de objetos do tipo Postagens com todos os
	 * que estao no banco de dados.
	 */	
    public Postagem[] getAllPostagems() throws Exception {
        connection.conectar();
        Statement st = connection.conectar().createStatement
        		(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM postagem;");
        rs.last();
        Postagem[] postagens = new Postagem[rs.getRow()];
        rs.beforeFirst();
        for (int i = 0; rs.next(); i++) {
            postagens[i] = new Postagem(rs.getInt("id"), 
            		                    rs.getString("medico"), 
            		                    rs.getInt("acidente"),
            		                    rs.getString("site"),
            		                    rs.getString("videoaula"),
            		                    rs.getString("descricao"));     
        }
        st.close();
        
        return postagens;
    }

	/**
	 * Metodo para obter postagens por id.
	 * @param id a ser procurado.
	 * @return postagem - selecionado pelo id.
	 */
	public Postagem getById(String id) {
		Postagem postagem = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM postagem WHERE id = " + id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 postagem = new Postagem(rs.getInt("id"), 
            		                     rs.getString("medico"), 
            		                     rs.getInt("acidente"),
            		                     rs.getString("site"),
            		                     rs.getString("videoaula"),
            		                     rs.getString("descricao")); 
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return postagem;
	}
	
	/**
     * Metodo para criar um arrayList sob um determinado atributo.
	 * @param atributo - servindo como parametro para o ORDER BY.
	 */
	protected List<Postagem> get(String atributo) {
		List<Postagem> postagens = new ArrayList<Postagem>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM postagem" + ((atributo.trim().length() == 0) ? "" : (" ORDER BY " + atributo));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Postagem postagem = new Postagem(rs.getInt("id"), 
            		                             rs.getString("medico"), 
            		                             rs.getInt("acidente"),
            		                             rs.getString("site"),
            		                             rs.getString("videoaula"),
            		                             rs.getString("descricao")); 
	            postagens.add(postagem);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return postagens;
	}

    /**
	 * Metodo para listar todos postages sem ordenacao. 
	 */
	public List<Postagem> get() {
		return get("");
	}

    /**
	 * Metodo para listar todos postages ordenados por id. 
	 */
	public List<Postagem> getById() {
		return get("id");		
	}
    
    /**
	 * Metodo para listar todos postagens ordenados por medico. 
	 */
	public List<Postagem> getByMedico() {
		return get("medico");		
	}

    /**
	 * Metodo para listar todos postagens ordenados por acidente. 
	 */
	public List<Postagem> getByAcidente() {
		return get("acidente");		
	}

    /**
	 * Metodo para listar todos postagens ordenados por site. 
	 */
	public List<Postagem> getBySite() {
		return get("site");		
	}

    /**
	 * Metodo para listar todos postagens ordenados por videoaula. 
	 */
	public List<Postagem> getByVideoaula() {
		return get("videoaula");		
	}

    /**
	 * Metodo para listar todos postagens ordenados por descricao. 
	 */
	public List<Postagem> getByDescricao() {
		return get("descricao");		
	}

}