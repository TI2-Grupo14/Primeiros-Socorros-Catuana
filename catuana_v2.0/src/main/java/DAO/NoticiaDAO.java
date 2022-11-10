package DAO;

import model.Noticia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		               + "VALUES ( " + noticia.getId()        + " , '"
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
	 * Metodo para obter noticias por nome.
	 * @param id a ser procurado (atributo Primary Key).
	 * @return noticia - selecionado pelo id.
	 */
	public Noticia get(int id) {
		Noticia noticia = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM noticia WHERE id = " + id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 noticia = new Noticia(rs.getInt("id"), 
            		                   rs.getString("titulo"), 
            		                   rs.getString("data"),
            		                   rs.getString("jornal"),
            		                   rs.getString("corpo"),
            		                   rs.getString("link"),
            		                   rs.getString("usuario"),
            		                   rs.getBoolean("aprovacao"));  
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return noticia;
	}
	
	/**
     * Metodo para criar um arrayList sob um determinado atributo.
	 * @param atributo - servindo como parametro para o ORDER BY.
	 */
	protected List<Noticia> getAll(String atributo) {
		List<Noticia> noticias = new ArrayList<Noticia>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM noticia" + ((atributo.trim().length() == 0) ? "" : (" ORDER BY " + atributo));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Noticia noticia = new Noticia(rs.getInt("id"), 
            		                          rs.getString("titulo"), 
                		                      rs.getString("data"),
            		                          rs.getString("jornal"),
            		                          rs.getString("corpo"),
            		                          rs.getString("link"),
            		                          rs.getString("usuario"),
            		                          rs.getBoolean("aprovacao"));  
	            noticias.add(noticia);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return noticias;
	}
	
    /**
	 * Metodo para listar todas noticias sem ordenacao. 
	 */
	public List<Noticia> getAll() {
		return getAll("");
	}

    /**
	 * Metodo para listar todas noticias ordenados por id. 
	 */
	public List<Noticia> getAllById() {
		return getAll("id");		
	}
    
    /**
	 * Metodo para listar todas noticias ordenados por titulo. 
	 */
	public List<Noticia> getAllByTitulo() {
		return getAll("titulo");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por data. 
	 */
	public List<Noticia> getAllByData() {
		return getAll("data");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por jornal. 
	 */
	public List<Noticia> getAllByJornal() {
		return getAll("jornal");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por corpo. 
	 */
	public List<Noticia> getAllByCorpo() {
		return getAll("corpo");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por link. 
	 */
	public List<Noticia> getAllByLink() {
		return getAll("link");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por usuario. 
	 */
	public List<Noticia> getAllByUsuario() {
		return getAll("usuario");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por aprovacao. Caso haja
	 * empate, ordenar por titulo.
	 */
	public List<Noticia> getAllByAprovacao() {
		return getAll("aprovacao, titulo");		
	}
	
}




/*
public Carousel getOffset(int index) {
	
	Carousel carousel = null;
	
	try {
		
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String sql = "SELECT * FROM carousel LIMIT 1 OFFSET " + index;
		
		ResultSet rs = st.executeQuery(sql);	
        
		if(rs.next()) carousel = new Carousel(rs.getInt("id"), rs.getString("image_url"), rs.getString("description"));
		
        st.close();
	} 
	catch(Exception e) { System.err.println(e.getMessage()); }
	return carousel;
}
*/