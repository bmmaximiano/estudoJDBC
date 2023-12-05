package br.com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	private Integer id;
	private String nome;
	private List<Produto>produtos = new ArrayList<Produto>();
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		
		return String.format("C %d, %s", this.id, this.nome);
	}

	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
	}
// retorna uma lista de produtos
	public List<Produto> getProdutos() {
		return produtos;
	}

	
	

}
