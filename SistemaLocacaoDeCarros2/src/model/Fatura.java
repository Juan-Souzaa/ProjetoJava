package model;

import java.time.LocalDate;

public class Fatura {
	private Integer numeroFatura;
	private LocalDate dataEmissao;
	private Double valorTotal;
	private String observacoes;
	private Locacao locacaoFatura;

	
	

	public Fatura( LocalDate dataEmissao, Double valorTotal, String observacoes,
		Locacao	locacao) {
	
		this.dataEmissao = dataEmissao;
		this.valorTotal = valorTotal;
		this.observacoes = observacoes;
		this.locacaoFatura = locacao;
		
	}

	
	

	public Fatura(Integer numeroFatura, LocalDate dataEmissao, Double valorTotal, String observacoes,
			Locacao locacaoFatura) {
		super();
		this.numeroFatura = numeroFatura;
		this.dataEmissao = dataEmissao;
		this.valorTotal = valorTotal;
		this.observacoes = observacoes;
		this.locacaoFatura = locacaoFatura;
	}




	public Fatura() {
		
	}

	public Fatura(String numeroFatura, LocalDate dataEmissao, Double valorTotal, String observacoes2,
			Locacao locacao) {
		// TODO Auto-generated constructor stub
	}


	public Integer getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(Integer numeroFatura) {
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



	public Locacao getLocacaoFatura() {
		return locacaoFatura;
	}

	public void setLocacaoFatura(Locacao locacaoFatura) {
		this.locacaoFatura = locacaoFatura;
	}
}
