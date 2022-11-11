package DAO;

import model.Acidente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcidenteDAO extends DAO {
	
	/**
	 * Construtor da classe AcidenteDAO, utilizando heranca da classe DAO.
	 */
	public AcidenteDAO() {
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
	 * Metodo para inserir um acidente no banco de dados.
	 * @param acidente - objeto a ser inserido.
	 * @return status - true, se inserido com sucesso; false, caso contrario.
	 */
	public boolean insert(Acidente acidente) {
		boolean status = false;
		try {
			String sql = "INSERT INTO acidente "
		               + "VALUES ( " + acidente.getCodigo()    + " , '"
		                             + acidente.getNome()      + "', '" 
		                             + acidente.getDescricao() + "'); ";
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
	 * Metodo para deletar um acidente no banco de dados.
	 * @param codigo - atributo chave do acidente a ser excluido.
	 * @return status - true, se deletado com sucesso; false, caso contrario.
	 */
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM acidente WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo para atualizar um acidente no banco de dados.
	 * @param usuario - objeto a ser atualizado.
	 * @return status - true, se atualizado com sucesso; false, caso contrario.
	 */
	public boolean update(Acidente acidente) {
		boolean status = false;
		try {  
			String sql = "UPDATE acidente SET "
				  	   + "nome = "         + acidente.getNome()       + " , " 
					   + "descricao = '"   + acidente.getDescricao()  + "', "
					   + "WHERE codigo = '"+ acidente.getCodigo()     + "'; ";
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
	 * Metodo para obter acidentes por codigo.
	 * @param codigo a ser procurado.
	 * @return acidente - selecionado pelo nome.
	 */
	public Acidente get(int codigo) {
	
		Acidente acidente = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM acidente WHERE codigo = " + codigo;
			ResultSet rs = st.executeQuery(sql);	
	   
	        if(rs.next()){            
	        	 acidente = new Acidente(rs.getInt("codigo"), 
				                         rs.getString("nome"), 
								         rs.getString("descricao"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return acidente;
	}

	/**
	 * Metodo para obter acidentes por nome.
	 * @param nome a ser procurado (atributo UNIQUE).
	 * @return acidente - selecionado pelo nome.
	 */
	public Acidente get(String nome) {
	
		Acidente acidente = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM acidente WHERE nome = '" + nome + "' ";
			ResultSet rs = st.executeQuery(sql);	
	   
	        if(rs.next()){            
	        	 acidente = new Acidente(rs.getInt("codigo"), 
				                         rs.getString("nome"), 
								         rs.getString("descricao"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return acidente;
	}
	
	/**
     * Metodo para criar um arrayList sob um determinado atributo.
	 * @param atributo - servindo como parametro para o ORDER BY.
	 * @return lista contendo todos acidentes
	 */
	protected List<Acidente> getAll(String atributo) {
		List<Acidente> acidentes = new ArrayList<Acidente>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM acidente" + ((atributo.trim().length() == 0) ? "" : (" ORDER BY " + atributo));
			ResultSet rs = st.executeQuery(sql);
			
	        while(rs.next()) {	            	
	        	Acidente acidente = new Acidente(rs.getInt("codigo"), 
				                                 rs.getString("nome"), 
								                 rs.getString("descricao"));
	            acidentes.add(acidente);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return acidentes;
		
	}
	
    /**
	 * Metodo para listar todos acidentes sem ordenacao. 
	 */
	public List<Acidente> getAll() {
		return getAll("");
	}

    /**
	 * Metodo para listar todos acidentes ordenados por nome. 
	 */
	public List<Acidente> getAllByNome() {
		return getAll("nome");		
	}
    
    /**
	 * Metodo para listar todos acidentes ordenados por codigo. 
	 */
	public List<Acidente> getAllByCodigo() {
		return getAll("codigo");		
	}

    /**
	 * Metodo para listar todos acidentes ordenados por descricao. 
	 */
	public List<Acidente> getAllByDescricao() {
		return getAll("descricao");		
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