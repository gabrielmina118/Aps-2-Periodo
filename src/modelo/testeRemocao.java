package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bancoDados.IniciaConexao;

public class testeRemocao {

	public static void main(String[] args) throws SQLException {
		
		IniciaConexao ia = new IniciaConexao();
		Connection con = ia.conexao();
		
		String sql = "DELETE FROM PESSOA WHERE ID_PESSOA >= ?";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, 21);
		pstm.execute();
		
		Integer linhasAfetadas = pstm.getUpdateCount();
		System.out.println("Quantidade de linhas afetadas : " + linhasAfetadas);
	}
}
