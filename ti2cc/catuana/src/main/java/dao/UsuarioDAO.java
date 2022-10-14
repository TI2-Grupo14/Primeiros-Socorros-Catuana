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
            		                  rs.getString("email")),
                                      rs.getString("celular")),
            		                  rs.getString("senha")),
            		                  rs.getBoolean("adm"),
            		                  rs.getBoolean("medico");
       }
        st.close();
        
        return usuarios;
    }
}