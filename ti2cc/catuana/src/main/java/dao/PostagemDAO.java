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
	 * @return postagens[] - array de objetos do tipo Postagems com todos os
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
}