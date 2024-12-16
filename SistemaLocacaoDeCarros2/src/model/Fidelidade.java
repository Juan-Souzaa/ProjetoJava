package model;

import java.time.LocalDate;

public class Fidelidade {
	private String idFidelidade;
	private Integer pontos;
	private String nivel;
	private LocalDate dataUltimaAtualizacao;
	
	private Cliente clienteFidelidade ;

	// Construtor completo
	public Fidelidade( Integer pontos, String nivel, LocalDate dataUltimaAtualizacao, Cliente cliente) {
		
		this.pontos = pontos;
		this.nivel = nivel;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.clienteFidelidade = cliente;
	}

	// Construtor vazio
	public Fidelidade() {
	}

	// Getters e setters
	public String getIdFidelidade() {
		return idFidelidade;
	}

	public void setIdFidelidade(String idFidelidade) {
		this.idFidelidade = idFidelidade;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Cliente getClienteFidelidade() {
		return clienteFidelidade;
	}

	public void setClienteFidelidade(Cliente clienteFidelidade) {
		this.clienteFidelidade = clienteFidelidade;
	}

	public LocalDate getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(LocalDate dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
}
