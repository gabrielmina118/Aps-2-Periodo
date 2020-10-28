package principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bancoDados.IniciaConexao;
import controllers.CidadeDAO;
import modelo.Cidade;
import modelo.Mutirao;


public class principal {
	public static void main(String[] args) throws SQLException {
		
		IniciaConexao ia = new IniciaConexao();
		Connection con = ia.conexao();
		
			CidadeDAO cidade = new CidadeDAO(con);
			
			List<Cidade> listaCidades = cidade.listarComMutirao();
			
			
			
			for(Cidade cid1: listaCidades) {
				System.out.print("\n");
				System.out.println(cid1.getNome());
				for(Mutirao mt1: cid1.getMutiroes()) {
					System.out.print(cid1.getNome()+" - " + mt1.getNome());
					System.out.print("\n");
				}
			}
	}
}
