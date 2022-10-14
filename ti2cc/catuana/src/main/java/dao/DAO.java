package dao;

import static spark.Spark.*;
import java.sql.*;

public class DAO {
	protected Connection conexao;
	protected boolean flagConnectionIsConnected;
	
	/**
	 * Construtor padrao da classe DAO.
	 */
	public DAO() {
		conexao = null;
		flagConnectionIsConnected = false;
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
        this.connection = DriverManager.getConnection("jdbc:postgresql://"
        		+ "db.biicxlhzbaskovsdnlxu.supabase.co:5432/postgres",
        		"postgres","0wTJ4kNfqxafPx8t");
       if(connection == null){
           throw new Exception("Url maybe broked");
       } else{
           System.out.println("Connection has been connected with success");
           flagConnectionIsConnected = true;
       }
    }
	
	/**
	 * Metodo para desconectar a aplicacao com o banco de dados.
	 */
    public void close() throws SQLException{
        try{
            connection.close();
            if(connection == null){
                flagConnectionIsConnected = false;
            }
        }catch(Exception e){throw e;}
    }

}