package dao;

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
	 * Metodo para conectar a aplicacao com o banco de dados hospedado no
	 * servidor.	
	 * @return status - true, se a conexao foi bem sucessidade; false, caso
	 * contrario.
	 */
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" 
		           + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	/**
	 * Metodo para desconectar a aplicacao com o banco de dados.
	 * @return status - true, se a conexao for fechada; false, caso contrario.
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