package DAO;

import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			String sql = "INSERT INTO usuario "
		               + "VALUES ( '" + usuario.getNome()    + "', '" 
					                  + usuario.getCpf()     + "', '"
					                  + usuario.getEmail()   + "', '" 
					                  + usuario.getCelular() + "', '" 
					                  + usuario.getSenha()   + "',  " 
					                  + usuario.getAdm()     + " ,  " 
					                  + usuario.getMedico()  + " ); ";
			System.out.println(sql);
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
			st.executeUpdate("DELETE FROM usuario WHERE cpf = '" + cpf + "';");
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
				       + "adm = "       + usuario.getAdm()     + ",  "			
				       + "medico = "    + usuario.getMedico()  + "   "			
		               + "WHERE cpf = '"+ usuario.getCpf()     + "'; ";

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
	 * Metodo para obter usuarios por cpf.
	 * @param cpf a ser procurado.
	 * @return usuario - selecionado pelo cpf.
	 */
	public Usuario get(String cpf) {
		Usuario usuario = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "' ";
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
	public List<Usuario> getAll(String atributo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario " + ((atributo.trim().length() == 0) ? "" : (" ORDER BY " + atributo));
			System.out.println(sql);
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
	 * Metodo para listar todas postagens sem ordenacao. 
	 */
	public List<Usuario> getAll() {
		return getAll("");
	}

    /**
	 * Metodo para listar todos postages ordenados por nome. 
	 */
	public List<Usuario> getAllByNome() {
		return getAll("nome");		
	}
    
    /**
	 * Metodo para listar todos usuarios ordenados por cpf. 
	 */
	public List<Usuario> getAllByCpf() {
		return getAll("cpf");		
	}

    /**
	 * Metodo para listar todos usuarios ordenados por email. 
	 */
	public List<Usuario> getAllByEmail() {
		return getAll("email");		
	}

    /**
	 * Metodo para listar todos usuarios ordenados por celular. 
	 */
	public List<Usuario> getAllByCelular() {
		return getAll("celular");		
	}

    /**
	 * Metodo para listar todos usuarios ordenados por senha. 
	 */
	public List<Usuario> getAllBySenha() {
		return getAll("senha");		
	}

	/*
	public void main (String[] args) {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = getAll();
		
		for(int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			System.out.println(user);
			
		}
	}
	*/

}