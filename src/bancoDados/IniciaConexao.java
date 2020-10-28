package bancoDados;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class IniciaConexao {
	
	// interface da pool de conexoes
	public DataSource dataSource; 
	
	// pool de conexoes
	public IniciaConexao() {
		ComboPooledDataSource combodataSource = new ComboPooledDataSource();
		combodataSource.setJdbcUrl("jdbc:mysql://localhost/projetoaps?useTimezone=true&serverTimezone=UTC");
		combodataSource.setUser("root");
		combodataSource.setPassword("a258mina");
		
		combodataSource.setMaxPoolSize(16);
		
		this.dataSource = combodataSource;
	}
	
	public Connection conexao() {
		try{
			return this.dataSource.getConnection();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
