package model;

import java.time.LocalDate;

public class Balconista extends Usuario {
	private Integer idBalconista;
	private String turno;
	private String filial;

	// Construtor completo
	public Balconista( String turno, String filial) {
		super();
		
		this.turno = turno;
		this.filial = filial;
	}

	// Construtor vazio
	public Balconista() {
	}

	

	public Balconista(Integer idUsuario, String nomeCompleto, String email, String senha, String telefone,
			String endereco, LocalDate dataCadastro, String nivelAcesso, String cpf,  String turno,
			String filial) {
		super(idUsuario, nomeCompleto, email, senha, telefone, endereco, dataCadastro, nivelAcesso, cpf);
		
		this.turno = turno;
		this.filial = filial;
	}

	
	public Integer getIdBalconista() {
		return idBalconista;
	}

	public void setIdBalconista(Integer idBalconista) {
		this.idBalconista = idBalconista;
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
