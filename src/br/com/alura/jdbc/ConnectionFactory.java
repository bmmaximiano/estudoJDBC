package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
* classe responsável pela conexão com o banco de dados.
*onde já é definido as configurações como url do dv, user e passworda
*/
public class ConnectionFactory {

	//objeto DataSource que contém métodos de conexão com a fonte de dados 
	public DataSource dataSource;

	/*
	* método onde será criado o pool de coexões. que define a quantidade de conexões simultâneas ao DB além das
	*configurações de acesso ao DB.
	*/
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("bm34280");
		comboPooledDataSource.setMaxPoolSize(15);
		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException {
		return dataSource.getConnection();

	}

}
