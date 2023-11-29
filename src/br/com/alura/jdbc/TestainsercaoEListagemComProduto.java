package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.alura.jdbc.DAO.ProdutoDAO;
import br.com.alura.jdbc.modelo.Produto;

public class TestainsercaoEListagemComProduto {

	public static void main(String[] args) {

		Produto comoda = new Produto("Comoda", "Cômoda Vertical");

		ConnectionFactory connectionFactory = new ConnectionFactory() {
		};

		try (Connection connection = connectionFactory.recuperarConexao();) {
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);
			produtoDAO.salvar(comoda);
			List<Produto> lsitaDeProdutos = produtoDAO.listar(); 

		} catch (SQLException e) {
			System.out.println("erro ne conexão com banco de dados");
		}
		System.out.println(comoda);
	}

}
