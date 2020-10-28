package Principal;

import java.sql.Connection;
import java.util.List;

import DAO.CidadeDAO;
import bancoDados.IniciaConexao;
import modelo.Cidade;
import modelo.Mutirao;

public class Main {

	public static void main(String[] args) {
	
		IniciaConexao ia = new IniciaConexao();
		Connection con = ia.conexao();
		
			CidadeDAO cidade = new CidadeDAO(con);
			
			List<Cidade> listaCidades = cidade.listarComMutirao();
	
			for(Cidade cid1: listaCidades) {
				System.out.print("\n");
				System.out.println(cid1.getNome());
				for(Mutirao mt1: cid1.getMutiroes()) {
					System.out.print(mt1.getNome()+" - "+mt1.getData_mutirao() + " - "+ mt1.getHorario());
					System.out.print("\n");
				}
			}
		
	}
}
