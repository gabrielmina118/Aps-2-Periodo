package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Cidade;
import modelo.Mutirao;

public class MutiraoDAO {

	private Connection conecta;
	
	public MutiraoDAO(Connection con) {
		this.conecta = con;
	}
	
	public void salvar (Mutirao mutirao) throws SQLException {
		
		String sql = "INSERT INTO MUTIRAO(NOME,DATA_MUTIRAO,HORA) VALUES(?,?,?)";
		try(PreparedStatement pstm = conecta.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, mutirao.getNome());
			pstm.setString(2,mutirao.getData_mutirao());
			pstm.setString(3, mutirao.getHorario());
			pstm.execute();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					mutirao.setId(rst.getInt(1));
					System.out.println("ID criado com sucesso");
				}
			}
		}
		
	}
	
	public List<Mutirao> listar() throws SQLException{
		List<Mutirao> mutiroes = new ArrayList<Mutirao>();
		
		String sql = "SELECT ID_MUTIRAO,NOME,DATA_MUTIRAO,HORA FROM MUTIRAO";
		try(PreparedStatement pstm = conecta.prepareStatement(sql)){
			pstm.execute();
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Mutirao mutirao = new Mutirao(
								rst.getInt(1),
								rst.getString(2),
								rst.getString(3),
								rst.getString(4)
							);
					mutiroes.add(mutirao);
				}
			}
			
		}
		return mutiroes;
	}

	public List<Mutirao> buscar(Cidade cid1) throws SQLException {
		List<Mutirao> mutiroes = new ArrayList<Mutirao>();
		
		String sql = "SELECT ID_MUTIRAO,NOME,DATA_MUTIRAO,HORA FROM MUTIRAO WHERE CIDADE_ID = ?";
		try(PreparedStatement pstm = conecta.prepareStatement(sql)){
			
			pstm.setInt(1, cid1.getId());
			pstm.execute();
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Mutirao mutirao = new Mutirao(
								rst.getInt(1),
								rst.getString(2),
								rst.getString(3),
								rst.getString(4)
							);
					mutiroes.add(mutirao);
				}
			}
			
		}
		return mutiroes;
	}
}
