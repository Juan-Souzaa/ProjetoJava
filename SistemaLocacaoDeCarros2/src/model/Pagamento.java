package model;

import java.time.LocalDate;

public class Pagamento {
	private Integer idPagamento;
	private Double valor;
	private String metodoPagamento;
	private LocalDate dataPagamento;
	private String status;
	private String descricao;

	// Relacionamento
	private Fatura fatura; // Pagamento está vinculado a uma fatura

	public Pagamento(Double valor, String metodoPagamento, LocalDate dataPagamento, String status,
			String descricao, Fatura fatura) {
	
		this.valor = valor;
		this.metodoPagamento = metodoPagamento;
		this.dataPagamento = dataPagamento;
		this.status = status;
		this.descricao = descricao;
		this.fatura = fatura;
	}

	
	

	public Pagamento(Integer idPagamento, Double valor, String metodoPagamento, LocalDate dataPagamento, String status,
			String descricao, Fatura fatura) {
		super();
		this.idPagamento = idPagamento;
		this.valor = valor;
		this.metodoPagamento = metodoPagamento;
		this.dataPagamento = dataPagamento;
		this.status = status;
		this.descricao = descricao;
		this.fatura = fatura;
	}




	public Pagamento() {
		super();
	}

	// Getters e Setters
	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
}
