package DAO;

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
	
	public void salvarComCidade(Mutirao mutirao) throws SQLException{
		String sql = "INSERT INTO MUTIRAO(NOME,DATA_MUTIRAO,HORA,CIDADE_ID) VALUES (?,?,?,?)";
		
		try(PreparedStatement pstm = conecta.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1,mutirao.getNome());
			pstm.setString(2,mutirao.getData_mutirao());
			pstm.setString(3,mutirao.getHorario());
			pstm.setInt(4, mutirao.getCidade_id());
			
			pstm.execute();
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					mutirao.setId(rst.getInt(1));
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
	
	public void deletar(Integer id) throws SQLException {
		
		String sql = "DELETE FROM MUTIRAO WHERE ID_MUTIRAO = ?";
		
		try(PreparedStatement pstm = conecta.prepareStatement(sql)){
			pstm.setInt(1, id);
			pstm.execute();
		}
	}
	
	public void alterar(String nome,String data_mutirao,String hora , Integer id) throws SQLException{
		String sql = "UPDATE MUTIRAO M SET M.NOME =? , M.DATA_MUTIRAO =? , M.HORA =? WHERE ID_MUTIRAO =? ";
		try(PreparedStatement pstm = conecta.prepareStatement(sql)){
			pstm.setString(1, nome);
			pstm.setString(2, data_mutirao);
			pstm.setString(3, hora);
			pstm.setInt(4,id);
			pstm.execute();
		}
	}
	
}
