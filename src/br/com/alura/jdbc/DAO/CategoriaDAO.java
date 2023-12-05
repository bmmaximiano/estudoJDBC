package br.com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.ConnectionFactory;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

public class CategoriaDAO {
	
	//objeto de conex�o
	private Connection connection;
	
	//Constructor recebendo a conexao para a DAO
	public CategoriaDAO(Connection connection){
		this.connection = connection;
	}
	
	//m�todo para listar as categorias
	public List<Categoria>listar () throws SQLException {
		//criando a lista
		List <Categoria> categorias = new ArrayList<>();
		System.out.println("Executando a query de listar categoria");
		//string com os comandos slq
		String sql = "select id, nome from categoria";
		//prepara a instru��o, nesse caso sem o RETUR_ GENERATED_KEY pois vamos realizar uma consulta
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			//executa as instru��es
			pstm.execute();
			//obtendo o resultado do comando realizado pelo pstm
			try(ResultSet rst = pstm.getResultSet()){
				//verificando se h� uma linha n�o nula e segue 
				while(rst.next()) {
					//se houver uma linha, criamos um objeto categoria recebendo o id(1) e o nome(2) que est�o na tabela
					Categoria categoria = new Categoria(rst.getInt(1),rst.getString(2));
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}

	public List<Categoria> listarComProduto() throws SQLException {
		//objeto categoria para quebrar a inclus�o de repeti��o de categorias
		Categoria ultima = null;
		//criando a lista
		List <Categoria> categorias = new ArrayList<>();
		System.out.println("Executando a query de listar categoria");
		//string com os comandos slq - aqui temos um comando sql que vincula tabelas
		String sql = "select c.id, c.nome, p.id, p.nome, p.descricao from categoria c inner join " + "produto p on c.id = p.categoria_id";
		//prepara a instru��o, nesse caso sem o RETUR_ GENERATED_KEY pois vamos realizar uma consulta
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			//executa as instru��es
			pstm.execute();
			//obtendo o resultado do comando realizado pelo pstm
			try(ResultSet rst = pstm.getResultSet()){
				//verificando se h� uma linha n�o nula e segue para criar uma instancia de uma categoria 
				while(rst.next()) {
					
					// valida��o para n�o colocar a mesmsa categoria quando tiver mais de um produto
					//fazemos uma verifica��o se a categoria � nula ou se ela j� existe, e depois de validar, cria uma categoria
					if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						//se houver uma linha, criamos um objeto categoria recebendo o id(1) e o nome(2) que est�o na tabela
						Categoria categoria = new Categoria(rst.getInt(1),rst.getString(2));
						
						//incluimos o valor da categoria para valida��o do if
						ultima = categoria;
						// adiciona a categoria na lista categorias
						categorias.add(categoria);
						}
					Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
					ultima.adicionarProduto(produto);
				}
			}
		}
		return categorias;
	}
		
	

}
