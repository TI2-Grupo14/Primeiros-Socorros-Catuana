Package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UsuarioDAO extends DAO {
	
	/**
	 * Construtor da classe UsuarioDAO, utilizando heranca da classe DAO.
	 */
	public UsuarioDAO() {
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
	 * Metodo para inserir um usuario no banco de dados.
	 * @param usuario - objeto a ser inserido.
	 * @return status - true, se inserido com sucesso; false, caso contrario.
	 */
	public boolean insert(Usuario usuario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO usuario"
		               + "VALUES ( '" + usuario.getNome()   + "', '" 
					                  + usuario.getCpf()    + "', '"
					                  + usuario.getEmail()  + "', '" 
					                  + usuario.getCeular() + "', '" 
					                  + usuario.getSenha()  + "', " 
					                  + usuario.getAdm()    + " , " 
					                  + usuario.getMedico() + " );"
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
	 * Metodo para atualizar um usuario no banco de dados.
	 * @param usuario - objeto a ser atualizado.
	 * @return status - true, se atualizado com sucesso; false, caso contrario.
	 */
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET "
		               + "nome = '"     + usuario.getNome()    + "', "
					   + "email = '"    + usuario.getEmail()   + "', " 
					   + "celular = '"  + usuario.getCelular() + "', "
					   + "senha = '"    + usuario.getSenha()   + "', "
				       + "adm = "       + usuario.getAdm()     + " , "			
				       + "medico = "    + usuario.getMedico()  + " , "			
		               "WHERE cpf = '"  + usuario.getCpf()     + "'; ";
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
	 * Metodo para deletar um usuario no banco de dados.
	 * @param cpf - atributo chave do usuario a ser excluido.
	 * @return status - true, se deletado com sucesso; false, caso contrario.
	 */
	public boolean delete(String cpf) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE cpf = '" + cpf "';");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo para listar os usuarios contidos no banco de dados.
	 * @return usuarios[] - array de objetos do tipo Usuarios com todos os
	 * que estao no banco de dados.
	 */	
    public Usuario[] getAllUsuarios() throws Exception {
        connection.conectar();
        Statement st = connection.conectar().createStatement
        		(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM usuario;");
        rs.last();
        Usuario[] usuarios = new Usuario[rs.getRow()];
        rs.beforeFirst();
        for (int i = 0; rs.next(); i++) {
            usuarios[i] = new Usuario(rs.getString("nome"), 
            		                  rs.getString("cpf"), 
            		                  rs.getString("email"),
                                      rs.getString("celular"),
            		                  rs.getString("senha"),
            		                  rs.getBoolean("adm"),
            		                  rs.getBoolean("medico"));
       }
        st.close();
        
        return usuarios;
    }

	/**
	 * Metodo para obter usuarios por id.
	 * @param nome a ser procurado.
	 * @return usuario - selecionado pelo nome.
	 */
	public Usuario getByNome(String nome) {
		Usuario usuario = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE nome = '" + nome "' ";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getString("nome"), 
            		                   rs.getString("cpf"), 
            		                   rs.getString("email"),
                                       rs.getString("celular"),
            		                   rs.getString("senha"),
            		                   rs.getBoolean("adm"),
            		                   rs.getBoolean("medico"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}

	/**
	 * Metodo para obter usuarios por email.
	 * @param email a ser procurado.
	 * @return usuario - selecionado pelo email.
	 */
	public Usuario getByEmail(String email) {
		Usuario usuario = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE email = '" + email "' ";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getString("nome"), 
            		                   rs.getString("cpf"), 
            		                   rs.getString("email"),
                                       rs.getString("celular"),
            		                   rs.getString("senha"),
            		                   rs.getBoolean("adm"),
            		                   rs.getBoolean("medico"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	/**
     * Metodo para criar um arrayList sob um determinado atributo.
	 * @param atributo - servindo como parametro para o ORDER BY.
	 */
	protected List<Usuario> get(String atributo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((atributo.trim().length() == 0) ? "" : (" ORDER BY " + atributo));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Usuario usuario = new Usuario(rs.getString("nome"), 
            		                          rs.getString("cpf"), 
            		                          rs.getString("email"),
                                              rs.getString("celular"),
            		                          rs.getString("senha"),
            		                          rs.getBoolean("adm"),
            		                          rs.getBoolean("medico"));
	            usuarios.add(usuario);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

    /**
	 * Metodo para listar todos postages sem ordenacao. 
	 */
	public List<Usuario> get() {
		return get("");
	}

    /**
	 * Metodo para listar todos postages ordenados por nome. 
	 */
	public List<Usuario> getByNome() {
		return get("nome");		
	}
    
    /**
	 * Metodo para listar todos usuarios ordenados por cpf. 
	 */
	public List<Usuario> getByCpf() {
		return get("cpf");		
	}

    /**
	 * Metodo para listar todos usuarios ordenados por email. 
	 */
	public List<Usuario> getByEmail() {
		return get("email");		
	}

    /**
	 * Metodo para listar todos usuarios ordenados por celular. 
	 */
	public List<Usuario> getByCelular() {
		return get("celular");		
	}

    /**
	 * Metodo para listar todos usuarios ordenados por senha. 
	 */
	public List<Usuario> getBySenha() {
		return get("senha");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por adm. Caso haja
	 * empate, ordenar por nome.
	 */
	public List<Noticia> getByAdm() {
		return get("adm, nome");		
	}

    /**
	 * Metodo para listar todas noticias ordenados por medico. Caso haja
	 * empate, ordenar por nome.
	 */
	public List<Noticia> getByMedico() {
		return get("medico, nome");		
	}
}