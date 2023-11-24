import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		String nome = "null";
		List<String> lista = new ArrayList<String>();

		ConnectionFactory factory = new ConnectionFactory() {
		};
        
		Connection connection = factory.recuperarConexao();
		
		PreparedStatement stm = connection.prepareStatement("delete from produto where id > ?");
		stm.setInt(1, 2);
		
		stm.execute();
		
		Integer linhasModificadas = stm.getUpdateCount();
		
		System.out.println("Qauntidade de linhas deletadas: " + linhasModificadas);
		
		
	}

}
