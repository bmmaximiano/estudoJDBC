import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
//		String nome = "mouse'";
//		String descricao = "mouse sem fio); delete from produto";

		ConnectionFactory factory = new ConnectionFactory() {
		};

		try (Connection connection = factory.recuperarConexao()) {
			connection.setAutoCommit(false);

			try (PreparedStatement stm = connection.prepareStatement(
					"insert into produto (nome, descricao) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {

				adicionarVariavel("SmartTV", "45 polegads", stm);
				adicionarVariavel("Radio", "radio de bateria", stm);

				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Falha ao atualizar DB: " + e);
				connection.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		if (nome.equals("Radio")) {
			throw new RuntimeException("não deu pra add");
		}

		stm.execute();

		try (ResultSet rst = stm.getGeneratedKeys()) {
			while (rst.next()) {
				Integer id = rst.getInt(1);

				System.out.println(id);
			}
		}

	}

}
