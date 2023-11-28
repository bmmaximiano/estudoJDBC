package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory criaConexao = new ConnectionFactory() {};
		Connection connection = criaConexao.recuperarConexao();
		
		System.out.println("Fechando Conexão!");
		
		connection.close();
		
	}

}
