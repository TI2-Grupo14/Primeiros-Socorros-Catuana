package dao;

import static spark.Spark.*;
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
	 * Metodo getter para obter a conecao atual.
	 * @return conexao.
	 */
	public Connection getConnection(){
	    return this.connection;
	}

	/**
	 * Metodo settar a conecao atual.
	 */
	public void setConnection(Connection connection) {
	    this.connection = connection;
	}
	
	/**
	 * Metodo para conectar a aplicacao com o banco de dados hospedado no
	 * servidor.	
	 */
    public void conectar() throws Exception {
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
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
    }
	
	/**
	 * Metodo para desconectar a aplicacao com o banco de dados.
	 */
    public void close() throws SQLException{
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