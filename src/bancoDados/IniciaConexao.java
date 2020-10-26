package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IniciaConexao {
	
	public Connection conexao() throws SQLException {
		return DriverManager.
				getConnection("jdbc:mysql://localhost/projetoaps?useTimezone=true&serverTimezone=UTC","root","a258mina");
	}
}
