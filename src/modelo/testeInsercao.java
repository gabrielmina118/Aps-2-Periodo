package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bancoDados.IniciaConexao;

public class testeInsercao {

	public static void main(String[] args) throws SQLException {
		
		IniciaConexao ic = new IniciaConexao();
		Connection con = ic.conexao();
		
		Statement stm = con.createStatement();
		// Cria o statement e retorna o ID gerado
		stm.execute("INSERT INTO PESSOA(NOME,ENDERECO,IDADE) VALUES('IGOR MINA','PRAÇA SECA',26)"
					,Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.print("O id: " + id + " foi gerado com sucesso");
		}
	}
}
