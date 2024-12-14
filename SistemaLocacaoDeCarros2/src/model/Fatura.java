package model;

import java.time.LocalDate;

public class Fatura {
	private String numeroFatura;
	private LocalDate dataEmissao;
	private Double valorTotal;
	private String observacoes;
	private Locacao locacaoFatura;

	
	private Pagamento pagamento; 

	public Fatura(String numeroFatura, LocalDate dataEmissao, Double valorTotal, String observacoes,
			Pagamento pagamento) {
		this.numeroFatura = numeroFatura;
		this.dataEmissao = dataEmissao;
		this.valorTotal = valorTotal;
		this.observacoes = observacoes;
		this.pagamento = pagamento;
	}

	public Fatura(String numeroFatura, LocalDate dataEmissao, Double valorTotal, String observacoes) {
		super();
		this.numeroFatura = numeroFatura;
		this.dataEmissao = dataEmissao;
		this.valorTotal = valorTotal;
		this.observacoes = observacoes;
	}

	public Fatura() {
		
	}

	public Fatura(String numeroFatura, LocalDate dataEmissao, Double valorTotal, String observacoes2,
			Locacao locacao) {
		// TODO Auto-generated constructor stub
	}


	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Locacao getLocacaoFatura() {
		return locacaoFatura;
	}

	public void setLocacaoFatura(Locacao locacaoFatura) {
		this.locacaoFatura = locacaoFatura;
	}
}
