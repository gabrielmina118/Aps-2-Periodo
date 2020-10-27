package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bancoDados.IniciaConexao;

public class testeListagem {
	public static void main(String[] args) throws SQLException {
		
		IniciaConexao ic = new IniciaConexao();
		Connection con = ic.conexao();
	
		String sql = "SELECT ID_PESSOA,NOME,ENDERECO,IDADE FROM PESSOA";
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.execute();
		ResultSet rst = pstm.getResultSet();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println(id);
			
			String nome = rst.getString(2);
			System.out.println(nome);
			
			String endereco = rst.getString(3);
			System.out.println(endereco);
			
			Integer idade = rst.getInt(4);
			System.out.println(idade);
		}
		con.close();
		
	}
}
