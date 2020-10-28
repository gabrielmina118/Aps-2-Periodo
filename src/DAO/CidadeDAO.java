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

public class CidadeDAO {

	private Connection conecta;
	
		public CidadeDAO(Connection con) {
			this.conecta = con;
		}
	
		public void salvar(Cidade cidade) throws SQLException {
			String sql = "INSERT INTO CIDADE(NOME,ESTADO) VALUES(?,?)";
			try(PreparedStatement pstm = conecta.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, cidade.getNome());
				pstm.setString(2, cidade.getEstado());
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						cidade.setId(rst.getInt(1));
						System.out.println("ID criado com sucesso");
					}
				}
			}
			
		}
	
	public List<Cidade> listar() {
		try{
			List<Cidade> cidades = new ArrayList<Cidade>();

			String sql = "SELECT ID_CIDADE,NOME,ESTADO FROM CIDADE";
			try(PreparedStatement pstm = conecta.prepareStatement(sql)){
				pstm.execute();
				try(ResultSet rst = pstm.getResultSet()){
					while(rst.next()) {
						Cidade cid = new Cidade(
									rst.getInt(1),
									rst.getString(2),
									rst.getString(3)								
								);
						cidades.add(cid);
					}
				}
				
			}
				return cidades;
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		

	public List<Cidade> listarComMutirao(){
		try {
			Cidade ultima = null;
			List<Cidade> cidades = new ArrayList<Cidade>();
			
			String sql = "select c.id_cidade,c.nome,c.estado,m.id_mutirao,m.nome,m.data_mutirao,m.hora "
					+ "from cidade as c inner join mutirao as m where m.cidade_id = c.id_cidade;";
			try(PreparedStatement pstm = conecta.prepareStatement(sql)){
				pstm.execute();
				try(ResultSet rst = pstm.getResultSet()){
					while(rst.next()) {
						// Para que nao haja repeticao de cidade
						if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
							Cidade cid = new Cidade(
									rst.getInt(1),
									rst.getString(2),
									rst.getString(3)								
								);
								ultima = cid;
								cidades.add(cid);
						}
						Mutirao mutirao = new Mutirao(
									rst.getInt(4),
									rst.getString(5),
									rst.getString(6),
									rst.getString(7)
								);
						ultima.adicionar(mutirao);
					}
				}
				
			}
			return cidades;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

