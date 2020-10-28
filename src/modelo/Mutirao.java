package modelo;

public class Mutirao {

	private Integer id;
	private String nome;
	private String data_mutirao;
	private String horario;
	private Integer cidade_id;

	// Construtor da main
	public Mutirao(String nome, String data_mutirao, String horario) {
		this.nome = nome;
		this.data_mutirao = data_mutirao;
		this.horario = horario;
	}

	// Construtor da lista
	public Mutirao(Integer id, String nome, String data_mutirao, String horario) {
		this.id = id;
		this.nome = nome;
		this.data_mutirao = data_mutirao;
		this.horario = horario;
	}

	public Integer getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(Integer cidade_id) {
		this.cidade_id = cidade_id;
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

	public String getData_mutirao() {
		return data_mutirao;
	}

	public void setData_mutirao(String data_mutirao) {
		this.data_mutirao = data_mutirao;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return String.format("O mutirão é : %d - %s - %s - %s", this.id, this.nome, this.data_mutirao, this.horario);
	}
}
