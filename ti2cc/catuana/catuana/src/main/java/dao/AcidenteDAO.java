package dao;

import model.Acidente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
			String sql = "INSERT INTO acidente"
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
	 * Metodo para listar os acidentes contidos no banco de dados.
	 * @return acidentes[] - array de objetos do tipo Acidentes com todos os
	 * que estao no banco de dados.
	 */	
    public Acidente[] getAllAcidentes() throws Exception {
        connection.conectar();
        Statement st = connection.conectar().createStatement
        		(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM acidente;");
        rs.last();
        Acidente[] acidentes = new Acidente[rs.getRow()];
        rs.beforeFirst();
        for (int i = 0; rs.next(); i++) {
            acidentes[i] = new Acidente(rs.getInt("codigo"), 
            		                    rs.getString("nome"), 
            		                    rs.getString("descricao"));
        }
        st.close();
        
        return acidentes;
    }	
	
	/**
	 * Metodo para obter acidentes por nome.
	 * @param nome a ser procurado (atributo UNIQUE).
	 * @return acidente - selecionado pelo nome.
	 */
	public Acidente getByNome(String nome) {
		Acidente acidente = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM acidente WHERE nome = '" + nome + "'";
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
	 */
	protected List<Acidente> get(String atributo) {
		List<Acidente> acidentes = new ArrayList<Acidente>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM acidente " + ((atributo.trim().length() == 0) ? "" : (" ORDER BY " + atributo));
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
	public List<Acidente> get() {
		return get("");
	}

    /**
	 * Metodo para listar todos acidentes ordenados por nome. 
	 */
	public List<Acidente> getByNome() {
		return get("nome");		
	}
    
    /**
	 * Metodo para listar todos acidentes ordenados por codigo. 
	 */
	public List<Acidente> getByCodigo() {
		return get("codigo");		
	}

    /**
	 * Metodo para listar todos acidentes ordenados por descricao. 
	 */
	public List<Acidente> getByDescricao() {
		return get("descricao");		
	}

}