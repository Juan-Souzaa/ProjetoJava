package model;

import java.time.LocalDate;

public class Locacao {
	public Integer idLocacao;
	private LocalDate dataLocacao;
	private LocalDate dataDevolucaoPrevista;
	private LocalDate dataDevolucaoReal;
	private Double valorTotal;
	private String tipoLocacao;
	private String observacoes;

	// Relacionamentos
	private Cliente clienteLocacao; // Uma locação pertence a um cliente
	private Veiculo veiculoLocacao; // Um veículo está associado à locação
	private Fatura fatura; // Uma fatura está vinculada à locação

	public Locacao(Integer idLocacao, LocalDate dataLocacao, LocalDate dataDevolucaoPrevista,
			LocalDate dataDevolucaoReal, Double valorTotal, String tipoLocacao, String observacoes, Cliente cliente,
			Veiculo veiculo, Fatura fatura) {
		this.idLocacao = idLocacao;
		this.dataLocacao = dataLocacao;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.dataDevolucaoReal = dataDevolucaoReal;
		this.valorTotal = valorTotal;
		this.tipoLocacao = tipoLocacao;
		this.observacoes = observacoes;
		this.setClienteLocacao(cliente);
		this.setVeiculoLocacao(veiculo);
		this.fatura = fatura;
	}

	public Locacao(Integer idLocacao, LocalDate dataLocacao, LocalDate dataDevolucaoPrevista,
			LocalDate dataDevolucaoReal, Double valorTotal, String tipoLocacao, String observacoes) {
		super();
		this.idLocacao = idLocacao;
		this.dataLocacao = dataLocacao;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.dataDevolucaoReal = dataDevolucaoReal;
		this.valorTotal = valorTotal;
		this.tipoLocacao = tipoLocacao;
		this.observacoes = observacoes;
	}

	public Locacao() {
		super();
	}



	// Getters e Setters
	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}

	public LocalDate getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public LocalDate getDataDevolucaoReal() {
		return dataDevolucaoReal;
	}

	public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
		this.dataDevolucaoReal = dataDevolucaoReal;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTipoLocacao() {
		return tipoLocacao;
	}

	public void setTipoLocacao(String tipoLocacao) {
		this.tipoLocacao = tipoLocacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public Cliente getClienteLocacao() {
		return clienteLocacao;
	}

	public void setClienteLocacao(Cliente clienteLocacao) {
		this.clienteLocacao = clienteLocacao;
	}

	public Veiculo getVeiculoLocacao() {
		return veiculoLocacao;
	}

	public void setVeiculoLocacao(Veiculo veiculoLocacao) {
		this.veiculoLocacao = veiculoLocacao;
	}
}
