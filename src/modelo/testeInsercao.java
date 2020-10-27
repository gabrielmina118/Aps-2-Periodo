package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bancoDados.IniciaConexao;

public class testeInsercao {

		public static void main(String[] args) throws SQLException {
			
			IniciaConexao ic = new IniciaConexao();
			try(Connection con = ic.conexao()){
			con.setAutoCommit(false);
			
			String sql = "INSERT INTO PESSOA(NOME,ENDERECO,IDADE) VALUES(?,?,?)";
			
			// try com recursos para fechar todas as transicoes
			try (
					PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					){
				Adicionar("teste1","teste1",11,pstm);
				Adicionar("teste2","teste2",12,pstm);
				Adicionar("teste3","teste3",13,pstm);
				
				con.commit();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.print("RoolBack Executado");
				con.rollback();
			}
		}
	}

		private static void Adicionar(String nome,String endereco, int idade,PreparedStatement pstm) throws SQLException {
			pstm.setString(1, nome);
			pstm.setString(2, endereco);
			pstm.setInt(3, idade);			
			pstm.execute();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					Integer id = rst.getInt(1);
					System.out.println("O id: " + id + " foi gerado com sucesso");
				}
			}
			
		}
}