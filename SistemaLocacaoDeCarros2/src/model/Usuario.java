package model;

import java.time.LocalDate;

public class Usuario {
	private Integer idUsuario;
	private String nomeCompleto;
	private String email;
	private String senha;
	private String telefone;
	private String endereco;
	private LocalDate dataCadastro;
	private String cpf;
 
	
	private String nivelAcesso;

	// Construtor completo
	public Usuario(Integer idUsuario, String nomeCompleto, String email, String senha, String telefone, String endereco,
			LocalDate dataCadastro,  String nivelAcesso, String cpf) {
		this.idUsuario = idUsuario;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
		this.nivelAcesso = nivelAcesso;
		this.cpf = cpf;
		
	}

	// Construtor vazio
	public Usuario() {
	}

	
	// Getters e setters
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
