package dao;

import model.Noticia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class NoticiaDAO extends DAO {
	
	/**
	 * Construtor da classe NoticiaDAO, utilizando heranca da classe DAO.
	 */
	public NoticiaDAO() {
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
	 * Metodo para inserir um noticia no banco de dados.
	 * @param noticia - objeto a ser inserido.
	 * @return status - true, se inserido com sucesso; false, caso contrario.
	 */
	public boolean insert(Noticia noticia) {
		boolean status = false;
		try {	
			String sql = "INSERT INTO noticia"
		               + "VALUES ( " + noticia.getID()        + " , '"
                                     + noticia.getTitulo()    + "', '" 
	                                 + noticia.getData()      + "', '" 
			                         + noticia.getJornal()    + "', '" 
					                 + noticia.getCorpo()     + "', '" 
					                 + noticia.getLink()      + "', '" 
					                 + noticia.getUsuario()   + "',  " 
		                             + noticia.getAprovacao() + " ); ";
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
	 * Metodo para atualizar um noticia no banco de dados.
	 * @param usuario - objeto a ser atualizado.
	 * @return status - true, se atualizado com sucesso; false, caso contrario.
	 */
	public boolean update(Noticia noticia) {
		boolean status = false;
		try {  		
			String sql = "UPDATE noticia SET "
				  	   + "titulo = '"      + noticia.getTitulo()    + "', " 
					   + "data = '"        + noticia.getData()      + "', "
					   + "jornal = '"      + noticia.getJornal()    + "', "
					   + "corpo = '"       + noticia.getCorpo()     + "', " 
	                   + "link = '"        + noticia.getLink()      + "', "
					   + "usuario = '"     + noticia.getUsuario()   + "', "
					   + "aprovacao = "    + noticia.getAprovacao() + " , "
					   + "WHERE id = "     + noticia.getId()        + " ; ";
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
	 * Metodo para deletar um noticia no banco de dados.
	 * @param id - atributo chave do noticia a ser excluido.
	 * @return status - true, se deletado com sucesso; false, caso contrario.
	 */
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM noticia WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo para listar os noticias contidos no banco de dados.
	 * @return noticias[] - array de objetos do tipo Noticias com todos os
	 * que estao no banco de dados.
	 */	
    public Noticia[] getAllNoticias() throws Exception {
        connection.conectar();
        Statement st = connection.conectar().createStatement
        		(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM noticia;");
        rs.last();
        Noticia[] noticias = new Noticia[rs.getRow()];
        rs.beforeFirst();
        for (int i = 0; rs.next(); i++) {
            noticias[i] = new Noticia(rs.getInt("id"), 
            		                  rs.getString("titulo"), 
            		                  rs.getString("data"),
            		                  rs.getString("jornal"),
            		                  rs.getString("corpo"),
            		                  rs.getString("link"),
            		                  rs.getString("usuario"),
            		                  rs.getBoolean("aprovacao"));     
        }
        st.close();
        
        return noticias;
    }
}