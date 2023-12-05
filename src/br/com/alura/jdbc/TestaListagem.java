package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Classe que realiza o teste de conexão com o banco de dados mosntra o conteudo da tabela produto 
public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		//crando uma conexão com o banco de dados
		ConnectionFactory criaConexao = new ConnectionFactory();
		
		Connection connection = criaConexao.recuperarConexao();
		/*
		 *Realizndo a persistência dos dados 
		 */
		PreparedStatement stm = connection.prepareStatement("select id, nome, descricao from produto");
				stm.execute();
				ResultSet rst = stm.getResultSet();
				while(rst.next()) {
					Integer id = rst.getInt("id");
					System.out.println(id);
					String nome = rst.getString("nome");
					System.out.println(nome);
					String descricao = rst.getString("descricao");
					System.out.println(descricao);
				}
				connection.close();

	}

}
