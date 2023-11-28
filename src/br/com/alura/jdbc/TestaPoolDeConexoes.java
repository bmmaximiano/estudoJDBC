package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class TestaPoolDeConexoes {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory() {
		};
		try {
			
			for(int i =0; i < 20; i++) {
				Connection connection = connectionFactory.recuperarConexao();
				System.out.println("Conexão: " + i + " aberta");
			}
			
		}
		catch(Exception e) {
			System.out.println("Falha de conexão");
		}
		
		

	}

}
