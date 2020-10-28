package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cidade {

	private Integer id;
	private String nome;
	private String estado;
	private List<Mutirao> mutiroes = new ArrayList<Mutirao>();

	// construtor da main
	public Cidade(String nome, String estado) {
		this.nome = nome;
		this.estado = estado;
	}

	// construtor da lista
	public Cidade(Integer id, String nome, String estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public List<Mutirao> getMutiroes() {
		return mutiroes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return String.format("%s",this.nome);
	}

	public void adicionar(Mutirao mutirao) {
		mutiroes.add(mutirao);

	}
}
