package DAO;

//import static spark.Spark.*;
import java.sql.*;

public class DAO {
	protected Connection conexao;
	
	/**
	 * Construtor padrao da classe DAO.
	 */
	public DAO() {
		conexao = null;
	}
	
	/**
	 * Metodo getter para obter a conexao atual.
	 * @return conexao.
	 */
	public Connection getConnection(){
	    return this.conexao;
	}

	/**
	 * Metodo settar a conexao atual.
	 */
	public void setConnection(Connection conexao) {
	    this.conexao = conexao;
	}
	
	/**
	 * Metodo para conectar a aplicacao com o banco de dados hospedado no
	 * servidor.
	 * @return status - true, se ocorreu conexao, false, caso contrario.	
	 */
    public boolean conectar() {
		String driverName = "postgres.database.azure.com";     
		String serverName = "gabrielvargas-ti2";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + "." + driverName + ":" + porta +"/" + "postgres";
		String username = "gabrielvargas";
		String password = "PucMin@s";
		boolean status = false;
		
		try {
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (Exception e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
    }
	
	/**
	 * Metodo para desconectar a aplicacao com o banco de dados.
	 * @return status - true, se fechou conexao, false, caso contrario.	
	 */
    public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
    }

}