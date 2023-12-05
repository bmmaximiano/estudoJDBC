//@Autor: Maximiano B.M.

package br.com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

/*
 * Classe do tipo DAO (Data Access Object) classe respons�vel por isolar a l�gica de acesso de dados do resto da aplica��o
 * aqui ser� realizado a l�gica para manipula��o dos dados na base, ou seja, um encapsulamento da l�gica.
 * 
*/
public class ProdutoDAO {

	// criamos um objeto connection para realizar a conex�o com o banco de dados
	private Connection connection;

	// construtor que recebe como argumento uma connection para evitar a necessidade
	// de abrir uma c
	public ProdutoDAO(Connection connection) {

		this.connection = connection;
	}

	/*
	 * m�todo que realiza a percist�ncia (salvamento) dos atributos da classe no
	 * banco de dados � um m�todo void, pois n�o precisa retornar um valor, s�
	 * executa o input dos dados recebe como argumento um objeto do tipo produto e
	 * salvara na tabela produto do banco de dados
	 */
	public void salvar(Produto produto) throws SQLException { // pecisamos indicar a exception do sql

		String sql = "insert into produto (nome, descricao) values (?,?)"; // string contendo as instru��es de
																			// manipula��o do banco de dados

		/*
		 * m�todo try para verificar a conex�o, seu argumento � um PreparedStatement que
		 * � uma classe que possui uma l�gica de seguran�a para realizar as instru��es
		 * SQL com a prote��o contra a SQL Injection
		 */
		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			/*
			 * seta o valor dos atributos do produto no banco de dados, o valor inteiro do
			 * primeiro argumento � referente � coluna da tabela que deve ser inserido o
			 * dado. o segundo valor do argumento do m�todo � o valor a ser inserido
			 */
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());

			// realiza a execu��o das instru��es do pstm.
			pstm.execute();

			/*
			 * m�todo que seta o ID (primar key do produto)
			 */
			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}
		System.out.println(produto);
	}

	/*
	 * m�todo que retorna uma lista de produtos
	 */
	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		// instru��o a ser executada no banco de dados
		String sql = "select id, nome, descricao from produto";

		// try usado para realizar a persistencia no db
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			// executa o statement com as instru��es sql
			pstm.execute();

			// verifica o resultado da execu��o do statement
			try (ResultSet rst = pstm.getResultSet()) {

				// m�todo para verificar os itens da tabela e inserir na lista
				while (rst.next()) {

					// instancia o produto a partir dos dados da tabela
					Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

					// adiciona o produto na lista
					produtos.add(produto);
				}

				// retorna a lista de produtos
				return produtos;
			}
		}
	}
//metodo que lista cada produto contido em cada categoria recebendo como argumento uma categotia ct
	public List<Produto> bucar(Categoria ct) throws SQLException {
		//aqui criamos uma lista de produtos
		List<Produto>produtos = new ArrayList<Produto>();
		System.out.println("Executando a query de buscar produto por categoria");
		//comando sql
		String sql = "select id, nome, descricao from produto where categoria_id = ?";
		//statement (lembrando, ele � a classe com m�todos de seguran�a para realizar as consultas)
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			//aqui setamos qual a coluno correspondente � categoria na tabela produtos
			pstm.setInt(1, ct.getId());
			//execu��o do comando
			pstm.execute();
			//criando a lsita com os produtos de cada categoria
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
					produtos.add(produto);
				}
				return produtos;
			}
					
		}

	}

}
