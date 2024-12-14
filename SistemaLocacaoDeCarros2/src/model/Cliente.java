package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
	protected Integer idCliente;
	private LocalDate dataNascimento;
	private String categoriaCNH;
	


	

	public Cliente() {
	};

	

	


	public Cliente(LocalDate dataNascimento, String categoriaCNH) {
		super();
		this.dataNascimento = dataNascimento;
		this.categoriaCNH = categoriaCNH;
		
	}






	public Cliente(Integer idUsuario, String nomeCompleto, String email, String senha, String telefone, String endereco,
			LocalDate dataCadastro, String nivelAcesso, String cpf,
			LocalDate dataNascimento, String categoriaCNH) {

		super(idUsuario, nomeCompleto, email, senha, telefone, endereco, dataCadastro, nivelAcesso, cpf);
		
		this.dataNascimento = dataNascimento;
		this.categoriaCNH = categoriaCNH;
	

	}

	




	// Getters e Setters
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCategoriaCNH() {
		return categoriaCNH;
	}

	public void setCategoriaCNH(String categoriaCNH) {
		this.categoriaCNH = categoriaCNH;
	}


}