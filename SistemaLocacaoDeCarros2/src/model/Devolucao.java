package model;

import java.time.LocalDate;

public class Devolucao  {
	private Integer idDevolucao;
	private LocalDate dataDevolucao;
	private String condicaoVeiculo;
	private Double taxaAtraso;
	private Boolean statusDevolucao;
	
	private Locacao locacaoDevolucao;

	



	public Devolucao(LocalDate dataDevolucao, String condicaoVeiculo, Double taxaAtraso, Boolean statusDevolucao,
			Locacao locacaoDevolucao) {
		super();
		this.dataDevolucao = dataDevolucao;
		this.condicaoVeiculo = condicaoVeiculo;
		this.taxaAtraso = taxaAtraso;
		this.statusDevolucao = statusDevolucao;
		this.locacaoDevolucao = locacaoDevolucao;
	}

	public Devolucao(Integer idDevolucao, LocalDate dataDevolucao, String condicaoVeiculo, Double taxaAtraso,
			Boolean statusDevolucao, Locacao locacaoDevolucao) {
		super();
		this.idDevolucao = idDevolucao;
		this.dataDevolucao = dataDevolucao;
		this.condicaoVeiculo = condicaoVeiculo;
		this.taxaAtraso = taxaAtraso;
		this.statusDevolucao = statusDevolucao;
		this.locacaoDevolucao = locacaoDevolucao;
	}



	public Devolucao() {
		super();
	}

	public Integer getIdDevolucao() {
		return idDevolucao;
	}

	public void setIdDevolucao(Integer idDevolucao) {
		this.idDevolucao = idDevolucao;
	}

	public Locacao getLocacaoDevolucao() {
		return locacaoDevolucao;
	}

	public void setLocacaoDevolucao(Locacao locacaoDevolucao) {
		this.locacaoDevolucao = locacaoDevolucao;
	}

	// Getters e setters
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getCondicaoVeiculo() {
		return condicaoVeiculo;
	}

	public void setCondicaoVeiculo(String condicaoVeiculo) {
		this.condicaoVeiculo = condicaoVeiculo;
	}

	public Double getTaxaAtraso() {
		return taxaAtraso;
	}

	public void setTaxaAtraso(Double taxaAtraso) {
		this.taxaAtraso = taxaAtraso;
	}

	public Boolean getStatusDevolucao() {
		return statusDevolucao;
	}

	public void setStatusDevolucao(Boolean statusDevolucao) {
		this.statusDevolucao = statusDevolucao;
	}
}
