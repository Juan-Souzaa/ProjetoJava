package model;

import java.time.LocalDate;

public class AgenteLocacao extends Usuario {
	private Integer codigoAgente;
	private String regiaoAtuacao;

	// Construtor completo
	public AgenteLocacao(Integer codigoAgente, String regiaoAtuacao) {
		super();
		
		this.regiaoAtuacao = regiaoAtuacao;
	}

	// Construtor vazio
	public AgenteLocacao() {
	}

	
	public AgenteLocacao(Integer idUsuario, String nomeCompleto, String email, String senha, String telefone,
			String endereco, LocalDate dataCadastro, String nivelAcesso, String cpf,
			String regiaoAtuacao) {
		super(idUsuario, nomeCompleto, email, senha, telefone, endereco, dataCadastro, nivelAcesso, cpf);
	
		this.regiaoAtuacao = regiaoAtuacao;
	}

	// Getters e setters
	public Integer getCodigoAgente() {
		return codigoAgente;
	}

	public void setCodigoAgente(Integer codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	public String getRegiaoAtuacao() {
		return regiaoAtuacao;
	}

	public void setRegiaoAtuacao(String regiaoAtuacao) {
		this.regiaoAtuacao = regiaoAtuacao;
	}
}
