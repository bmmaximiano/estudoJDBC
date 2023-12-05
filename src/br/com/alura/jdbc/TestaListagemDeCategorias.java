package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.DAO.CategoriaDAO;
import br.com.alura.jdbc.DAO.ProdutoDAO;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

 //Classe para realizar a listagem das categorias da tabela categoria do banco de dados
public class TestaListagemDeCategorias {
	
	public static void main(String[] args) throws SQLException{
		
		//realizmos o método try with resources para executar as ações com a conexão ao bando de dados
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
		//objeto para conexao com a tabela categorias do banco de dados	
		CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
		//criando uma lista de categorias
		List<Categoria> listaCategorias = categoriaDAO.listarComProduto();
		//usamos o metodo forEach para listar todas as categorias existentes 
		listaCategorias.stream().forEach(ct -> {
			//imprime o nome da categoria
			System.out.println(ct.getNome());
			for(Produto produto : ct.getProdutos()) {
				//imprimimos a categoria e o produto
				System.out.println(ct.getNome() + " - " + produto.getNome());
			}
				});

	}

}

}
