package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
* classe respons�vel pela conex�o com o banco de dados.
*onde j� � definido as configura��es como url do dv, user e passworda
*/
public class ConnectionFactory {

	//objeto DataSource que cont�m m�todos de conex�o com a fonte de dados 
	public DataSource dataSource;

	/*
	* m�todo onde ser� criado o pool de coex�es. que define a quantidade de conex�es simult�neas ao DB al�m das
	*configura��es de acesso ao DB.
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
