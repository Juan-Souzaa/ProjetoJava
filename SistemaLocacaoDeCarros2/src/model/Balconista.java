package model;

public class Balconista extends Usuario {
	private String matricula;
	private String turno;
	private String filial;

	// Construtor completo
	public Balconista(String matricula, String turno, String filial) {
		super();
		this.matricula = matricula;
		this.turno = turno;
		this.filial = filial;
	}

	// Construtor vazio
	public Balconista() {
	}

	public Balconista(String nomeCompleto, String email, String senha, String telefone, String endereco, String turno2,
			String filial2, String tipoUsuario, String nivelAcesso) {
		// TODO Auto-generated constructor stub
	}

	// Getters e setters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}



	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}
}
