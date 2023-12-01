//@Autor: Maximiano B.M.

package br.com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.modelo.Produto;

/*
 * Classe do tipo DAO (Data Access Object) classe responsável por isolar a lógica de acesso de dados do resto da aplicação
 * aqui será realizado a lógica para manipulação dos dados na base, ou seja, um encapsulamento da lógica.
 * 
*/
public class ProdutoDAO {

	//criamos um objeto connection para realizar a conexão com o banco de dados
	private Connection connection; 
	
	//construtor que recebe como argumento uma connection
	public ProdutoDAO(Connection connection) {

		this.connection = connection;
	}

	/*
	* método que realiza a percistência (salvamento) dos atributos da classe no banco de dados
	* é um método void, pois não precisa retornar um valor, só executa o input dos dados
	* recebe como argumento um objeto do tipo produto e salvara na tabela produto do banco de dados
	*/
	public void salvar(Produto produto) throws SQLException { // pecisamos indicar a exception do sql

			String sql = "insert into produto (nome, descricao) values (?,?)"; // string contendo as instruções de manipulação do bado de dados

			/*
			 * método try para verificar a conexão, seu argumento é um PreparedStatement que é uma classe que possui uma lógica de segurança para
			 * realizar as instruções SQL com a proteção contra a SQL Injection 
			*/
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				/*
				 * seta o valor dos atributos do produto no banco de dados,
				 * o valor inteiro do primeiro argumento é referente à coluna da tabela que deve
				 * ser inserido o dado. o segundo valor do argumento do método é o valor a ser inserido
				 */
				pstm.setString(1, produto.getNome()); 
				pstm.setString(2, produto.getDescricao());
				
				//realiza a execução das instruções do pstm.
				pstm.execute(); 

				/*
				 *método que seta o ID (primar key do produto)*/
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}
				}
			}
		System.out.println(produto);
	}
	
	/*
	 * método que retorna uma lista de produtos*/
	public List<Produto> listar() throws SQLException{
		List<Produto> produtos = new ArrayList<Produto>();
		
		//instrução a ser executada no banco de dados
		String sql = "select id, nome, descricao from produto";
		
		//try usado para realizar a persistencia no db
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			
			// executa o statement com as instruções sql
			pstm.execute(); 
			
			// verifica o resultado da execução do statement
			try(ResultSet rst = pstm.getResultSet()){
				
				// método para verificar os itens da tabela e inserir na lista
				while(rst.next()) { 
					
					//instancia o produto a partir dos dados da tabela
					Produto produto = new Produto (rst.getInt(1), rst.getString(2), rst.getString(3)); 
					
					//adiciona o produto na lista
					produtos.add(produto); 
				}
				
				//retorna a lista de produtos
				return produtos;
			}
		}
	}

}
